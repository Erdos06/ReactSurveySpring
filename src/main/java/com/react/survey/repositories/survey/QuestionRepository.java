package com.react.survey.repositories.survey;

import com.react.survey.entities.survey.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
