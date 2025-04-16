package com.volvo.emsp.request;


import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class CardRequest {
    private Long id;
    private String emaid;
    private Account account;
    private String contractId;
    private Card.CardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
