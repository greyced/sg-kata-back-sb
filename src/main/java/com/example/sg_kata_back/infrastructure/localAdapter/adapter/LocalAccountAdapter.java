package com.example.sg_kata_back.infrastructure.localAdapter.adapter;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountFetch;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountUpdate;
import com.example.sg_kata_back.infrastructure.localAdapter.repository.LocalAccountRepository;

@Component
public class LocalAccountAdapter implements AccountFetch, AccountUpdate {

    private final LocalAccountRepository localAccountRepository;

    public LocalAccountAdapter(LocalAccountRepository localAccountRepository) {
        this.localAccountRepository = localAccountRepository;
    }

    @Override
    public AccountStatement fetchAccountStatement(String accountId) {
        return localAccountRepository.accountStatement;
    }

    @Override
    public AccountBalance fetchAccountBalance(String accountId) {
        return localAccountRepository.accountBalance;
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
