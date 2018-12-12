package com.danceUpByStas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is the UserDance bean class representing the UserDance junction entity.
 * @author srevin
 */
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

    /**
     * The empty constructor.
     */
    public UserDance() {

    }

    /**
     * The parameterized constructor.
     * @param user The reference to the user object.
     * @param dance The reference to the dance object.
     */
    public UserDance(User user, Dance dance) {

        this.user = user;
        this.dance = dance;
    }

}
