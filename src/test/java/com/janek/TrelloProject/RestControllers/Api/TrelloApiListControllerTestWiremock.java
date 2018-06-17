package com.janek.TrelloProject.RestControllers.Api;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class TrelloApiListControllerTestWiremock {

    private int port = 8081;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(port);

    @Test
    public void shouldGetAllMyLists(){
        wireMockRule.stubFor(get(urlEqualTo("/TrelloApi/lists/"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\n" +
                        "\"listId\": \"12qq\",\n" +
                        "\"trellocards\": \n" +
                        "}")));

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
                .body(containsString("{\n" +
                        "\"listId\": \"12qq\",\n" +
                        "\"trellocards\": \n" +
                        "}"));
        //@formatter:on
    }

}