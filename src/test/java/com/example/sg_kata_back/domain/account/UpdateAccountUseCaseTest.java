package com.example.sg_kata_back.domain.account;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateAccountUseCaseTest {

    final String accountId = "accountId";

    @Mock
    AccountUpdate accountUpdate;

    @InjectMocks
    UpdateAccountUseCase updateAccountUseCase;

     @Test
    void should_withdraw_method_correctly_called() {
        // Given
        final AccountBalance mockAccountBalance = new AccountBalance(UUID.randomUUID().toString(), new BigDecimal(200), AccountCurrency.EUR);
        when(accountUpdate.withdraw(accountId, new BigDecimal(100))).thenReturn(mockAccountBalance);

        // When
        updateAccountUseCase.withdraw(accountId, new BigDecimal(100));

        // Then

        verify(accountUpdate, times(1)).withdraw(accountId, new BigDecimal(100));
    }


     @Test
    void should_deposit_method_correctly_called() {
        // Given
        final AccountBalance mockAccountBalance = new AccountBalance(UUID.randomUUID().toString(), new BigDecimal(200), AccountCurrency.EUR);
        when(accountUpdate.deposit(accountId, new BigDecimal(100))).thenReturn(mockAccountBalance);

        // When
        updateAccountUseCase.deposit(accountId, new BigDecimal(100));

        // Then
        verify(accountUpdate, times(1)).deposit(accountId, new BigDecimal(100));
    }
}
