package com.volvo.emsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam AccountStatus newStatus) {
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