package com.danceUpByStas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This entity is created to reflect the many-to-many relationship between User and Lesson.
 * Even though, there are only 2 columns in this entity, the set-up is designed to accomodate
 * the potential expansion of the entity by adding some other fields later on. Some examples
 * may include a primary key or a timestamp to record when the association between the user and
 * the lesson took place.
 */
@Entity(name = "UserLesson")
@Table(name = "User_Lesson")
@Data
public class UserLesson implements Serializable {

    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private Lesson lesson;

    public UserLesson() {

    }

    public UserLesson(User user, Lesson lesson) {

        this.user = user;
        this.lesson = lesson;
    }

}
