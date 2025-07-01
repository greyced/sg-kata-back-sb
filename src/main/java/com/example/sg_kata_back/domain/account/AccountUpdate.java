package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;

public interface AccountUpdate {
    AccountBalance deposit(String accountId, BigDecimal amount);
    AccountBalance withdraw(String accountId, BigDecimal amount);
}
