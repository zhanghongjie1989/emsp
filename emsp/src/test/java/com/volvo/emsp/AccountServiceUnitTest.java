package com.volvo.emsp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.volvo.emsp.domain.Account;
import com.volvo.emsp.reposity.AccountRepository;
import com.volvo.emsp.reposity.entity.AccountEntity;
import com.volvo.emsp.reposity.mapper.Account2EntitySwitchMapper;
import com.volvo.emsp.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Account2EntitySwitchMapper mapper;

    @InjectMocks
    private AccountService accountService;

    private Account testAccount;
    private AccountEntity testAccountEntity;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setEmail("test@example.com");
        testAccount.setStatus(Account.AccountStatus.CREATED);
        testAccount.setContractId("DE-ABC-123456789-0");

        testAccountEntity = new AccountEntity();
        testAccountEntity.setEmail("test@example.com");
    }

    @Test
    void createAccount_ShouldSuccess_WhenEmailNotExists() {
        // Arrange
        when(accountRepository.findByEmail(testAccount.getEmail())).thenReturn(Optional.empty());
        when(mapper.map2(testAccount)).thenReturn(testAccountEntity);
        when(mapper.map2(testAccountEntity)).thenReturn(testAccount);
        when(accountRepository.save(testAccountEntity)).thenReturn(testAccountEntity);

        // Act
        Account result = accountService.createAccount(testAccount);

        // Assert
        assertNotNull(result);
        assertEquals(testAccount.getEmail(), result.getEmail());
        assertEquals(Account.AccountStatus.CREATED, result.getStatus());
        assertNotNull(result.getContractId());

        verify(accountRepository).findByEmail(testAccount.getEmail());
        verify(accountRepository).save(testAccountEntity);
        verify(mapper, times(2)).map2((Account) any());
    }

    @Test
    void createAccount_ShouldThrow_WhenEmailExists() {
        // Arrange
        when(accountRepository.findByEmail(testAccount.getEmail())).thenReturn(Optional.of(testAccountEntity));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> accountService.createAccount(testAccount));
        
        assertEquals("Account with this email already exists.", exception.getMessage());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void changeAccountStatus_ShouldSuccess_WhenAccountExists() {
        // Arrange
        Account.AccountStatus newStatus = Account.AccountStatus.ACTIVATED;
        when(accountRepository.findByEmail(testAccount.getEmail())).thenReturn(Optional.of(testAccountEntity));
        when(mapper.map2(testAccountEntity)).thenReturn(testAccount);
        when(mapper.map2(testAccount)).thenReturn(testAccountEntity);
        when(accountRepository.save(testAccountEntity)).thenReturn(testAccountEntity);

        // Act
        Account result = accountService.changeAccountStatus(testAccount.getEmail(), newStatus);

        // Assert
        assertNotNull(result);
        assertEquals(newStatus, result.getStatus());
        assertNotNull(result.getUpdatedAt());

        verify(accountRepository).findByEmail(testAccount.getEmail());
        verify(accountRepository).save(testAccountEntity);
    }

    @Test
    void changeAccountStatus_ShouldThrow_WhenAccountNotExists() {
        // Arrange
        String nonExistingEmail = "nonexisting@example.com";
        when(accountRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> accountService.changeAccountStatus(nonExistingEmail, Account.AccountStatus.ACTIVATED));
        
        assertEquals("Account not found", exception.getMessage());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void getAccountsByLastUpdated_ShouldReturnPagedResults() {
        // Arrange
        LocalDateTime lastUpdated = LocalDateTime.now().minusDays(1);
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        
        Page<AccountEntity> entityPage = new PageImpl<>(List.of(testAccountEntity), pageable, 1);
        when(accountRepository.findByUpdatedAt(lastUpdated, pageable)).thenReturn((List<AccountEntity>) entityPage);
        when(mapper.map2(testAccountEntity)).thenReturn(testAccount);

        // Act
        List<Account> result = accountService.getAccountsByLastUpdated(lastUpdated, page, size);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testAccount.getEmail(), result.get(0).getEmail());
        
        verify(accountRepository).findByUpdatedAt(lastUpdated, pageable);
        verify(mapper).map2(testAccountEntity);
    }
}