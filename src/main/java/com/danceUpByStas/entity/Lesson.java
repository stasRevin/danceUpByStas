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

    public Lesson(LocalTime startTime, LocalTime endTime, Location location, LocalDate date) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.date = date;
    }

    public Lesson() {

    }

    public void addInstructors(User user) {

        instructors.add(user);
    }

    public void addStudents(User user) {

        students.add(user);

    }

}
