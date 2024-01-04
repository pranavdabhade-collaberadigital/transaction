package com.tsb.transaction.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Date;

@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY,
        content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Transaction(
        @JsonProperty("AccountId") String accountId,
        @JsonProperty("TransactionId") String transactionId,
        @JsonProperty("TransactionReference") String transactionReference,
        @JsonProperty("Amount") Amount amount,
        @JsonProperty("CreditDebitIndicator") String creditDebitIndicator,
        @JsonProperty("Status") String status,
        @JsonProperty("BookingDateTime") Date bookingDateTime,
        @JsonProperty("ValueDateTime") Date valueDateTime,
        @JsonProperty("TransactionInformation") String transactionInformation,
        @JsonProperty("AddressLine") String addressLine,
        @JsonProperty("BankTransactionCode") BankTransactionCode bankTransactionCode,

        @JsonProperty("ProprietaryBankTransactionCode") ProprietaryBankTransactionCode proprietaryBankTransactionCode,

        @JsonProperty("Balance") Balance balance){}


