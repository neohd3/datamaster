package com.datamaster.survey.service.survey;

import com.datamaster.survey.dao.model.Question;
import com.datamaster.survey.dao.model.Survey;
import com.datamaster.survey.dao.repo.SurveyRepository;
import com.datamaster.survey.dao.repo.SurveySpec;
import com.datamaster.survey.web.dto.SurveyDTO;
import com.datamaster.survey.web.form.SurveyForm;
import com.datamaster.survey.web.request.SurveyFilter;
import com.datamaster.survey.web.response.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyService(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    public PageableResponse<SurveyDTO> findAll(SurveyFilter filter, Pageable page) {

        Page<Survey> all = surveyRepository.findAll(SurveySpec.where(filter), page);
        List<SurveyDTO> surveys = all.getContent().stream().map(surveyMapper::toDto).collect(toList());

        return new PageableResponse<>(surveys, page, all.getTotalElements());
    }

    @Transactional
    public SurveyDTO create(SurveyForm form) {

        Survey survey = new Survey();
        survey.setName(form.getName());
        survey.setStart(form.getStart());
        survey.setEnd(form.getEnd());
        survey.setActive(form.getActive());

        List<Question> questions = form.getQuestions().stream().map(e -> {
            Question q = new Question();
            q.setSurvey(survey);
            q.setText(e.getText());
            q.setOrder(e.getOrder());
            return q;
        }).collect(toList());

        survey.setQuestions(questions);

        return surveyMapper.toDto(surveyRepository.save(survey));
    }
}
