package com.react.survey.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.react.survey.entities.survey.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query("SELECT s FROM Survey s")
    public List<Survey> getAll();

    List<Survey> findAll();
}
