package com.example.silkpay.model.exeptions;

import com.example.silkpay.model.enums.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException{

    private HttpStatus httpStatus;
    private ErrorCode code;
    private String message;
}
