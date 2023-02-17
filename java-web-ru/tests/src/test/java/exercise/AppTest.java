package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static exercise.AppTest.TestData.*;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void setUp() {
        app = App.getApp();
        app.start(0);
        baseUrl = "http://localhost:" + app.port();
    }

    @AfterAll
    static void shutDown() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUser() {
        HttpResponse<String> responseForValidUser = Unirest
                .post(baseUrl + "/users")
                .field("firstName", validFirstName)
                .field("lastName", validLastName)
                .field("email", validEmail)
                .field("password", validPassword)
                .asString();

        assertThat(responseForValidUser.getStatus()).isEqualTo(302);

        User validUser = new QUser()
                .firstName.equalTo(validFirstName)
                .findOne();
        assertThat(validUser).isNotNull();
        assertThat(validUser.getLastName()).isEqualTo(validLastName);
        assertThat(validUser.getEmail()).isEqualTo(validEmail);
        assertThat(validUser.getPassword()).isEqualTo(validPassword);

        HttpResponse<String> responseForInvalidUser = Unirest
                .post(baseUrl + "/users")
                .field("firstName", validFirstName)
                .field("lastName", validLastName)
                .field("email", invalidEmail)
                .field("password", validPassword)
                .asString();

        assertThat(responseForInvalidUser.getStatus()).isEqualTo(422);

        String body = responseForInvalidUser.getBody();
        assertThat(body).contains(invalidEmail);
        assertThat(body).contains("Должно быть валидным email");

        User invalidUser = new QUser()
                .email.equalTo(invalidEmail)
                .findOne();
        assertThat(invalidUser).isNull();

    }

    interface TestData {
        String validFirstName = "new firstName";
        String validLastName = "new lastName";
        String validEmail = "email@mail.net";
        String invalidEmail = "invalid-email";
        String validPassword = "validpassword";



    }
    
    // END
}
