package com.cova.quizapp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "app_user" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column(name = "first_name")
    @NotBlank
    @NotNull
    private String firstname;

    @Column(name = "last_name")
    @NotBlank
    @NotNull
    private String lastname;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "username")
    @NotBlank
    @NotNull
    private String username;

    @Column(name = "password")
    @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "created_at")
    @JsonIgnore
    @NotNull
    private Timestamp createdAt;

    @Column(name = "is_active")
    @JsonIgnore
    private int isActive;

    @Column(name = "updated_at")
    @JsonIgnore
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TriviaHistory> triviaHistories;


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
        AppUser other = (AppUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }



}
