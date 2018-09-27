package com.danceUpByStas.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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
