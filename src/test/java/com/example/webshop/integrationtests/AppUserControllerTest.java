package com.example.webshop.integrationtests;

import com.example.webshop.models.AppUserRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppUserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void registerSuccessReturnsExpectedResponse() {

        AppUserRequestModel requestJson = new AppUserRequestModel("username1", "password", "some.email@gmail.com");

        webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), AppUserRequestModel.class)
                .exchange()
                .expectStatus().isEqualTo(201);

    }

}