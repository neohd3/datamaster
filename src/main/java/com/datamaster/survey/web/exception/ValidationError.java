package com.datamaster.survey.web.exception;

import lombok.Value;

@Value
public class ValidationError {
    public final String field;
    public final Object rejectedValue;
    public final String code;
    public final String message;
}