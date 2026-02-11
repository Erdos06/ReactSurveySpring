package com.react.survey.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private int surveyId;

    @Column(length = 20, name = "title", nullable = false)
    private String title;

    @Column(length = 20, name = "author", nullable = false)
    private String author;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "description", nullable = false, length = 40)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();

    public Survey(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.createdAt = Date.from(Instant.now());
    }
}
