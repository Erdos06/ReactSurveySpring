package com.react.survey.controllers;

import com.react.survey.mappers.survey.SurveyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.react.survey.dtos.survey.SurveyDTO;
import com.react.survey.entities.survey.Survey;
import com.react.survey.services.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/surveys")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MainController {
    private final SurveyService surveyService;
    private final SurveyMapper surveyMapper;

    @GetMapping("")
    public List<SurveyDTO> index() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public SurveyDTO show(@PathVariable int id) {
        return surveyMapper.toSurveyDto(surveyService.getSurveyById(id));
    }

    @PostMapping("/create")
    public void create(@RequestBody SurveyDTO surveyDto) {
        surveyService.save(surveyMapper.toSurvey(surveyDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        surveyService.deleteSurvey(id);
    }

    @PostMapping("/new")
    public SurveyDTO createSurvey(@RequestBody SurveyDTO surveyDTO) {
        return surveyService.createSurvey(surveyDTO);
    }

    @PutMapping("/{id}/change")
    public void changeSurvey(@PathVariable int id, @RequestBody SurveyDTO surveyDto) {
        surveyService.changeSurvey(id, surveyDto);
    }
}
