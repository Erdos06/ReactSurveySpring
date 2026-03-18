package com.react.survey.repositories.answer;

import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.answer.userAnswer.UserAnswerOption;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UserAnswerOptionRepository extends JpaRepository<UserAnswerOption, Integer> {
    @Transactional
    @Modifying
    void deleteByUserAnswer(UserAnswer userAnswer);
}
