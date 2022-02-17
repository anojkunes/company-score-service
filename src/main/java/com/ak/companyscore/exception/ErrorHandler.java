package com.ak.companyscore.exception;

import com.ak.companyscore.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.stream.Collectors;

import static com.ak.companyscore.exception.ErrorCode.INTERNAL_ERROR;
import static com.ak.companyscore.exception.ErrorCode.MALFORMED_REQUEST;
import static com.ak.companyscore.exception.ErrorCode.VALIDATION_ERROR_OCCURRED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({HttpMessageNotReadableException.class, HttpMessageConversionException.class})
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableExceptionException(Exception e) {
        LOG.warn("Malformed request - {}", e.getMessage());
        return new ResponseEntity(createErrorResponse(MALFORMED_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(new ErrorResponse(VALIDATION_ERROR_OCCURRED.getCode(), getBeanValidationError(e.getBindingResult())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleInternalErrorException(RuntimeException e) {
        LOG.error("Internal Error", e);
        return new ResponseEntity<>(createErrorResponse(INTERNAL_ERROR), INTERNAL_SERVER_ERROR);
    }

    private static ErrorResponse createErrorResponse(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

    private static String getBeanValidationError(BindingResult binding) {
        return binding.getFieldErrors().stream().map(m -> m.getField() + " " + m.getDefaultMessage()).collect(Collectors.joining(", "));
    }
}
