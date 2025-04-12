package com.volvo.emsp.reposity.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emaid;

    @Column(nullable = false)
    private String contractId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
    public enum CardStatus {
        CREATED, ASSIGNED, ACTIVATED, DEACTIVATED
    }
}