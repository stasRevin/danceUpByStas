package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLesson> users = new ArrayList<>();

    public Lesson(LocalTime startTime, LocalTime endTime, Location location, LocalDate date) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.date = date;
    }

    public Lesson() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return getId() == lesson.getId() &&
                Objects.equals(getStartTime(), lesson.getStartTime()) &&
                Objects.equals(getEndTime(), lesson.getEndTime()) &&
                Objects.equals(getDate(), lesson.getDate()) &&
                Objects.equals(getLocation(), lesson.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartTime(), getEndTime(), getDate(), getLocation());
    }
}
