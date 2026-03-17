package com.react.survey.repositories.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.react.survey.entities.survey.Survey;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query("SELECT s FROM Survey s")
    public List<Survey> getAll();

    List<Survey> findAll();

    @Query("SELECT COUNT(s) > 0 FROM Survey s JOIN s.questions q WHERE s.surveyId = :surveyId AND q.questionId = :questionId")
    boolean questionExistsInSurvey(@Param("surveyId") int surveyId, @Param("questionId") int questionId);

    @Query("SELECT COUNT(q) > 0 FROM Question q JOIN q.options o WHERE q.questionId = :questionId AND o.optionId = :optionId")
    boolean optionExistsInQuestion(@Param("questionId") int questionId, @Param("optionId") int optionId);

    @Query(value = "SELECT q.question_id FROM questions q WHERE q.survey_id = :surveyId ORDER BY q.question_id ASC LIMIT 1", nativeQuery = true )
    Optional<Integer> findFirstQuestionIdBySurveyId(int surveyId);

}
