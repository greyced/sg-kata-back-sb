package com.example.sg_kata_back.application.restapi.mapper;

import java.util.List;

import com.example.sg_kata_back.application.restapi.dto.AccountBalanceDTO;
import com.example.sg_kata_back.application.restapi.dto.AccountStatementDTO;
import com.example.sg_kata_back.application.restapi.dto.AccountTransactionDTO;
import com.example.sg_kata_back.domain.account.AccountBalance;
import com.example.sg_kata_back.domain.account.AccountStatement;
import com.example.sg_kata_back.domain.account.AccountTransaction;

public interface AccountMapper {
    static AccountBalanceDTO fromBalanceDomainToDto(AccountBalance accountBalance) {
        return AccountBalanceDTO.builder().amount(accountBalance.amount())
                .currency(accountBalance.currency().toString()).build();
    }

    static AccountStatementDTO fromStatementDomainToDto(AccountStatement accountStatement) {
        return AccountStatementDTO.builder().status(accountStatement.getStatus().toString())
                .transactions(mapTransactionDomainToDto(accountStatement.getTransactions())).build();
    }

    private static List<AccountTransactionDTO> mapTransactionDomainToDto(List<AccountTransaction> transactions) {
        return transactions.stream()
                .map(c -> AccountTransactionDTO.builder().type(c.type().toString()).amount(c.amount())
                        .date(c.dateTime()).build())
                .toList();
    }
}
