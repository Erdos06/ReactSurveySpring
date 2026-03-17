package com.react.survey.repositories.answer;

import com.react.survey.entities.answer.UserSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSurveyRepository extends JpaRepository<UserSurvey, Long> {
}
