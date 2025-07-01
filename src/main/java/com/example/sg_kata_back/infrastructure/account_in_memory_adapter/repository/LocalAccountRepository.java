package com.example.sg_kata_back.infrastructure.account_in_memory_adapter.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountCurrency;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountTransaction;
import com.example.sg_kata_back.domain.account.AccountTransactionType;

@Component
public class LocalAccountRepository {

        public LocalAccountRepository() {
                this.initStatement();
        }

        private final AccountTransaction initialTransaction = new AccountTransaction(UUID.randomUUID().toString(),
                        AccountTransactionType.DEPOSIT, LocalDateTime.now(), new BigDecimal(100));

        public List<AccountTransaction> transactions = new ArrayList<>();

        public AccountBalance deposit(BigDecimal amount) {
                AccountTransaction deposit = new AccountTransaction(UUID.randomUUID().toString(), AccountTransactionType.DEPOSIT,
                                LocalDateTime.now(), amount);
                transactions.add(deposit);
                return new AccountBalance(UUID.randomUUID().toString(), computeBalanceAmount(transactions), AccountCurrency.EUR);
        }

        public AccountBalance withdraw(BigDecimal amount) {
                AccountTransaction withdraw = new AccountTransaction(UUID.randomUUID().toString(), AccountTransactionType.WITHDRAW,
                                LocalDateTime.now(), amount);
                transactions.add(withdraw);
                return new AccountBalance(UUID.randomUUID().toString(), computeBalanceAmount(transactions), AccountCurrency.EUR);
        }

        public AccountBalance getBalance(String accountId) {
                return new AccountBalance(UUID.randomUUID().toString() + accountId, computeBalanceAmount(transactions), AccountCurrency.EUR);
        }

        public AccountStatement getStatement(String accountId) {
                return new AccountStatement(UUID.randomUUID().toString() + accountId, transactions);
        }

        private void initStatement() {
                this.transactions.add(initialTransaction);
        }

        private BigDecimal computeBalanceAmount(List<AccountTransaction> transactions) {
                BigDecimal totalDeposit = transactions.stream()
                                .filter(c -> c.type().equals(AccountTransactionType.DEPOSIT))
                                .map(c -> c.amount())
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                BigDecimal totalWithDraw = transactions.stream()
                                .filter(c -> c.type().equals(AccountTransactionType.WITHDRAW))
                                .map(c -> c.amount())
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
                return totalDeposit.subtract(totalWithDraw);
        }
}
