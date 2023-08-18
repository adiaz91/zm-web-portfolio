package com.portfolio.integration.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.data.PortfolioFactory;
import com.portfolio.entities.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
class PortfolioControllerIntegrationTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ObjectMapper objectMapper;
    private static String PATH="/api";


    @BeforeEach
    void setUp() {

    }

    @Test
    void findById_successful() {

        final long ID=1L;

        client.get().uri(PATH+ "/{id}",ID).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.imageUrl").isEqualTo("image_url_1")
                .jsonPath("$.twitterUserName").isEqualTo("twitter_user_name1")
                .jsonPath("$.title").isEqualTo("backend")
                .jsonPath("$.address").isEqualTo("ABC 123 89")
                .jsonPath("$.email").isEqualTo("mail1@example.com")
                .jsonPath("$.experience").isEqualTo("experience1")
                .jsonPath("$.experienceSummary").isEqualTo("experience_summary1")
                .jsonPath("$.lastName").isEqualTo("Thomson")
                .jsonPath("$.name").isEqualTo("Brad")
                .jsonPath("$.phone").isEqualTo("+51 999 999 999")
                .jsonPath("$.zipCode").isEqualTo("8966-988");

    }

    @Test
    void findById_notFound() throws Exception {
        final long ID=100L;
        client.get().uri(PATH+ "/{id}",ID).exchange()
                .expectStatus().isNotFound();

    }


    @Test
    void update_successful() throws Exception {
        Portfolio portfolio = PortfolioFactory.sample3();
        client.put().uri(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(portfolio)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.imageUrl").isEqualTo("http://expamle.com/images")
                .jsonPath("$.twitterUserName").isEqualTo("peterpan")
                .jsonPath("$.title").isEqualTo("Senior Java Developer")
                .jsonPath("$.address").isEqualTo("Av 45 96")
                .jsonPath("$.email").isEqualTo("updated@example.com")
                .jsonPath("$.experience").isEqualTo("sample experience")
                .jsonPath("$.experienceSummary").isEqualTo("sample summary")
                .jsonPath("$.lastName").isEqualTo("Pan")
                .jsonPath("$.name").isEqualTo("Peter")
                .jsonPath("$.phone").isEqualTo("+2 366 998 555")
                .jsonPath("$.zipCode").isEqualTo("9775");
    }

    @Test
    void update_not_found() throws Exception {
        Portfolio portfolio = PortfolioFactory.sample3();
        portfolio.setId(1000L);
        client.put().uri(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(portfolio)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    void update_bad_request() throws Exception {
        Portfolio portfolio = PortfolioFactory.sample3();
        portfolio.setId(null);
        client.put().uri(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(portfolio)
                .exchange()
                .expectStatus().isBadRequest();

    }
}