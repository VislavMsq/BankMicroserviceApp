package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.TransactionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
//        List<TransactionDto> transactionDtoList = new ArrayList<>();
//        TransactionDto transactionDto = new TransactionDto();
//        transactionDto.setId();
//        transactionDto.setType();
//        transactionDto.setAmount();
//        transactionDto.setDescription();
//        transactionDto.setDebitAccountId();
//        transactionDto.setCreditAccountId();
//
//        transactionDtoList.add(transactionDto);
//        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/all")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(csrf()))
//                .andReturn();

    }
}