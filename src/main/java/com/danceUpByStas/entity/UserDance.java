package com.danceUpByStas.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dance getDance() {
        return dance;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getLearningProficiency() {
        return learningProficiency;
    }

    public void setLearningProficiency(int learningProficiency) {
        this.learningProficiency = learningProficiency;
    }

}
