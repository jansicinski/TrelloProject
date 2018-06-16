package com.janek.TrelloProject.RestControllers.Api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrelloApiListControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldGetAllMyLists() {
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .log().all();

        Response when = given
                .when()
                .get("TrelloApi/lists/");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(containsString("5a54de08b62cff803bd7f42e=5a54de08b62cff803bd7f42b"));
        //@formatter:on
    }

    @Test
    public void shouldSaveAllListsToDb() {
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .log().all();

        Response when = given
                .when()
                .post("TrelloApi/lists/saveAllToDb");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("listId", hasItems("5a54dc1ec9b64db92e6d23ca", "5a54dc1ec9b64db92e6d23cb", "5a54de08b62cff803bd7f42c"));
        //@formatter:on
    }

}