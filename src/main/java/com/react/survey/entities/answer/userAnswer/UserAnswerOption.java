package com.react.survey.entities.answer.userAnswer;

import com.react.survey.entities.answer.userAnswer.UserAnswer;
import com.react.survey.entities.survey.Option;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserAnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Собственный ID для каждой галочки в чекбоксе

    @ManyToOne // Теперь много опций могут ссылаться на один ответ
    @JoinColumn(name = "user_answer_id")
    private UserAnswer userAnswer;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
}