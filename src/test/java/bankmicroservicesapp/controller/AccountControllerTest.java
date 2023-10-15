package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AccountDto;
import bankmicroservicesapp.entity.Account;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/testDb.sql")
@Sql("/addTestData.sql")
// создание тестовой базы и ее зачисты - аннатация
class AccountControllerTest {
    //
    @Autowired
    private MockMvc mockMvc; // мок для тестирования запросов
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

        MvcResult accountCreatingResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON) // в каком формате вернет(или положит?) значение наш контроллер
                        .with(csrf())               // ?
                        .content(accountStringDto)) // кладем, что принимает контроллер
                .andReturn();                       // возвращает ответ из контроллера

        // достал статус код
//      Assertions.assertEquals(201,accountCreatingResult.getResponse().getStatus()); // переделать на 201 через респонс
        Assertions.assertEquals(200, accountCreatingResult.getResponse().getStatus());

        String accountResultJson = accountCreatingResult.getResponse().getContentAsString(); // достали из результата запроса на 54, что он должен врентуть
        Account accountResult = objectMapper.readValue(accountResultJson, Account.class); // конвертирует ответ с 54 к классу аккаунт

        Assertions.assertEquals(accountDto.getName(), accountResult.getName()); // сравниваю поля
        Assertions.assertEquals(accountDto.getType(), accountResult.getType().toString());
        Assertions.assertEquals(accountDto.getStatus(), accountResult.getStatus().toString());
        Assertions.assertEquals(accountDto.getCurrencyCode(), accountResult.getCurrencyCode().toString());
        Assertions.assertEquals(accountDto.getBankRating(), accountResult.getCurrencyCode().toString());
        Assertions.assertEquals(Double.parseDouble(accountDto.getBalance()), accountResult.getBalance());


    }
}