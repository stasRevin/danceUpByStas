package com.danceUpByStas.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Lesson bean class representing the Lesson entity.
 * @author srevin
 */
@Entity(name = "Lesson")
@Table(name = "Lesson")
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name="start_time")
    private LocalTime startTime;
    @Column(name="end_time")
    private LocalTime endTime;

    @Column (name="date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "fk_Lesson_Location1"))
    private Location location;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLesson> users = new ArrayList<>();

    @Transient
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> instructors = new ArrayList<>();

    @Transient
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> students = new ArrayList<>();

    /**
     * The parameterized constructor.
     * @param startTime The start time of the lesson.
     * @param endTime The end time of the lesson.
     * @param location The location of the lesson.
     * @param date The date of the lesson.
     */
    public Lesson(LocalTime startTime, LocalTime endTime, Location location, LocalDate date) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.date = date;
    }

    /**
     * The empty constructor.
     */
    public Lesson() {

    }

    /**
     * This method will add users who have instructor roles to the list of instructors.
     * @param user The user who has instructor role.
     */
    public void addInstructors(User user) {

        instructors.add(user);
    }

    /**
     * This method will add users who have student roles to the list of students.
     * @param user The user who has user student role.
     */
    public void addStudents(User user) {

        students.add(user);

    }

}
