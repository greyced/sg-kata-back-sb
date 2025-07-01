package com.example.sg_kata_back.infrastructure.localAdapter.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountTransaction;

@Component
public class LocalAccountRepository {

    public List<AccountTransaction> transactions = new ArrayList<>(List.of(
            AccountTransaction.builder().type("deposit")
                    .amount(new BigDecimal(100)).dateTime(LocalDateTime.now()).id("id1").build()));

    public AccountStatement accountStatement = AccountStatement.builder().status("positive").transactions(transactions)
            .build();

    public AccountBalance accountBalance = AccountBalance.builder().currency("EUR")
            .amount(computeBalanceAmount(transactions)).build();

    public AccountBalance deposit(BigDecimal amount) {
        this.transactions.add(AccountTransaction.builder().type("deposit").amount(amount).dateTime(LocalDateTime.now())
                .id("newId").build());
        return AccountBalance.builder().currency("EUR").amount(computeBalanceAmount(transactions)).build();
    }

    public AccountBalance withdraw(BigDecimal amount) {
        this.transactions.add(AccountTransaction.builder().type("withdraw").amount(amount).dateTime(LocalDateTime.now())
                .id("newId").build());
        return AccountBalance.builder().currency("EUR").amount(computeBalanceAmount(transactions)).build();
    }

    private BigDecimal computeBalanceAmount(List<AccountTransaction> transactions) {
        BigDecimal totalDeposit = transactions.stream().filter(c -> c.type().equals("deposit")).map(c -> c.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithDraw = transactions.stream().filter(c -> c.type().equals("withdraw")).map(c -> c.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalDeposit.subtract(totalWithDraw);
    }
}
