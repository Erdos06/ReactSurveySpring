package com.react.survey.entities.user;

import com.react.survey.entities.survey.Survey;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false, length = 18)
    private String username;

    @Column(unique = true, nullable = false, length = 30)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Survey> surveys;
}
