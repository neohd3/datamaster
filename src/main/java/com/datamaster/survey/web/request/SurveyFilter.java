package com.datamaster.survey.web.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SurveyFilter {
    private String name;
    private LocalDateTime date;
    private Boolean active;
}
