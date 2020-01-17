package com.datamaster.survey.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SurveyDTO extends AbstractDTO {

    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private Boolean active;
    private List<QuestionDTO> questions;
}