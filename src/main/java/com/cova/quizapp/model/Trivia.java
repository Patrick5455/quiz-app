package com.cova.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TRIVIA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trivia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "difficulty_level")
    private String difficultyLevel;


    public enum DifficultyLevel{
        HARD,
        MEDIUM,
        EASY
    }


}
