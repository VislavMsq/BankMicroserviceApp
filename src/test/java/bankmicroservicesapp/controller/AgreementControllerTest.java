package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AgreementDto;
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
class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deleteById() {

    }

    @Test
    void findAgreementManager() throws Exception {
        List<AgreementDto> expectedAgreementList = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setProductName("HealthInsurance");
        agreementDto.setUserId("05ebe134-0d14-4675-99ef-d07da2b2212f");
        agreementDto.setInterestRate("10.2");
        agreementDto.setCurrencyCode("USD");
        agreementDto.setStatus("Completed");
        agreementDto.setDiscount("0.0");
        agreementDto.setAgreementLimit("5000.0");
        agreementDto.setSum("500.0");

        expectedAgreementList.add(agreementDto);

        MvcResult mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/get/agreementManager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("managerId", "5b92d682-015a-4a12-9e25-33daa97ebdec"))
                .andReturn();

        String agreementResultJson = mockMvc1.getResponse().getContentAsString();
        System.out.println(agreementResultJson + "*********************************************");
        List<AgreementDto> actual = objectMapper.readValue(agreementResultJson, new TypeReference<>() {
        });


        Assertions.assertEquals(200, mockMvc1.getResponse().getStatus());
        Assertions.assertEquals(actual, expectedAgreementList);
    }

    @Test
    void findAgreementsClientIdIs() {
    }
}