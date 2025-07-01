package com.example.sg_kata_back.domain.account;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountUseCaseTest {

    final String accountId = "accountId";


    @Mock
    AccountFetch accountFetch;

    @InjectMocks
    AccountUseCase accountUseCase;

    @Test
    void should_return_expected_balance_account() {
        // Given
        final AccountBalance mockAccountBalance = new AccountBalance(UUID.randomUUID().toString(), new BigDecimal(300), AccountCurrency.EUR);
        when(accountFetch.fetchAccountBalance(accountId)).thenReturn(mockAccountBalance);

        // When
        final AccountBalance accountBalance = accountUseCase.retrieveBalanceByAccountId(accountId);

        // Then

        Assertions.assertThat(accountBalance.amount()).isEqualTo( new BigDecimal(300));
    }

     @Test
    void should_return_expected_statement_account() {
        // Given

        final List<AccountTransaction> mockTransactions = List.of(
            new AccountTransaction(UUID.randomUUID().toString(), AccountTransactionType.DEPOSIT, LocalDateTime.now(), new BigDecimal(200)),
             new AccountTransaction(UUID.randomUUID().toString(), AccountTransactionType.DEPOSIT, LocalDateTime.now(), new BigDecimal(100)));

        final AccountStatement mockAccountStatement = new AccountStatement(accountId, mockTransactions);

        when(accountFetch.fetchAccountStatement(accountId)).thenReturn(mockAccountStatement);

        // When
        final AccountStatement accountStatement = accountUseCase.findStatementByAccountId(accountId);

        // Then
        Assertions.assertThat(accountStatement.getTransactions().size()).isEqualTo( 2);
    }

}
