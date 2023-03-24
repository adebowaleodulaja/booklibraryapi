package com.wizer.booklibrary.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { ApiRequestException.class })
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(apiRequestException.getMessage(), httpStatus,
                ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(notFoundException.getMessage(), httpStatus,
                ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException cvexc) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(cvexc.getLocalizedMessage(), httpStatus,
                ZonedDateTime.now(ZoneId.systemDefault()));

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
