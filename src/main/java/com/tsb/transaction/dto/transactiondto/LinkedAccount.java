package com.tsb.transaction.dto.transactiondto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedAccount{
    @JsonProperty("SchemeName") String schemeName;
    @JsonProperty("Identification") String identification;
    @JsonProperty("Name") String name;
    @JsonProperty("SecondaryIdentification") String secondaryIdentification;
}