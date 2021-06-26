package com.cova.quizapp.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String firstname;

    @Column(name = "last_name")
    @NotBlank
    private String lastname;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TriviaHistory> triviaHistories;


}
