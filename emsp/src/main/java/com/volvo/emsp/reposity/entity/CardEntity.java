package com.volvo.emsp.reposity.entity;

import com.volvo.emsp.domain.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards", schema = "account")
public class CardEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emaid;

    @Column(nullable = false)
    private String contractId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    private Card.CardStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}