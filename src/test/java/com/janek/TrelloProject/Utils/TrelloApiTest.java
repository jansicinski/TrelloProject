package com.janek.TrelloProject.Utils;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import jdk.incubator.http.HttpClient;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
//import static org.junit.Assert.assertThat;
//import static sun.nio.cs.Surrogate.is;

public class TrelloApiTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8081);

    @Test
    public void shouldReturnMyBoards(){
        wireMockRule.stubFor(get(urlEqualTo("/TrelloApi/boards"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("Hello world!")));

//        assertThat(testClient.get("/TrelloApi/boards").statusCode(), is(200));
    }

}