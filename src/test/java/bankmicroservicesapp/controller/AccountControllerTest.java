package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
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
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;           // мок для тестирования запросов
    @Autowired
    private ObjectMapper objectMapper; // конвертор в джон

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

        String accountStringDto = objectMapper.writeValueAsString(accountDto); // convent v json
        // when
        MvcResult accountCreatingResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON) // в каком формате вернет(или положит?) значение наш контроллер
                        .with(csrf())
                        .content(accountStringDto)) // кладем, что принимает контроллер
                .andReturn();                       // возвращает ответ из контроллера
        // then
        // достал статус код
        Assertions.assertEquals(200, accountCreatingResult.getResponse().getStatus());

        String accountResultJson = accountCreatingResult.getResponse().getContentAsString(); // достали стригну
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class);    // сконвертировали строку в дсон

        Assertions.assertEquals(accountDto.getName(), accountResult.getName());              // сравниваю поля
        Assertions.assertEquals(accountDto.getType(), accountResult.getType().toString());
        Assertions.assertEquals(accountDto.getStatus(), accountResult.getStatus().toString());
        Assertions.assertEquals(accountDto.getCurrencyCode(), accountResult.getCurrencyCode().toString());
        Assertions.assertEquals(accountDto.getBankRating(), String.valueOf(accountResult.getBankRating()));
        Assertions.assertEquals(Double.parseDouble(accountDto.getBalance()), accountResult.getBalance());

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
        accountDto.setBalance("12250.0");

        expected.add(accountDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/get-all/by-status")
                        .contentType(MediaType.APPLICATION_JSON) //  каком формате мы будем возвращать
                        .with(csrf())
                        .param("status", "Active"))//todo как положить через анатацию реквест парам в боди
                .andReturn();

        String accountResultJson = mvcResult.getResponse().getContentAsString();     // достали стригну

        List<AccountDto> actual = objectMapper.readValue(accountResultJson, new TypeReference<>() {
        });

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus()); // ++
        Assertions.assertEquals(expected, actual);


    }
}