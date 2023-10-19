package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AgreementDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
class AgreementControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Test
    void deleteById() {

    }

    @Test
    void findAgreementManager() throws Exception {
        List<AgreementDto> expectedAgreementList = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setProductName("HealthInsurance");
        agreementDto.setUserId("fca81f38-1f36-4b02-a646-d066458da668");
        agreementDto.setInterestRate("10.2000");
        agreementDto.setCurrencyCode("USD");
        agreementDto.setStatus("Completed");
        agreementDto.setDiscount("0.00");
        agreementDto.setAgreementLimit("5000.00");
        agreementDto.setSum("5000.00");

        expectedAgreementList.add(agreementDto);

        String agreementStr = objectMapper.writeValueAsString(expectedAgreementList);

        MvcResult mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/managerId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(agreementStr))
                .andReturn();

        String agreementResultJson = mockMvc1.getResponse().getContentAsString();
        List<AgreementDto> actual = objectMapper.readValue(agreementResultJson, new TypeReference<>(){
        });

        Assertions.assertEquals(200,mockMvc1.getResponse().getStatus());
        Assertions.assertEquals(actual,expectedAgreementList);

    }

    @Test
    void findAgreementsClientIdIs() {
    }
}