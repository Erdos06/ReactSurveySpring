package com.react.survey.controllers;

import com.react.survey.dtos.answer.AnswerDto;
import com.react.survey.dtos.survey.SurveyDto;
import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.mappers.survey.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.react.survey.services.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/surveys")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    private final SurveyMapper surveyMapper;

    @GetMapping("")
    public List<SurveyDto> index() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public SurveyDto show(@PathVariable int id) {
        return surveyMapper.toSurveyDto(surveyService.getSurveyById(id));
    }

    @PostMapping("/create")
    public void create(@RequestBody SurveyDto surveyDto) {
        surveyService.save(surveyMapper.toSurvey(surveyDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        surveyService.deleteSurvey(id);
    }

    @PostMapping("/new")
    public SurveyDto createSurvey(@RequestBody SurveyDto surveyDTO) {
        return surveyService.createSurvey(surveyDTO);
    }

    @PutMapping("/{id}/change")
    public void changeSurvey(@PathVariable int id, @RequestBody SurveyDto surveyDto) {
        surveyService.changeSurvey(id, surveyDto);
    }

    @PostMapping("/{surveyId}/start")
    public UserSurvey startSurvey(@PathVariable int surveyId) {
        return surveyService.startSurvey(surveyId);
    }

    @PostMapping("/{surveyId}/answer")
    public void answerSurvey(@PathVariable int surveyId, @RequestBody AnswerDto answerDto) {

    }
}
