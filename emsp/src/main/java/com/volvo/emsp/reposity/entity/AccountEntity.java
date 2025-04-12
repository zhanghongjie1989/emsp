package com.volvo.emsp.reposity.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public enum AccountStatus {
        CREATED, ACTIVATED, DEACTIVATED
    }
}