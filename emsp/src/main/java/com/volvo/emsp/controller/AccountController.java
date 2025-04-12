package com.volvo.emsp.controller;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.mapper.AccountSwitchMapper;
import com.volvo.emsp.request.AccountRequest;
import com.volvo.emsp.response.AccountResponse;
import com.volvo.emsp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid; // 添加这一行

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;



    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{email}/status")
    public ResponseEntity<AccountResponse> changeAccountStatus(
            @PathVariable String email,
            @RequestParam Account.AccountStatus newStatus) {
        AccountResponse response = accountService.changeAccountStatus(email, newStatus);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> getAccountsByLastUpdated(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastUpdated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<AccountResponse> response = accountService.getAccountsByLastUpdated(lastUpdated, page, size);
        return ResponseEntity.ok(response);
    }
}