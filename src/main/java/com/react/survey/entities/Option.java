package com.react.survey.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "options")
@NoArgsConstructor()
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int optionId;

    @Column(name = "option_text", nullable = false,  length = 20)
    private String optionText;

    @Column(name = "count", nullable = false)
    private int count = 0;

}
