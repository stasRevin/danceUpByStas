package com.danceUpByStas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Transient
    @Column(name="time_scheduled")
    private LocalDateTime timeScheduled;

    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_lesson_role_id"))
    private Role role;

    public UserLesson() {

    }

    public UserLesson(User user, Lesson lesson, Role role) {

        this.user = user;
        this.lesson = lesson;
        this.role = role;
    }

}
