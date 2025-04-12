package com.volvo.emsp.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Account {

    private Long id;
    private String email;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public enum AccountStatus {
        CREATED, ACTIVATED, DEACTIVATED
    }
}