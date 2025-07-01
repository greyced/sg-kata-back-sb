package com.example.sg_kata_back.application.restapi.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sg_kata_back.application.restapi.dto.AccountBalanceDTO;
import com.example.sg_kata_back.application.restapi.dto.AccountStatementDTO;
import com.example.sg_kata_back.application.restapi.mapper.AccountMapper;
import com.example.sg_kata_back.domain.account.AccountUseCase;
import com.example.sg_kata_back.domain.account.UpdateAccountUseCase;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/account/{accountId}")
public class AccountController {

    private final AccountUseCase accountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    public AccountController(AccountUseCase accountUseCase, UpdateAccountUseCase updateAccountUseCase){
        this.accountUseCase = accountUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
    }

    @GetMapping("/balance")
    AccountBalanceDTO getBalanceDTO(@PathVariable String accountId) {
        return AccountMapper.fromBalanceDomainToDto(accountUseCase.retrieveBalanceByAccountId(accountId));
    }

    @GetMapping("/statement")
    AccountStatementDTO findStatement(@PathVariable String accountId) {
        return AccountMapper.fromStatementDomainToDto(accountUseCase.findStatementByAccountId(accountId));
    }

    @PostMapping("/withdraw")
    AccountBalanceDTO withdraw(@PathVariable String accountId, @RequestBody BigDecimal amount) {
        return AccountMapper.fromBalanceDomainToDto(updateAccountUseCase.withdraw(accountId, amount));
    }

    @PostMapping("/deposit")
    AccountBalanceDTO deposit(@PathVariable String accountId, @RequestBody BigDecimal amount) {
        return AccountMapper.fromBalanceDomainToDto(updateAccountUseCase.deposit(accountId, amount));
    }

}
