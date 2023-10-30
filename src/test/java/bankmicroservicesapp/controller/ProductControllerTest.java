package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.ProductDto;
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
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void updateProductTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setProductType("PersonalLoans");

        String prodDtoString = objectMapper.writeValueAsString(productDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/product/update/60587cf0-aaa3-475a-8ef8-aa452c6e8fe9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(prodDtoString))
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product/get/60587cf0-aaa3-475a-8ef8-aa452c6e8fe9")
                        .with(csrf()))
                .andReturn();

        ProductDto prodDtoString2 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        ProductDto productDto1 = new ProductDto();
        productDto1.setId("60587cf0-aaa3-475a-8ef8-aa452c6e8fe9");
        productDto1.setProductType("PersonalLoans");
        productDto1.setProductStatus("Available");
        productDto1.setInterestRate("2.99");

        Assertions.assertEquals(productDto1, prodDtoString2);
    }

    @Test
    void findProductAgreementStatus() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setId("60587cf0-aaa3-475a-8ef8-aa452c6e8fe9");
        productDto.setProductType("HealthInsurance");
        productDto.setProductStatus("Available");
        productDto.setInterestRate("2.99");

        productDtos.add(productDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/product/get/interestRate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .param("quantityAgreement", "2.0"))
                .andReturn();

        String productResultJson = mvcResult.getResponse().getContentAsString();
        List<ProductDto> actual = objectMapper.readValue(productResultJson, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(actual, productDtos);

    }
}