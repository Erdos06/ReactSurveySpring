package com.react.survey.entities.answer.userAnswer;

import com.react.survey.entities.survey.Question;
import com.react.survey.entities.survey.Survey;
import com.react.survey.entities.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_answer_id")
    private int userAnswerId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Survey survey;
}
