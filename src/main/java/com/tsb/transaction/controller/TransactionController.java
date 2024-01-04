package com.tsb.transaction.controller;

import com.tsb.transaction.dto.transactiondto.TransactionResponseDto;
import com.tsb.transaction.service.TransactionServiceImpl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TransactionController implements TransactionApiVersion {


    private final TransactionServiceImpl transactionServiceImpl;
    @Autowired
    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }
       // System.out.println("Into transaction controller ......");


    @GetMapping("/transaction/{TransactionId}")
    public Flux<TransactionResponseDto>getTransaction(@RequestHeader("x-fapi-auth-date") String xFapiAuthDate,
                                                      @RequestHeader("x-fapi-customer-ip-address") String xFapiCustomerIpAddress,
                                                      @RequestHeader("x-fapi-interaction-id") String xFapiInteractionId,
                                                      @RequestHeader("Accept") String accept,
                                                      @PathVariable("TransactionId") String transactionId) {
      System.out.println("Into transaction controller ......");

        return transactionServiceImpl.getTransactionById(xFapiAuthDate,xFapiCustomerIpAddress,xFapiInteractionId,accept,transactionId);


    }

}
