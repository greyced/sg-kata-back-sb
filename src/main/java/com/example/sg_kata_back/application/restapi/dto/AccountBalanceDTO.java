package com.example.sg_kata_back.application.restapi.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record AccountBalanceDTO(BigDecimal amount, String currency) {

}
