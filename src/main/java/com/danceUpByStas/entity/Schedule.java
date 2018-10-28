package com.danceUpByStas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "Schedule")
@Table(name = "Schedule")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @JsonProperty("startTime")
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @JsonProperty("date")
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_Schedule_User1"))
    private User user;

    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, User user) {

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }

    public Schedule() {

    }

}
