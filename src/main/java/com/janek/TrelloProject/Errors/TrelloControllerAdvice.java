package com.janek.TrelloProject.Errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.empty;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class TrelloControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(NullTrelloObjectIdException e, ServletWebRequest request) {
        return ErrorResponse.of(BAD_REQUEST, "trello objectId cannot be null", request.getRequest().getRequestURI());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e, ServletWebRequest request) {
        List<ErrorResponse.ValidationError> validationErrors = buildValidationErrors(e);
        return ErrorResponse.of(
                UNPROCESSABLE_ENTITY,
                "Validation failed",
                request.getRequest().getRequestURI(),
                validationErrors
        ).toResponseEntity();
    }

    private List<ErrorResponse.ValidationError> buildValidationErrors(MethodArgumentNotValidException e) {
        return Optional.of(e)
                .map(MethodArgumentNotValidException::getBindingResult)
                .map(Errors::getAllErrors)
                .map(Collection::stream)
                .orElse(empty())
                .map(this::asErrorDescription)
                .collect(toList());
    }

    private ErrorResponse.ValidationError asErrorDescription(ObjectError objectError) {
        FieldError fieldError = (FieldError) objectError;
        return new ErrorResponse.ValidationError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue()
        );
    }

}