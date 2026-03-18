package com.react.survey.entities.answer.userAnswer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAnswerTextArea {
    @Id
    private int userAnswerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_answer_id")
    private UserAnswer userAnswer;

    private String textAreaAnswer;
}
