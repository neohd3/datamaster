package com.datamaster.survey.web.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ValidationException extends RuntimeException {

    private BindingResult result;

    public ValidationException(BindingResult result) {
        this.result = result;
    }
}
