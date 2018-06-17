package com.janek.TrelloProject.RestControllers.Api;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TrelloApiBoardControllerTestWiremock {

    @Value("${trelloproject.key}")
    private String key;

    @Value("${trelloproject.token}")
    private String token;

    @LocalServerPort
    private int port;

    private int mePort = 8010;

    private int boardPort = 8020;

    @Rule
    public WireMockRule wireMockRuleMe = new WireMockRule(mePort);

    @Rule
    public WireMockRule wireMockRuleBoard = new WireMockRule(boardPort);

    @Test
    public void shouldGetAllMyBoards(){
        //TODO: make WireMock replace true API call because this doesn't work
        wireMockRuleMe.stubFor(get(urlMatching("/members/me*"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"idBoards\":[\"zzz\"]}")));

        wireMockRuleBoard.stubFor(get(urlEqualTo("/boards/zzz*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":\"zzz\",\"name\":\"zzzBoard\"}")));

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
                .body("name", hasItems("zzzBoard"));
        //@formatter:on
    }

}