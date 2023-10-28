package bankmicroservicesapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/dropTable.sql")
@Sql("/testDb.sql")
@Sql("/addTestData.sql")
class UserControllerTest {

    @Test
    void userGetByIdTest() {

    }
}