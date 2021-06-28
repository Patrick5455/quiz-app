package com.cova.quizapp.model.entity;

import com.cova.quizapp.model.response.GetTriviaResultResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @JsonIgnore
    private Long id ;

    @Column(name = "date_of_trivia")
    private Timestamp triviaDate;

    @Column(name = "num_passed_trivia")
    private Integer passedTrivia;

    @Column(name = "num_failed_trivia")
    private Integer failedTrivia;

    @Column(name = "total_score")
    private Double totalScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private Trivia.DifficultyLevel difficultyLevel;

    @Column(name = "num_of_answered_trivia")
    private Integer numOfAnsweredTrivia;

    @Column(name = "performance")
    @Enumerated(EnumType.STRING)
    private GetTriviaResultResponse.Performance performance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private AppUser appUser;

    @Column(name = "total_trivia_given")
    private Integer totalTriviaGiven;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TriviaHistory other = (TriviaHistory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
