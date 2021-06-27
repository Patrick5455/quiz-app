package com.cova.quizapp.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trivia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trivia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "question")
    @NotNull
    private String question;

    @Column(name = "answer")
    @NotNull
    private String answer;

    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    public enum DifficultyLevel{
        @JsonProperty(value = "HARD")
        HARD(20),
        @JsonProperty(value = "MEDIUM")
        MEDIUM(10),
        @JsonProperty(value = "EASY")
        EASY(5);

        int numOfAllowedQosForLevel;

         DifficultyLevel(int numOfAllowedQosForLevel){ this.numOfAllowedQosForLevel = numOfAllowedQosForLevel; }
        public int getNumOfAllowedQosForLevel() { return numOfAllowedQosForLevel; }
    }

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
        Trivia other = (Trivia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
