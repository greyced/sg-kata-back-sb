package com.example.sg_kata_back.domain.account;

import java.util.List;

import lombok.Builder;


@Builder
public record AccountStatement(Integer id, String status, List<AccountTransaction> transactions) {}
