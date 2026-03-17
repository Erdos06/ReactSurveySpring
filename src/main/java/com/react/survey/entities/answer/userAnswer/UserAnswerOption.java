package com.react.survey.entities.answer.userAnswer;

import com.react.survey.entities.survey.Option;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserAnswerOption {
    @Id
    private int userAnswerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_answer_id")
    private UserAnswer userAnswer;

    @ManyToOne
    private Option option;
}
