package com.bankmicroservicesapp.controller;

import com.bankmicroservicesapp.dto.AccountDto;
import com.bankmicroservicesapp.entity.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/testDb.sql")
@Sql("/addTestData.sql")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAccount() throws Exception {

        AccountDto accountDto = new AccountDto();
        accountDto.setUserId("7d80f158-2eff-4328-9921-0792706fe2d5");
        accountDto.setName("John.Jonson");
        accountDto.setType("Business");
        accountDto.setStatus("New");
        accountDto.setCurrencyCode("EUR");
        accountDto.setBankRating("5");
        accountDto.setBalance("350.00");

        String accountStringDto = objectMapper.writeValueAsString(accountDto);
        // when
        MvcResult accountCreatingResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(accountStringDto))
                .andReturn();
        // then
        Assertions.assertEquals(200, accountCreatingResult.getResponse().getStatus());

        String accountResultJson = accountCreatingResult.getResponse().getContentAsString();
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);

        Assertions.assertEquals(accountDto.getName(), accountResult.getName());
        Assertions.assertEquals(accountDto.getType(), accountResult.getType().toString());
        Assertions.assertEquals(accountDto.getStatus(), accountResult.getStatus().toString());
        Assertions.assertEquals(accountDto.getCurrencyCode(), accountResult.getCurrencyCode().toString());
        Assertions.assertEquals(accountDto.getBankRating(), String.valueOf(accountResult.getBankRating()));
        Assertions.assertEquals(BigDecimal.valueOf(Double.parseDouble(accountDto.getBalance())), accountResult.getBalance());

    }

    @Test
    void getAllByStatusTest() throws Exception {

        List<AccountDto> expected = new ArrayList<>();
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId("05ebe134-0d14-4675-99ef-d07da2b2212f");
        accountDto.setName("John.Smith");
        accountDto.setType("Personal");
        accountDto.setStatus("Active");
        accountDto.setCurrencyCode("USD");
        accountDto.setBankRating("5");
        accountDto.setBalance("12250.00");

        expected.add(accountDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/get-all/by-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("status", "Active"))
                .andReturn();

        String accountResultJson = mvcResult.getResponse().getContentAsString();

        List<AccountDto> actual = objectMapper.readValue(accountResultJson, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getIdTest() throws Exception {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId("05ebe134-0d14-4675-99ef-d07da2b2212f");
        accountDto.setName("John.Smith");
        accountDto.setType("Personal");
        accountDto.setStatus("Active");
        accountDto.setCurrencyCode("USD");
        accountDto.setBankRating("5");
        accountDto.setBalance("12250.00");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/get/by-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("id", "11ebe124-0d14-4675-99ef-d07da2b2222a"))
                .andReturn();

        String accountResultJson = mvcResult.getResponse().getContentAsString();

        AccountDto actual = objectMapper.readValue(accountResultJson, new TypeReference<>() {
        });
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(accountDto, actual);
    }
}