package com.example.sg_kata_back.application.restapi.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountStatementDTO {
    String status;
    List<AccountTransactionDTO> transactions;
}
