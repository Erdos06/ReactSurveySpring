package com.react.survey.entities.answer;

import com.react.survey.entities.survey.Question;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSurveyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question lastQuestion;

    @Enumerated(EnumType.STRING)
    private SurveyStatus status;

    @PrePersist
    public void prePersist(){
        if(status == null){
            status = SurveyStatus.NOT_STARTED;
        }
    }
}
