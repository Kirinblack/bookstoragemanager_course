package com.lguilherme.bookstoragemanager.Exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class BookstoreExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception){
        return buildResponseEntity(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));

    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException exception){
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }
    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Object> handleDateTimeException(DateTimeException exception){
        return buildResponseEntity(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                Collections.singletonList(exception.getMessage()));
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers,
            HttpStatus status, WebRequest request){
        return buildResponseEntity(
            HttpStatus.BAD_REQUEST, "Malformated JSON body or/and field error. please analyze it again",
            Collections.singletonList(exception.getLocalizedMessage()));
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message, List<String> errors) {
        ApiError apiError = ApiError.builder()
                 .code(HttpStatus.BAD_REQUEST.value())
                 .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                 .message(message)
                 .errors(errors)
                 .timestamp(LocalDateTime.now())
                 .build();
        return ResponseEntity.status(httpStatus).body(apiError);
    }

}
