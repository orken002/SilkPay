package com.example.silkpay.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponse {

    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("holder_fullname")
    private String holderFullName;
    @JsonProperty("transactions")
    private List<TransactionResponse> transactions;
}
