package br.com.rafaelshayashi.library.web;

import br.com.rafaelshayashi.library.config.JWSBuilder;
import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.exception.ResourceNotExistsException;
import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.service.BookInstanceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
class BookInstanceControllerTest {

    private static RsaJsonWebKey rsaJsonWebKey;

    private static String subject;

    @Value("${wiremock.server.baseUrl}")
    private static String wireMockServerBaseUrl;

    @MockBean
    private BookInstanceService service;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(BookInstanceRequest library) {
        try {
            return new ObjectMapper().writeValueAsString(library);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @BeforeAll
    static void initAll() throws JoseException {
        // JWK
        rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId("k1");
        rsaJsonWebKey.setAlgorithm(AlgorithmIdentifiers.RSA_USING_SHA256);
        rsaJsonWebKey.setUse("sig");

        subject = UUID.randomUUID().toString();

    }

    @BeforeEach
    private void init() {

        JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(rsaJsonWebKey);

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/.well-known/jwks.json"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonWebKeySet.toJson())));
    }

    @Test
    @DisplayName("POST /books/instances - Should create a book instance")
    void should_create_a_book_instance() throws Exception {

        LibraryBranch libraryBranchMock = LibraryBranch.builder()
                .name("The New York Public Library")
                .build();

        BookInstance bookInstanceMock = BookInstance
                .builder()
                .book(UUID.fromString("03e9ffcc-2191-4dd1-8d05-86976a5f431d"))
                .library(libraryBranchMock)
                .build();

        doReturn(bookInstanceMock).when(service).create(any());

        String token = JWSBuilder.getToken(rsaJsonWebKey, subject, wireMockServerBaseUrl).getCompactSerialization();

        BookInstanceRequest request = new BookInstanceRequest();
        request.setBookUuid(UUID.randomUUID().toString());
        request.setLibraryUuid(UUID.randomUUID().toString());

        mockMvc.perform(post("/books/instances")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /books/instances - Try to create a book instance with a nonexistent book")
    void try_to_create_a_book_instance_with_a_nonexistent_book() throws Exception {

        String token = JWSBuilder.getToken(rsaJsonWebKey, subject, wireMockServerBaseUrl).getCompactSerialization();

        doThrow(new ResourceNotExistsException(UUID.fromString("03e9ffcc-2191-4dd1-8d05-86976a5f431d").toString()))
                .when(service).create(any());

        BookInstanceRequest request = new BookInstanceRequest();
        request.setBookUuid(UUID.randomUUID().toString());
        request.setLibraryUuid(UUID.randomUUID().toString());

        mockMvc.perform(post("/books/instances")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isBadRequest());
    }
}
