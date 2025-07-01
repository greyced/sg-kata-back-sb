package com.example.sg_kata_back.domain.account;

public interface AccountFetch {
    AccountStatement fetchAccountStatement(String accountId);
    AccountBalance fetchAccountBalance(String accountId);
}
