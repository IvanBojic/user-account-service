package com.example.user_account_service.controller;

import com.example.user_account_service.dto.AccountDTO;
import com.example.user_account_service.model.Account;
import com.example.user_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/users/{userId}")
    public ResponseEntity<AccountDTO> createAccount(@PathVariable Long userId, @RequestBody Account account) {
        return accountService.createAccount(userId, account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(accountDTO -> ResponseEntity.ok(accountDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<AccountDTO>> getAccountsByUserId(@PathVariable Long userId) {
        List<AccountDTO> accounts = accountService.getAccountsByUserId(userId);
        return accounts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.ok("Account successfully deleted.");
        } else {
            return ResponseEntity.status(404).body("Account not found.");
        }
    }
}
