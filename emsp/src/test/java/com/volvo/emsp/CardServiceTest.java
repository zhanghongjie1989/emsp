package com.volvo.emsp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.domain.Card;
import com.volvo.emsp.reposity.AccountRepository;
import com.volvo.emsp.reposity.CardRepository;
import com.volvo.emsp.reposity.entity.AccountEntity;
import com.volvo.emsp.reposity.entity.CardEntity;
import com.volvo.emsp.reposity.mapper.Card2EntitySwitchMapper;
import com.volvo.emsp.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Card2EntitySwitchMapper mapper;

    @InjectMocks
    private CardService cardService;

    private final LocalDateTime testTime = LocalDateTime.now();
    private final String testUID = "CARD_123";
    private final String testEmail = "test@example.com";

    // Test objects
    private Card testCard;
    private CardEntity testCardEntity;
    private Account testAccount;
    private AccountEntity testAccountEntity;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setEmail(testEmail);
        
        testCard = new Card();
        testCard.setUID(testUID);
        testCard.setStatus(Card.CardStatus.ACTIVATED);
        
        testAccountEntity = new AccountEntity();
        testAccountEntity.setEmail(testEmail);
        
        testCardEntity = new CardEntity();
        testCardEntity.setUID(testUID);
    }

    @Test
    void createCard_ShouldGenerateUIDAndSave() {
        // Arrange
        when(mapper.map2(any(Card.class))).thenReturn(testCardEntity);
        when(cardRepository.save(any(CardEntity.class))).thenReturn(testCardEntity);
        when(mapper.map2(any(CardEntity.class))).thenReturn(testCard);

        // Act
        Card result = cardService.createCard(new Card());

        // Assert
        assertNotNull(result.getUID());
        verify(cardRepository).save(any(CardEntity.class));
        verify(mapper, times(2)).map2((Card) any());
    }

    @Test
    void assignCardToAccount_WhenBothExist_ShouldUpdateCard() {
        // Arrange
        when(accountRepository.findByEmail(testEmail)).thenReturn(Optional.of(testAccountEntity));
        when(cardRepository.findByUID(testUID)).thenReturn(Optional.of(testCardEntity));
        when(mapper.map2(testCardEntity)).thenReturn(testCard);

        // Act
        Card result = cardService.assignCardToAccount(testUID, testEmail);

        // Assert
        assertEquals(testAccount, result.getAccount());
        assertNotNull(testCardEntity.getUpdatedAt());
        verify(cardRepository).save(testCardEntity);
    }

    @Test
    void assignCardToAccount_WhenAccountMissing_ShouldNoop() {
        // Arrange
        when(accountRepository.findByEmail(testEmail)).thenReturn(Optional.empty());

        // Act
        Card result = cardService.assignCardToAccount(testUID, testEmail);

        // Assert
        assertNull(result);
        verify(cardRepository, never()).save(any());
    }

    @Test
    void changeCardStatus_ShouldUpdateStatusAndTimestamp() {
        // Arrange
        when(cardRepository.findByUID(testUID)).thenReturn(Optional.of(testCardEntity));
        when(mapper.map2(testCardEntity)).thenReturn(testCard);

        // Act
        Card result = cardService.changeCardStatus(testUID, Card.CardStatus.DEACTIVATED);

        // Assert
        assertEquals(Card.CardStatus.DEACTIVATED, result.getStatus());
        assertNotNull(testCardEntity.getUpdatedAt());
        verify(cardRepository).save(testCardEntity);
    }

    @Test
    void getCardsByLastUpdated_ShouldReturnPagedResults() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<CardEntity> entities = Collections.singletonList(testCardEntity);
        when(cardRepository.findByUpdatedAtGreaterThanEqual(any(LocalDateTime.class), any(Pageable.class)))
            .thenReturn((List<CardEntity>) new PageImpl<>(entities, pageable, 1));
        when(mapper.map2(any(CardEntity.class))).thenReturn(testCard);

        // Act
        List<Card> results = cardService.getCardsByLastUpdated(testTime, 1, 10);

        // Assert
        assertEquals(1, results.size());
        verify(cardRepository).findByUpdatedAtGreaterThanEqual(testTime, pageable);
    }
}