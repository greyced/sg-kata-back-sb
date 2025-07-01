package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record AccountBalance(Integer id, BigDecimal amount, String currency) {
       public AccountBalance withAmount(BigDecimal amount) {
        return new AccountBalance(id(), amount, currency());
    }
}
