package com.example.user_account_service.service;

import com.example.user_account_service.dto.UserDTO;
import com.example.user_account_service.dto.AccountDTO;
import com.example.user_account_service.model.User;
import com.example.user_account_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(User user) {
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            User updated = userRepository.save(user);
            return convertToDTO(updated);
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAccounts(user.getAccounts().stream()
                .map(account -> {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setId(account.getId());
                    accountDTO.setAccountNumber(account.getAccountNumber());
                    accountDTO.setBalance(account.getBalance());
                    return accountDTO;
                }).collect(Collectors.toList()));
        return dto;
    }
}
