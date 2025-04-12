package com.volvo.emsp.service;


import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(String emaid, String contractId) {
        Card card = new Card();
        card.setEmaid(emaid);
        card.setContractId(contractId);
        card.setStatus(Card.CardStatus.CREATED);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());
        return cardRepository.save(card);
    }

    public Card assignCardToAccount(Long cardId, Long accountId) {
        // Implementation omitted for brevity
    }
}