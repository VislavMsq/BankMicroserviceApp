package com.bankmicroservicesapp.controller;

import com.bankmicroservicesapp.dto.AgreementDto;
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
import java.util.Map;

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
    void deleteById() throws Exception {

        MvcResult mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.delete("/agreement/delete/a242f83f-a341-45f4-9430-e43cfbf55361")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andReturn();

        Assertions.assertEquals(200, mockMvc1.getResponse().getStatus());

        mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/get/a242f83f-a341-45f4-9430-e43cfbf55361")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andReturn();

        Map<String, Object> objectMap = objectMapper.readValue(mockMvc1.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertEquals("NOT_FOUND", objectMap.get("statusCode").toString());
    }

    @Test
    void findAgreementManagerTest() throws Exception {
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

        MvcResult mockMvc1 = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/get/by-manager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("id", "5b92d682-015a-4a12-9e25-33daa97ebdec"))
                .andReturn();

        String agreementResultJson = mockMvc1.getResponse().getContentAsString();
        List<AgreementDto> actual = objectMapper.readValue(agreementResultJson, new TypeReference<>() {
        });


        Assertions.assertEquals(200, mockMvc1.getResponse().getStatus());
        Assertions.assertEquals(actual, expectedAgreementList);
    }

    @Test
    void findAgreementsClientIdIsTest() throws Exception {
        List<AgreementDto> agreementDtos = new ArrayList<>();
        AgreementDto agreementDto = new AgreementDto();
        agreementDto.setProductName("HealthInsurance");
        agreementDto.setUserId("05ebe134-0d14-4675-99ef-d07da2b2212f");
        agreementDto.setInterestRate("10.2");
        agreementDto.setCurrencyCode("USD");
        agreementDto.setStatus("Completed");
        agreementDto.setDiscount("0.0");
        agreementDto.setAgreementLimit("5000.0");
        agreementDto.setSum("500.0");

        agreementDtos.add(agreementDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/agreement/get/by-client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("id", "05ebe134-0d14-4675-99ef-d07da2b2212f"))
                .andReturn();

        String agreementsJsonResult = mvcResult.getResponse().getContentAsString();

        List<AgreementDto> actual = objectMapper.readValue(agreementsJsonResult, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(actual, agreementDtos);
    }
}