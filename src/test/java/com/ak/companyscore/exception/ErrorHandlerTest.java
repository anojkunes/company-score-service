package com.ak.companyscore.exception;

import com.ak.companyscore.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static com.ak.companyscore.exception.ErrorCode.INTERNAL_ERROR;
import static com.ak.companyscore.exception.ErrorCode.MALFORMED_REQUEST;
import static com.ak.companyscore.exception.ErrorCode.VALIDATION_ERROR_OCCURRED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

class ErrorHandlerTest {

    private final ErrorHandler errorHandler = new ErrorHandler();

    @Test
    void handleHttpMessageNotReadableExceptionException_exceptionHandledAndReturned() {
        RuntimeException exception = new RuntimeException();
        ResponseEntity<ErrorResponse> output = errorHandler.handleHttpMessageNotReadableExceptionException(exception);
        assertEquals(BAD_REQUEST, output.getStatusCode());
        assertNotNull(output.getBody());
        assertEquals(MALFORMED_REQUEST.getCode(), output.getBody().getErrorCode());
        assertEquals(MALFORMED_REQUEST.getMessage(), output.getBody().getErrorMessage());
    }

    @Test
    void handleConstraintViolationException_exceptionHandledAndReturned() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of());
        MethodArgumentNotValidException e = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<ErrorResponse> output = errorHandler.handleMethodArgumentNotValidException(e);
        assertEquals(BAD_REQUEST, output.getStatusCode());
        assertNotNull(output.getBody());
        assertEquals(VALIDATION_ERROR_OCCURRED.getCode(), output.getBody().getErrorCode());
        assertEquals("", output.getBody().getErrorMessage());
    }

    @Test
    void handleInternalErrorException_exceptionHandledAndReturned() {
        RuntimeException exception = new RuntimeException();
        ResponseEntity<ErrorResponse> output = errorHandler.handleInternalErrorException(exception);
        assertEquals(INTERNAL_SERVER_ERROR, output.getStatusCode());
        assertNotNull(output.getBody());
        assertEquals(INTERNAL_ERROR.getCode(), output.getBody().getErrorCode());
        assertEquals(INTERNAL_ERROR.getMessage(), output.getBody().getErrorMessage());
    }
}
