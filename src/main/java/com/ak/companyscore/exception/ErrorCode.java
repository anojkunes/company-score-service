package com.ak.companyscore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_ERROR("TT-000", "An unexpected error has occurred"),
    MALFORMED_REQUEST("TT-001", "Malformed request"),
    VALIDATION_ERROR_OCCURRED("TT-002", "");


    private String code;
    private String message;
}
