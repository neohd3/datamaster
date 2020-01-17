package com.datamaster.survey.service.survey;

import com.datamaster.survey.dao.model.Survey;
import com.datamaster.survey.web.dto.SurveyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SurveyMapper {

    private final ModelMapper mapper;

    public SurveyMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public SurveyDTO toDto(Survey entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, SurveyDTO.class);
    }
}