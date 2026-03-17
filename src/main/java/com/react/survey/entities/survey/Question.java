package com.react.survey.entities.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    @Column(name = "text", nullable = false, length = 60)
    private String text;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "required")
    private boolean required;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Option> options = new ArrayList<>();
}
