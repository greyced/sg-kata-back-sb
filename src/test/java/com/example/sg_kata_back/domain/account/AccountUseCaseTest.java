package com.example.sg_kata_back.domain.account;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    void should_return_balance_account() {
        // Given
        final AccountBalance mockAccountBalance = AccountBalance.builder().amount( new BigDecimal(300)).build();
        when(accountFetch.fetchAccountBalance(accountId)).thenReturn(mockAccountBalance);

        // When
        final AccountBalance accountBalance = accountUseCase.retrieveBalance(accountId);

        // Then

        Assertions.assertThat(accountBalance.amount()).isEqualTo( new BigDecimal(300));
    }

     @Test
    void should_return_statement_account() {
        // Given

        final List<AccountTransaction> mockTransactions = List.of(
            AccountTransaction.builder().type("deposit")
                    .amount(new BigDecimal(100)).dateTime(LocalDateTime.now()).id("id1").build(), AccountTransaction.builder().type("deposit")
                    .amount(new BigDecimal(200)).dateTime(LocalDateTime.now()).id("id2").build());

        final AccountStatement mockAccountStatement = AccountStatement.builder().status("POSITIVE").transactions(mockTransactions).build();

        when(accountFetch.fetchAccountStatement(accountId)).thenReturn(mockAccountStatement);

        // When
        final AccountStatement accountStatement = accountUseCase.retrieveStatement(accountId);

        // Then
        Assertions.assertThat(accountStatement.transactions().size()).isEqualTo( 2);
    }

}
