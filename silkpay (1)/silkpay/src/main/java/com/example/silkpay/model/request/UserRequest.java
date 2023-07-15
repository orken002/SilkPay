package com.example.silkpay.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRequest {

    @NotNull(message = "username can not be null")
    @NotEmpty(message = "username can not be empty")
    @NotBlank(message = "username cannot contain only spaces")
    private String username;

    @NotNull(message = "password can not be null")
    @NotEmpty(message = "password can not be empty")
    @NotBlank(message = "password cannot contain only spaces")
    @Size(min = 6, message = "password must contain at least 6 letters")
    private String password;

    @NotNull(message = "name can not be null")
    @NotEmpty(message = "name can not be empty")
    @NotBlank(message = "name cannot contain only spaces")
    private String name;


    @NotNull(message = "surname can not be null")
    @NotEmpty(message = "surname can not be empty")
    @NotBlank(message = "surname cannot contain only spaces")
    private String surname;

    @NotNull(message = "patronymic can not be null")
    @NotEmpty(message = "patronymic can not be empty")
    @NotBlank(message = "patronymic cannot contain only spaces")
    private String patronymic;
}
