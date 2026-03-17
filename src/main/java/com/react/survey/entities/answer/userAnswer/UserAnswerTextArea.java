package com.react.survey.entities.answer.userAnswer;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserAnswerTextArea {
    @Id
    private int userAnswerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_answer_id")
    private UserAnswer userAnswer;

    private String textAreaAnswer;
}
