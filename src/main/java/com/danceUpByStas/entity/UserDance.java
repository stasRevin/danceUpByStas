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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDance)) return false;
        UserDance userDance = (UserDance) o;
        return Objects.equals(getUser(), userDance.getUser()) &&
                Objects.equals(getDance(), userDance.getDance()) &&
                Objects.equals(getYearsOfExperience(), userDance.getYearsOfExperience()) &&
                Objects.equals(getLearningProficiency(), userDance.getLearningProficiency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getDance(), getYearsOfExperience(), getLearningProficiency());
    }
}
