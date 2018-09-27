package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime startTime;
    @Column(name="end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "fk_Lesson_Location1"))
    private Location location;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLesson> users = new ArrayList<>();

    public Lesson(LocalDateTime startTime, LocalDateTime endTime, Location location) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Lesson() {

    }


}
