package com.datamaster.survey.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionDTO extends AbstractDTO {
    private String text;
    private Integer order;
}