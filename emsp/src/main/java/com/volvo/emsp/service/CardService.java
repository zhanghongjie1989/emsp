package com.volvo.emsp.service;


import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.CardRepository;
import com.volvo.emsp.reposity.mapper.Card2EntitySwitchMapper;
import com.volvo.emsp.request.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private Card2EntitySwitchMapper card2EntitySwitchMapper;

    public Card createCard(Card card) {
        card.setUID(card.getUID());
        return card2EntitySwitchMapper.map2(cardRepository.save(card2EntitySwitchMapper.map2(card)));
    }

    public Card assignCardToAccount(String cardId, String accountId) {
        // Implementation omitted for brevity
        return null;
    }

    public Card changeCardStatus(String emaid, Card.CardStatus newStatus) {
        return null;
    }

    public List<Card> getCardsByLastUpdated(LocalDateTime lastUpdated, int page, int size) {
        return null;
    }
}