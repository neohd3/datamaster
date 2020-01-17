package com.datamaster.survey.web.response;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString(callSuper = true)
public class SimpleResponse<T> extends Response {

    public final T result;

    public SimpleResponse(T result) {
        super(true, null, ResponseCode.OK, HttpStatus.OK);
        this.result = result;
    }
}