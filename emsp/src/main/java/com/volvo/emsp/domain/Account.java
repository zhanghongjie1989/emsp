package com.volvo.emsp.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String id;
    private String email;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Getters and Setters
    @Getter
    @RequiredArgsConstructor
    public enum AccountStatus {
        CREATED, ACTIVATED, DEACTIVATED
    }
}