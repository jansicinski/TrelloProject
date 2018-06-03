package com.janek.TrelloProject.Controllers;

import com.janek.TrelloProject.Entities.Trelloboard;
import com.janek.TrelloProject.Repositories.TrelloboardRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrelloDbBoardControllerTest {

    @Autowired
    TrelloboardRepository trelloboardRepository;

    @LocalServerPort
    private int port;

    String myBoardId;
    Trelloboard myTrelloboard;

    @Before
    public void setUp(){
        trelloboardRepository.save(Trelloboard.builder().boardId("123").name("cat").build());
        myTrelloboard = trelloboardRepository.findByBoardId("123").get();
        myBoardId = myTrelloboard.getBoardId();
    }

    @Test
    public void shouldUpdateBoard() {
    }

    @Test
    public void shouldCcreateBoard() {
        //@formatter:off
        given()
                .port(port)
                .body(myTrelloboard)
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .log().all()

                .when()
                .post("TrelloDb/boards/")

                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
        // should have location header
        //@formatter:on

        Trelloboard trelloboard = trelloboardRepository.findByBoardId("123").get();
        Assertions.assertThat(trelloboard)
                .isEqualToIgnoringGivenFields(Trelloboard.builder()
                                                            .boardId("123")
                                                            .name("cat").build(),
                                                            "trellolists");
    }

    @Test
    public void shouldDeleteBoard() {
        //@formatter:off
        given()
                .port(port)
                .log().all()

                .when()
                .delete("TrelloDb/boards/" + myBoardId)

                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // should have location header
        //@formatter:on
    }

    @Test
    public void shouldNotDeleteBoard() {
        //@formatter:off
        given()
                .port(port)
                .log().all()

                .when()
                .delete("TrelloDb/boards/" + "X")

                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
        // should have location header
        //@formatter:on
    }

    @Test
    public void shouldGetBoard() {
    }

    @Test
    public void shouldGetAllMyBoards() {
    }

}