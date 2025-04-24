package com.volvo.emsp.service;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.AccountRepository;
import com.volvo.emsp.reposity.mapper.Account2EntitySwitchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Account2EntitySwitchMapper account2EntitySwitchMapper;

    public Account createAccount(Account account) {
        if(accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Account with this email already exists.");
        }
        account.setStatus(Account.AccountStatus.CREATED);
        account.setContractId(account.generateEmaid());
        return account2EntitySwitchMapper.map2(accountRepository.save(account2EntitySwitchMapper.map2(account)));
    }

    public Account changeAccountStatus(String email, Account.AccountStatus status) {
        Account account = account2EntitySwitchMapper.map2(accountRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Account not found")));
        account.setStatus(status);
        account.setUpdatedAt(LocalDateTime.now());
        return account2EntitySwitchMapper.map2(accountRepository.save(account2EntitySwitchMapper.map2(account)));

    }

    public List<Account> getAccountsByLastUpdated(LocalDateTime lastUpdated, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return account2EntitySwitchMapper.map2(accountRepository.findByUpdatedAt(lastUpdated, pageable));
    }
}