package com.volvo.emsp.reposity.entity;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.response.AccountResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "account")
public class AccountEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String contractId;

    @Enumerated(EnumType.STRING)
    private Account.AccountStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}