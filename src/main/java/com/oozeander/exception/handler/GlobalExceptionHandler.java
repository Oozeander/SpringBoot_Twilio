package com.oozeander.exception.handler;

import com.oozeander.exception.InvalidPhoneNumberException;
import com.oozeander.model.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(message -> message.getDefaultMessage()).collect(Collectors.toList());
        return ResponseEntity.status(status).body(new ExceptionResponse(
                errors, LocalDateTime.now(), status.value(), request.getDescription(false)
        ));
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidPhoneNumberException(InvalidPhoneNumberException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse(List.of(ex.getLocalizedMessage()), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), request.getDescription(false))
        );
    }
}