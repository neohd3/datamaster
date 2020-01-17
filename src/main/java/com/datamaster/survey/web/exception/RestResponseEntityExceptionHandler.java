package com.datamaster.survey.web.exception;

import com.datamaster.survey.web.response.Response;
import com.datamaster.survey.web.response.ResponseCode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handle(Exception e) {

        Response response;
        if (e instanceof ValidationException) {
            ValidationException ex = (ValidationException) e;
            List<ValidationError> errors = ex.getResult().getAllErrors().stream().map(err -> {

                String field = "global";
                Object rejectedValue = null;
                String code = err.getCode();
                String message = err.getDefaultMessage();

                if (err instanceof FieldError) {
                    field = ((FieldError) err).getField();
                    rejectedValue = ((FieldError) err).getRejectedValue();
                }

                return new ValidationError(field, rejectedValue, code, message);

            }).collect(Collectors.toList());
            response = Response.fail(errors.toString(), ResponseCode.VALIDATION_ERROR, BAD_REQUEST);
        } else if (e instanceof EntityNotFoundException) {
            response = Response.fail(e.getMessage(), ResponseCode.ENTITY_NOT_FOUND, BAD_REQUEST);
        } else if (e instanceof AccessDeniedException) {
            response = Response.fail(e.getMessage(), ResponseCode.ACCESS_DENIED, FORBIDDEN);
        } else if (e instanceof IllegalArgumentException) {
            response = Response.fail(e.getMessage(), ResponseCode.ILLEGAL_ARGUMENT, BAD_REQUEST);
        } else if (e instanceof HttpMessageConversionException) {

            Throwable cause = ((HttpMessageConversionException) e).getRootCause();
            if (cause == null) cause = e;

            String msg = cause.getMessage();
            msg = msg.contains("Required request body is missing") ? "Required request body is missing." : msg;

            if (cause instanceof InvalidFormatException || cause instanceof JsonParseException) {
                msg = "Invalid json message received.";
            }

            response = Response.fail(msg, ResponseCode.ILLEGAL_ARGUMENT, BAD_REQUEST);
        } else if (e instanceof MethodArgumentTypeMismatchException) {

            Throwable cause = ((MethodArgumentTypeMismatchException) e).getRootCause();
            String msg = cause != null ? cause.getMessage() : e.getMessage();

            response = Response.fail(msg, ResponseCode.ILLEGAL_ARGUMENT, BAD_REQUEST);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            response = Response.fail("Method not supported.", ResponseCode.METHOD_NOT_SUPPORTED, HttpStatus.METHOD_NOT_ALLOWED);
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            response = Response.fail("Media type not supported.", ResponseCode.MEDIA_TYPE_NOT_SUPPORTED, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } else if (e instanceof NoHandlerFoundException) {
            response = Response.fail("Not found.", ResponseCode.NOT_FOUND, NOT_FOUND);
        } else {
            log.warn("Unhandled exception: ", e);
            response = Response.error("Unhandled exception: " + e.getClass());
        }

        String msg = response.getMessage();
        if (msg == null) msg = "";
        Response obfsct = response.withMsg(msg.replace("datamaster", "microsoft"));
        return new ResponseEntity<>(obfsct, null, response.getStatus());
    }

}