package com.tsb.transaction.dto.transactiondto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseDto {

    @JsonProperty("Data")
    public Data data;
    }

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Amount{
    @JsonProperty("Amount")
    public String amount;
    @JsonProperty("Currency")
    public String currency;
}
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Balance{
    @JsonProperty("Amount")
    public Amount amount;
    @JsonProperty("CreditDebitIndicator")
    public String creditDebitIndicator;
    @JsonProperty("Type")
    public String type;
}
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class BankTransactionCode{
    @JsonProperty("Code")
    public String code;
    @JsonProperty("SubCode")
    public String subCode;
}
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Data{
    @JsonProperty("Transaction")
    public List<Transaction> transaction;
}
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class ProprietaryBankTransactionCode{
    @JsonProperty("Code")
    public String code;
    @JsonProperty("Issuer")
    public String issuer;
}
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class Transaction{
    @JsonProperty("AccountId")
    public String accountId;
    @JsonProperty("TransactionId")
    public String transactionId;
    @JsonProperty("TransactionReference")
    public String transactionReference;
    @JsonProperty("Amount")
    public Amount amount;
    @JsonProperty("CreditDebitIndicator")
    public String creditDebitIndicator;
    @JsonProperty("Status")
    public String status;
    @JsonProperty("BookingDateTime")
    public Date bookingDateTime;
    @JsonProperty("ValueDateTime")
    public Date valueDateTime;
    @JsonProperty("TransactionInformation")
    public String transactionInformation;
    @JsonProperty("AddressLine")
    public String addressLine;
    @JsonProperty("BankTransactionCode")
    public BankTransactionCode bankTransactionCode;
    @JsonProperty("ProprietaryBankTransactionCode")
    public ProprietaryBankTransactionCode proprietaryBankTransactionCode;
    @JsonProperty("Balance")
    public Balance balance;
}




//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public static class Transaction {
//        private String accountId;
//        private String transactionId;
//        private String transactionReference;
//        private Amount amount;
//        private String creditDebitIndicator;
//        private String status;
//        private String bookingDateTime;
//        private String valueDateTime;
//        private String transactionInformation;
//        private BankTransactionCode bankTransactionCode;
//        private ProprietaryBankTransactionCode proprietaryBankTransactionCode;
//        private Balance balance;
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @JsonIgnoreProperties(ignoreUnknown = true)
//        public static class Amount {
//            private String amount;
//            private String currency;
//        }
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @JsonIgnoreProperties(ignoreUnknown = true)
//        public static class BankTransactionCode {
//            private String code;
//            private String subCode;
//        }
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @JsonIgnoreProperties(ignoreUnknown = true)
//        public static class ProprietaryBankTransactionCode {
//            private String code;
//            private String issuer;
//        }
//
//        @Data
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @JsonIgnoreProperties(ignoreUnknown = true)
//        public static class Balance {
//            private Amount amount;
//            private String creditDebitIndicator;
//            private String type;
//
////            @Data
////            @NoArgsConstructor
////            @AllArgsConstructor
////            @JsonIgnoreProperties(ignoreUnknown = true)
////            public static class Amount {
////                private String amount;
////                private String currency;
////            }
//        }
//    }}

