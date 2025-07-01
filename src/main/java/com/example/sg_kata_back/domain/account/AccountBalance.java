package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;

public record AccountBalance(String id, BigDecimal amount, AccountCurrency currency) {
}
