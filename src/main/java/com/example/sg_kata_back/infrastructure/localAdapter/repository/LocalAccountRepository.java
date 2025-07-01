package com.example.sg_kata_back.infrastructure.localAdapter.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountTransaction;

@Component
public class LocalAccountRepository {

    public List<AccountTransaction> transactions = List.of(
            AccountTransaction.builder().type("deposit")
                    .amount(new BigDecimal(100)).dateTime(LocalDateTime.now()).id("id1").build());

    public AccountStatement accountStatement = AccountStatement.builder().status("positive").transactions(transactions)
            .build();

    public AccountBalance accountBalance = AccountBalance.builder().currency("EUR").amount(new BigDecimal(100)).build();

    public AccountBalance deposit(BigDecimal amount) {
        BigDecimal updatedAmount = accountBalance.amount().add(amount);
        accountBalance.withAmount(updatedAmount);
        return accountBalance;
    }

    public AccountBalance withdraw(BigDecimal amount) {
        BigDecimal updatedAmount = accountBalance.amount().subtract(amount);
        accountBalance.withAmount(updatedAmount);
        return accountBalance;
    }
}
