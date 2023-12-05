package com.bankmicroservicesapp.controller;

import com.bankmicroservicesapp.dto.CreateTransactionDto;
import com.bankmicroservicesapp.dto.TransactionDto;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/testDb.sql")
@Sql("/addTestData.sql")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTransactionTest() throws Exception {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId("06752bb6-809f-400a-afff-50aaf6ccd647");
        transactionDto.setType("TransferFunds");
        transactionDto.setAmount("0.00");
        transactionDto.setDescription("for cookies");
        transactionDto.setDebitAccountId("11ebe124-0d14-4675-99ef-d07da2b2222a");
        transactionDto.setCreditAccountId("11ebe124-0d14-4675-99ef-d07da2b2222a");

        transactionDtoList.add(transactionDto);

        MvcResult mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andReturn();

        String transactionResultJson = mockMvc1.getResponse().getContentAsString();

        List<TransactionDto> actual = objectMapper.readValue(transactionResultJson, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mockMvc1.getResponse().getStatus());
        Assertions.assertEquals(actual, transactionDtoList);
    }

    @Test
    void createTransaction() throws Exception {
        CreateTransactionDto createTransactionDto = new CreateTransactionDto();
        createTransactionDto.setFromAccountId("11ebe124-0d14-4675-99ef-d07da2b2222a");
        createTransactionDto.setToAccountId("91ebe124-0d14-4675-99ef-d07da2b3222a");
        createTransactionDto.setType("TransferFunds");
        createTransactionDto.setAmount("150.0");
        createTransactionDto.setDescription("for cookies");

        String data = objectMapper.writeValueAsString(createTransactionDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction/create")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andReturn();

        TransactionDto transactionDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        TransactionDto expected = new TransactionDto();
        expected.setId(transactionDto.getId());
        expected.setDebitAccountId("11ebe124-0d14-4675-99ef-d07da2b2222a");
        expected.setCreditAccountId("91ebe124-0d14-4675-99ef-d07da2b3222a");
        expected.setType("TransferFunds");
        expected.setAmount("150.0");
        expected.setDescription("for cookies");

        Assertions.assertEquals(expected, transactionDto);

    }
}