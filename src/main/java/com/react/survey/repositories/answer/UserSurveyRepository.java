package com.react.survey.repositories.answer;

import com.react.survey.entities.answer.UserSurvey;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
    Optional<UserSurvey> findByUserAndSurvey(User user, Survey survey);
}
