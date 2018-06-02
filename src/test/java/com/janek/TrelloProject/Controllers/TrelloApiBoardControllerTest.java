package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrelloApiBoardControllerTest {

    @Autowired
    TrelloboardRepository trelloboardRepository;

    @LocalServerPort
    private int port;

    @Test
    public void shouldReturnMyBoards(){
        // TODO: 6/2/2018 TrelloApiControllerTest shouldReturnMyBoards
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
                .statusCode(HttpStatus.I_AM_A_TEAPOT.value())
                .body("message", containsString("teapot message"));
        //@formatter:on
    }

}