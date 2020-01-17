package com.datamaster.survey.web.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResponseCode {

    OK(0), SERVER_ERROR(1), BAD_REQUEST(2), METHOD_NOT_SUPPORTED(3), MEDIA_TYPE_NOT_SUPPORTED(4), TOO_MANY_FAILED_REQUESTS(5), NOT_FOUND(6),

    AUTHENTICATION(10), F2A_TOKEN_INVALID(12), ACCESS_DENIED(13),

    USER_EXIST(100), ENTITY_NOT_FOUND(101), VALIDATION_ERROR(102), ILLEGAL_ARGUMENT(103);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    @JsonCreator
    public static ResponseCode from(Integer i) {
        return Arrays.stream(ResponseCode.values()).filter(c -> c.code == i).findAny().get();
    }
}