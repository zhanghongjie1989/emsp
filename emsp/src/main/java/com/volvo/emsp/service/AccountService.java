package com.volvo.emsp.service;

import com.emsp.accountcard.entity.Account;
import com.emsp.accountcard.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String email) {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Account with this email already exists.");
        }
        Account account = new Account();
        account.setEmail(email);
        account.setStatus(Account.AccountStatus.CREATED);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public Account changeAccountStatus(Long id, Account.AccountStatus status) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setStatus(status);
        account.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }
}