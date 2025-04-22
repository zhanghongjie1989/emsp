package com.volvo.emsp.service;


import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.AccountRepository;
import com.volvo.emsp.reposity.CardRepository;
import com.volvo.emsp.reposity.mapper.Card2EntitySwitchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Card2EntitySwitchMapper card2EntitySwitchMapper;

    public Card createCard(Card card) {
        card.setUID(card.generateUID());
        return card2EntitySwitchMapper.map2(cardRepository.save(card2EntitySwitchMapper.map2(card)));
    }

    public Card assignCardToAccount(String uid, String email) {
        accountRepository.findByEmail(email).ifPresent(account -> {
            cardRepository.findByUID(uid).ifPresent(card -> {card.setAccount(account);
            cardRepository.save(card);});
        });
        return card2EntitySwitchMapper.map2(cardRepository.findByUID(uid));
    }

    public Card changeCardStatus(String emaid, Card.CardStatus newStatus) {
        return null;
    }

    public List<Card> getCardsByLastUpdated(LocalDateTime lastUpdated, int page, int size) {
        return null;
    }
}