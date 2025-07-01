package com.example.sg_kata_back.domain.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record AccountTransaction(String id, String type, LocalDateTime dateTime, BigDecimal amount) {

}
