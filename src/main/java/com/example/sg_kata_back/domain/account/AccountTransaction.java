package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record AccountTransaction(String id, AccountTransactionType type, LocalDateTime dateTime, BigDecimal amount) {

}
