package com.datamaster.survey.web.controller;

import com.datamaster.survey.service.survey.SurveyService;
import com.datamaster.survey.web.ApiPageable;
import com.datamaster.survey.web.dto.SurveyDTO;
import com.datamaster.survey.web.exception.ValidationException;
import com.datamaster.survey.web.form.SurveyForm;
import com.datamaster.survey.web.request.SurveyFilter;
import com.datamaster.survey.web.response.PageableResponse;
import com.datamaster.survey.web.response.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "Survey", value = "Survey Queries")
public class SurveyController {

    interface Paths {
        String SURVEYS = "/surveys";
    }

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping(Paths.SURVEYS)
    @ApiOperation(value = "Find all surveys.")
    @ApiPageable
    public PageableResponse<SurveyDTO> findAll(SurveyFilter filter, Pageable page) {
        return surveyService.findAll(filter, page);
    }

    @PostMapping(Paths.SURVEYS)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new a new survey.")
    public SimpleResponse<SurveyDTO> create(@Valid @RequestBody SurveyForm form, BindingResult result) {

        if (result.hasErrors()) throw new ValidationException(result);

        return new SimpleResponse<>(surveyService.create(form));
    }

}
