package br.com.supernova.apimembers.service;

import br.com.supernova.apimembers.builder.MemberBuilder;
import br.com.supernova.apimembers.exception.MemberAlreadyRegisteredException;
import br.com.supernova.apimembers.model.Member;
import br.com.supernova.apimembers.repository.MemberRepository;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    private static final String URI = "/api/v1/members";
    @Mock
    private MemberRepository repository;

    @Mock
    private WebTestClient webTest;

    @Test
    void whenMemberInformedThenStatusCreatedReturned() throws MemberAlreadyRegisteredException {
        webTest.get().uri(URI)
                .exchange()
                .expectStatus().isCreated()
                .expectBody();

    }
}
