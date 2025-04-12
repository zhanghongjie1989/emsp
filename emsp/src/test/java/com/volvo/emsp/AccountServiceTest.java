package com.volvo.emsp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount_ShouldReturnAccountResponse_WhenRequestIsValid() {
        // Arrange
        AccountRequest request = new AccountRequest("test@example.com", "CON123");
        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setContractId(request.getContractId());
        
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        AccountResponse response = accountService.createAccount(request);

        // Assert
        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void createAccount_ShouldThrowException_WhenEmailExists() {
        // Arrange
        AccountRequest request = new AccountRequest("existing@example.com", "CON123");
        when(accountRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateAccountException.class, () -> {
            accountService.createAccount(request);
        });
    }
}