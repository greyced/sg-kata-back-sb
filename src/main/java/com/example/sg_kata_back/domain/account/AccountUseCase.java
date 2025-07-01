package com.example.sg_kata_back.domain.account;

public class AccountUseCase {

    private final AccountFetch accountFetch;

    public AccountUseCase(AccountFetch accountFetch) {
        this.accountFetch = accountFetch;
    }

    public AccountStatement findStatementByAccountId(String accountId) {
        return this.accountFetch.fetchAccountStatement(accountId);
    }

    public AccountBalance retrieveBalanceByAccountId(String accountId) {
        return this.accountFetch.fetchAccountBalance(accountId);
    }

}
