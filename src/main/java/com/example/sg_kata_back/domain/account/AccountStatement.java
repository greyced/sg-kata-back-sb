package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountStatement {
    private final String id;
    private final AccountStatementStatus status;
    private final List<AccountTransaction> transactions;
    private final AccountBalance accountBalance;

    public AccountStatement(String id, List<AccountTransaction> transactions) {
        this.id = id;
        this.transactions = transactions;
        this.accountBalance = new AccountBalance("newId", computeBalanceAmount(transactions), AccountCurrency.EUR);
        this.status = this.accountBalance.amount().compareTo(BigDecimal.ZERO) > 0 ? AccountStatementStatus.CREDITOR
                : AccountStatementStatus.DEBTOR;
    }

    public String getId() {
        return id;
    }

    public AccountStatementStatus getStatus() {
        return status;
    }

    public List<AccountTransaction> getTransactions() {
        return transactions;
    }

    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    private BigDecimal computeBalanceAmount(List<AccountTransaction> transactions) {
        BigDecimal totalDeposit = transactions.stream().filter(c -> c.type().equals(AccountTransactionType.DEPOSIT))
                .map(c -> c.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalWithDraw = transactions.stream().filter(c -> c.type().equals(AccountTransactionType.WITHDRAW))
                .map(c -> c.amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalDeposit.subtract(totalWithDraw);
    }

}
