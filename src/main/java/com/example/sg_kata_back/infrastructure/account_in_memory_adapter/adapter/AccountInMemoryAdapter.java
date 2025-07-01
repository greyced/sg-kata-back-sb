package com.example.sg_kata_back.infrastructure.account_in_memory_adapter.adapter;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountFetch;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountUpdate;
import com.example.sg_kata_back.infrastructure.account_in_memory_adapter.repository.LocalAccountRepository;

@Component
public class AccountInMemoryAdapter implements AccountFetch, AccountUpdate {

    private final LocalAccountRepository localAccountRepository;

    public AccountInMemoryAdapter(LocalAccountRepository localAccountRepository) {
        this.localAccountRepository = localAccountRepository;
    }

    @Override
    public AccountStatement fetchAccountStatement(String accountId) {
        return localAccountRepository.getStatement(accountId);
    }

    @Override
    public AccountBalance fetchAccountBalance(String accountId) {
        return localAccountRepository.getBalance(accountId);
    }

    @Override
    public AccountBalance deposit(String accountId, BigDecimal amount) {
        return localAccountRepository.deposit(amount);
    }

    @Override
    public AccountBalance withdraw(String accountId, BigDecimal amount) {
        return localAccountRepository.withdraw(amount);
    }

}
