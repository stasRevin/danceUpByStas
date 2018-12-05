package com.danceUpByStas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "UserDance")
@Table(name = "User_Dance")
public class UserDance implements Serializable {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Dance dance;

    @Column(name = "experience_years")
    private Integer yearsOfExperience;
    @Column(name = "learning_proficiency")
    private Integer learningProficiency;

    public UserDance() {

    }

    public UserDance(User user, Dance dance) {

        this.user = user;
        this.dance = dance;
    }

}
