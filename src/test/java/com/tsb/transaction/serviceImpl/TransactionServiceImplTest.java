package com.tsb.transaction.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsb.transaction.controller.TransactionController;
import com.tsb.transaction.dto.transactiondto.TransactionResponseDto;
import com.tsb.transaction.model.transaction.Data;
import com.tsb.transaction.model.transaction.TransactionResponse;
import com.tsb.transaction.service.TransactionService.AuthService;
import com.tsb.transaction.service.TransactionServiceImpl.AuthServiceImpl;
import com.tsb.transaction.service.TransactionServiceImpl.TransactionServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import okhttp3.mockwebserver.MockWebServer;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

    @Mock
    private WebClient webClientMock;

    @Autowired
    private AuthService authService;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;

    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;


    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;


    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    private Mono<TransactionResponseDto> postResponseMock;

    @Autowired
    private TransactionServiceImpl transactionServiceMock;

    public static MockWebServer mockBackEnd;

    @Autowired
    TransactionController transactionController;


    @BeforeEach
    void initialize()
    {
       // transactionController = new TransactionController(transactionServiceMock);
        transactionServiceMock = new TransactionServiceImpl(new AuthServiceImpl(),webClientMock);
    }

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void getTransaction_test() throws Exception {
        TransactionResponse transactionObj = new TransactionResponse(Data.builder().build());

        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(
                ArgumentMatchers.<Class<TransactionResponse>>notNull()))
                .thenReturn(Mono.just(transactionObj));
        Flux<TransactionResponseDto> response = transactionServiceMock.getTransactionById("Sun, 10 Sep 2017 19:43:31 GMT",
                "104.25.212.99",
                "93bac548-d2de-4546-b106-880a5018460d",
                "application/json", "123");
        Assertions.assertNotEquals(null, response);

    }
}


