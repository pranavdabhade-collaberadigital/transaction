package com.tsb.transaction.model.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {

    private Amount Amount;
    private String CreditDebitIndicator;
    private String Type;
}
