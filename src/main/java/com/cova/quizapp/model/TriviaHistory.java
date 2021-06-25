package com.cova.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "trivia_history")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TriviaHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "date_of_trivia")
    private Timestamp triviaDate;

    @Column(name = "num_passed_trivia")
    private Long passedTrivia;

    @Column(name = "num_failed_trivia")
    private Long failedTrivia;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser appUser;


}
