package com.react.survey.repositories.answer;

import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.survey.Question;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    Optional<UserAnswer> findByUserAndSurveyAndQuestion(User user, Survey survey, Question question);
}
