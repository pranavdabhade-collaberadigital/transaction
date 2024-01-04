package com.tsb.transaction.service.TransactionServiceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tsb.transaction.dto.transactiondto.TransactionResponseDto;
import com.tsb.transaction.exception.CustomException;
import com.tsb.transaction.service.TransactionService.AuthService;
import com.tsb.transaction.service.TransactionService.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import com.tsb.transaction.util.JsonUtil;
import com.tsb.transaction.util.HeadersUtil;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);
    @Value("${api.fakeApi.uri}")
    private String gatewayUrl;

    @Value("${api.max-attempt}")
    private Integer maxAttempt;
    @Value("${api.delay-millis}")
    private Integer delayMillis;
    private final AuthService authService;
    private final WebClient webClient;

    public TransactionServiceImpl(AuthService authService, WebClient webClient) {

        this.authService = authService;
        this.webClient = webClient;
    }

    @Override
    public Flux<TransactionResponseDto> getTransactionById(String xFapiAuthDate,
                                                           String xFapiCustomerIpAddress,
                                                           String xFapiInteractionId,
                                                           String accept,
                                                           String transactionId) {
        return authService
                .getToken()
                .flatMap(tokenResponse -> {
                    HttpHeaders httpHeaders = HeadersUtil.AuthHeader(tokenResponse.token());
                    httpHeaders.set("x-fapi-auth-date", xFapiAuthDate);
                    httpHeaders.set("x-fapi-customer-ip-address", xFapiCustomerIpAddress);
                    httpHeaders.set("x-fapi-interaction-id", xFapiInteractionId);
                    httpHeaders.set("Accept", accept);
                    UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                            .fromHttpUrl(gatewayUrl)
                            .path(transactionId);

                    logger.info("Transaction Request: {} headers: {}",
                            componentsBuilder.toUriString(),
                            JsonUtil.toJson(httpHeaders));
                    return webClient
                            .get()
                            .uri(componentsBuilder.toUriString())
                            .headers(head -> head.addAll(httpHeaders))
                            .retrieve()
                            .onStatus(HttpStatusCode::isError,
                                    response -> response.bodyToMono(String.class).flatMap(errorBody -> {
                                        logger.error("Error transaction status code {}, response body: {}", response.statusCode(), errorBody);
                                        CustomException customException = new CustomException("451", "Failed to fetch transactions");
                                        return Mono.error(customException);
                                    }))
                            .bodyToMono(String.class)
                            .map(response -> {
                                logger.info("Transaction Response: {}", response);
                                return JsonUtil.toObjectOfList(response, new TypeReference<List<TransactionResponseDto>>() {
                                });
                            })
                            .retryWhen(Retry.fixedDelay(maxAttempt, Duration.ofMillis(delayMillis)))
                            .onErrorResume(error -> {
                              logger.error("Error in transaction : {}",error.getMessage());
                                throw new CustomException("451","Failed to fetch transaction");
                            });
                }).flatMapMany(Flux::fromIterable).log();
    }
}
