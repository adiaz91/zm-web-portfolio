package com.portfolio.integration.controllers.mvc;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerIntegrationTest {

    @Autowired
    private WebTestClient client;

    private static String PATH="";

    private final MediaType TEXT_HTML_UTF8=MediaType.parseMediaType("text/html;charset=UTF-8");

    @BeforeEach
    void setUp() {

    }

    @Test
    void findById_successful() {

        final long ID=1L;
        client.get().uri(PATH+ "/{id}",ID).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(TEXT_HTML_UTF8);

    }

    @Test
    void list_all_successful() {

        final long ID=1L;
        client.get().uri(PATH).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(TEXT_HTML_UTF8);

    }

    @Test
    void findById_notFound() {
        final long ID=100L;
        client.get().uri(PATH+ "/{id}",ID).exchange()
                .expectStatus().isNotFound();

    }



}