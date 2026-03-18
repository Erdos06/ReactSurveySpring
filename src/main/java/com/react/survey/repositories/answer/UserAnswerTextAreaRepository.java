package com.react.survey.repositories.answer;

import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.answer.userAnswer.UserAnswerTextArea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface UserAnswerTextAreaRepository extends JpaRepository<UserAnswerTextArea, Long> {
    @Transactional
    @Modifying
    void deleteByUserAnswer(UserAnswer userAnswer);
}
