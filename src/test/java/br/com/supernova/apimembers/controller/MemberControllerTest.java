package br.com.supernova.apimembers.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    private static final String ENDPOINT_LOCAL = "http://localhost:8080/api/v1/members";

    @Mock
    private WebTestClient webTestClient;

    @Test
    void whenservidorestejarecebendorequisicoesentaocontroledeveinformarcliente(){
        webTestClient.get().uri(ENDPOINT_LOCAL.concat("/{id}"),"10")
                .exchange()
                .expectStatus().isOk()
                .expectBody();


    }

    @Test
    public void getOneHeronotFound(){

        webTestClient.get().uri(ENDPOINT_LOCAL.concat("/{id}"),"10")
                .exchange()
                .expectStatus().isNotFound();

    }


    @Test
    public void deleteHero(){

        webTestClient.delete().uri(ENDPOINT_LOCAL.concat("/{id}"),"1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Void.class);

    }

}
