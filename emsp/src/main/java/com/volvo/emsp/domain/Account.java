package com.volvo.emsp.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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