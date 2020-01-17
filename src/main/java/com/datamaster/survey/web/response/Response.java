package com.datamaster.survey.web.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class Response {

    private final Boolean success;
    private final HttpStatus status;
    private final String message;
    private final ResponseCode code;
    private final LocalDateTime timestamp;

    public Response(final Boolean success, final String message, final ResponseCode code, final HttpStatus status) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public static Response fail(final String message, final ResponseCode code, final HttpStatus status) {
        return new Response(false, message, code, status);
    }

    public static Response of(final Boolean success, final String message, final ResponseCode code, final HttpStatus status) {
        return new Response(success, message, code, status);
    }

    public static Response ok() {
        return of(true, "OK", ResponseCode.OK, HttpStatus.OK);
    }

    public static Response commonError() {
        return fail("Bad request", ResponseCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    public static Response error(String msg) {
        return fail(msg, ResponseCode.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Response withMsg(String msg) {
        return new Response(this.success, msg, this.code, this.status);
    }

}