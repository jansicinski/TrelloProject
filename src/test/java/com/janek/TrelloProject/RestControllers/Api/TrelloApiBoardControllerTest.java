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
import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrelloApiBoardControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldGetAllMyBoards(){
        // TODO: 6/2/2018 TrelloApiControllerTest shouldReturnMyBoards, WireMock?
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .log().all();

        Response when = given
                .when()
                .get("TrelloApi/boards/");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItems("x", "TRNTL", "Przyjaciel SME", "Welcome Board", "Members", "Notes - devs only"));
        //@formatter:on
    }

    @Test
    public void shouldSaveAllBoardsToDb(){
        //@formatter:off
        RequestSpecification given = given()
                .port(port)
                .log().all();

        Response when = given
                .when()
                .post("TrelloApi/boards/saveAllToDb");

        when.then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("name", hasItems("x", "TRNTL", "Przyjaciel SME", "Welcome Board", "Members", "Notes - devs only"));
        //@formatter:on
    }

}