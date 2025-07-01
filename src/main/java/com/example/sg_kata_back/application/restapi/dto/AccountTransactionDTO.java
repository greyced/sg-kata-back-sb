package com.example.sg_kata_back.application.restapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record AccountTransactionDTO(BigDecimal amount, LocalDateTime date, String type) {

}
