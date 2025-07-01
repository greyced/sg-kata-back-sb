package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;

public class UpdateAccountUseCase {
    private final AccountUpdate accountUpdate;

    public UpdateAccountUseCase(AccountUpdate accountUpdate) {
        this.accountUpdate = accountUpdate;
    }

    public AccountBalance withdraw(String accountId, BigDecimal amount) {
        // to do : implement validator
        return this.accountUpdate.withdraw(accountId, amount);
    }

    public AccountBalance deposit(String accountId, BigDecimal amount) {
         // to do : implement validator
        return this.accountUpdate.deposit(accountId, amount);
    }

}
