package com.tsb.transaction.service.TransactionService;


import com.tsb.transaction.model.token.TokenResponse;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<TokenResponse> getToken();
}
