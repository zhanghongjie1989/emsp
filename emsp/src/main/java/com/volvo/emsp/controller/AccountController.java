package com.volvo.emsp.controller;

import com.volvo.emsp.common.PageResult;
import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.mapper.AccountSwitchMapper;
import com.volvo.emsp.request.AccountRequest;
import com.volvo.emsp.response.AccountResponse;
import com.volvo.emsp.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "account", description = "Operations related to account management")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountSwitchMapper accountSwitchMapper;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Validated @RequestBody AccountRequest request) {
        Account account = accountService.createAccount(accountSwitchMapper.map2(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountSwitchMapper.map2(account));
    }

    @PatchMapping("/{email}/status")
    public ResponseEntity<AccountResponse> changeAccountStatus(
            @PathVariable String email,
            @RequestParam Account.AccountStatus newStatus) {
        Account account = accountService.changeAccountStatus(email, newStatus);
        return ResponseEntity.ok(accountSwitchMapper.map2(account));
    }

    @GetMapping
    public ResponseEntity<PageResult<AccountResponse>> getAccountsByLastUpdated(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastUpdated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Account> response = accountService.getAccountsByLastUpdated(lastUpdated, page, size);
        return ResponseEntity.ok(PageResult.getPageResult(page, size, accountSwitchMapper.map2(accountService.getAccountsByLastUpdated(lastUpdated, page, size))));
    }
}