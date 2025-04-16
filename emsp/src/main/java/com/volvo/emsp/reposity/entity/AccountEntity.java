package com.volvo.emsp.reposity.entity;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.response.AccountResponse;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Account.AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}