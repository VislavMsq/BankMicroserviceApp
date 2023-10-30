package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.TransactionDto;
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
        transactionDto.setAmount("0.0");
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
}