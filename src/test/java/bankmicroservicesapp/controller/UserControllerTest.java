package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.UserDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/testDb.sql")
@Sql("/addTestData.sql")
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userGetByIdTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId("7d80f158-2eff-4328-9921-0792706fe2d5");
        userDto.setTaxCode("IT678901234");
        userDto.setFirstName("Marco");
        userDto.setLastName("Ferrari");
        userDto.setEmail("marco.ferrari@gmail.com");
        userDto.setUserPassword("123123123123");
        userDto.setAddress("901 Via Roma, Turin, Italy");
        userDto.setPhone("+39 678 901 2345");

        String userStringDto = objectMapper.writeValueAsString(userDto);

        MvcResult mockResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/7d80f158-2eff-4328-9921-0792706fe2d5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(userStringDto))
                .andReturn();

        UserDto actual = objectMapper.readValue(mockResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        Assertions.assertEquals(200, mockResult.getResponse().getStatus());
        Assertions.assertEquals(actual, userDto);
    }
}