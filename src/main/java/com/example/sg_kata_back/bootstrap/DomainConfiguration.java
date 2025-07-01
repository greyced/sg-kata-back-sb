package com.example.sg_kata_back.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.sg_kata_back.domain.account.AccountFetch;
import com.example.sg_kata_back.domain.account.AccountUpdate;
import com.example.sg_kata_back.domain.account.AccountUseCase;
import com.example.sg_kata_back.domain.account.UpdateAccountUseCase;

@Configuration
public class DomainConfiguration {

    @Bean
    public AccountUseCase accountUseCase(AccountFetch accountFetch) {
        return new AccountUseCase(accountFetch);
    }

    @Bean
    public UpdateAccountUseCase UpdateAccountUseCase(AccountUpdate accountUpdate) {
        return new UpdateAccountUseCase(accountUpdate);
    }
}
