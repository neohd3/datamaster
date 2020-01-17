package com.datamaster.survey.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SurveyForm {

    @NotNull
    @Length(min = 5, max = 120)
    private String name;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    @NotNull
    private Boolean active;

    private List<QuestionForm> questions;

    @Data
    public static class QuestionForm {

        @NotNull
        @Length(min = 5, max = 1000)
        private String text;

        @NotNull
        private Integer order;
    }

}
