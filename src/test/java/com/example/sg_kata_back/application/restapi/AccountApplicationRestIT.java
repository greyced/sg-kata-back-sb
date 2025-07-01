package com.example.sg_kata_back.application.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.sg_kata_back.SgKataBackApplication;
import com.example.sg_kata_back.application.restapi.dto.AccountBalanceDTO;
import com.example.sg_kata_back.application.restapi.dto.AccountStatementDTO;
import com.example.sg_kata_back.infrastructure.account_in_memory_adapter.repository.LocalAccountRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SgKataBackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountApplicationRestIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private LocalAccountRepository localAccountRepository;

    @BeforeEach
    public void beforeEach() {
        localAccountRepository.reset();
    }

    @Test
    public void testRetrieveBalance() {
        assertEquals(restTemplate
                .getForObject(createURLWithPort("/api/account/accountId/balance"), AccountBalanceDTO.class).amount(),
                new BigDecimal(100));
    }

    @Test
    public void testAccountStatementBalance() {
        assertEquals(restTemplate
                .getForObject(createURLWithPort("/api/account/accountId/statement"), AccountStatementDTO.class)
                .getTransactions().size(), 1);
    }

    @Test
    public void as_a_client_bank_if_i_deposit_100_with_an_existing_balance_100_new_balance_is_equal_200() {
        assertEquals(restTemplate
                .postForObject(createURLWithPort("/api/account/accountId/deposit"), new BigDecimal(100),
                        AccountBalanceDTO.class)
                .amount(), new BigDecimal(200));
    }

    @Test
    public void as_a_client_bank_if_i_withdraw_50_with_an_existing_balance_100_new_balance_is_equal_50() {
        assertEquals(restTemplate
                .postForObject(createURLWithPort("/api/account/accountId/withdraw"), new BigDecimal(50),
                        AccountBalanceDTO.class)
                .amount(), new BigDecimal(50));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
