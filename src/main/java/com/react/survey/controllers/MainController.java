package com.react.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.react.survey.dtos.SurveyDTO;
import com.react.survey.entities.Survey;
import com.react.survey.services.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/surveys")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping("/")
    public List<Survey> index() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Survey show(@PathVariable Integer id) {
        return surveyService.getSurveyById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody Survey survey) {
        surveyService.save(survey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        surveyService.deleteSurvey(Integer.toUnsignedLong(id));
    }

    @PostMapping("/new")
    public Survey createSurvey(@RequestBody SurveyDTO surveyDTO) {
        return surveyService.createSurvey(surveyDTO);
    }
}
