package com.example.sg_kata_back.domain.account;

public class AccountUseCase {

    private final AccountFetch accountFetch;

    public AccountUseCase(AccountFetch accountFetch) {
        this.accountFetch = accountFetch;
    }

    public AccountStatement retrieveStatement(String accountId) {
        return this.accountFetch.fetchAccountStatement(accountId);
    }

    public AccountBalance retrieveBalance(String accountId) {
        return this.accountFetch.fetchAccountBalance(accountId);
    }

}
