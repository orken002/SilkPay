package com.example.silkpay.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class CardRequest {

    @NotNull(message = "username can not be null")
    @NotEmpty(message = "username can not be empty")
    @NotBlank(message = "username cannot contain only spaces")
    private String username;
    private double amount = 5000D;

}
