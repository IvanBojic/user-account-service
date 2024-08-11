package com.example.user_account_service.service;

import com.example.user_account_service.dto.AccountDTO;
import com.example.user_account_service.model.Account;
import com.example.user_account_service.model.User;
import com.example.user_account_service.repository.AccountRepository;
import com.example.user_account_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<AccountDTO> createAccount(Long userId, Account account) {
        return userRepository.findById(userId).map(user -> {
            account.setUser(user);
            Account savedAccount = accountRepository.save(account);
            return ResponseEntity.status(201).body(convertToDTO(savedAccount));
        }).orElse(ResponseEntity.status(404).body(null));
    }

    public Optional<AccountDTO> getAccountById(Long id) {
        return accountRepository.findById(id).map(this::convertToDTO);
    }

    public List<AccountDTO> getAccountsByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAccounts)
                .map(accounts -> accounts.stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList()))
                .orElseGet(List::of);
    }

    public boolean deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBalance(account.getBalance());
        return dto;
    }
}
