package com.ak.companyscore.dto;

import lombok.Value;

@Value
public class ErrorResponse {
    String errorCode;
    String errorMessage;
}
