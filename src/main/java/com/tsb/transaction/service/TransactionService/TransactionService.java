package com.tsb.transaction.service.TransactionService;

import com.tsb.transaction.dto.transactiondto.TransactionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<TransactionResponseDto> getTransactionById(
            String xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String accept,
            String transactionId);

}
