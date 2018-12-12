package com.danceUpByStas.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This is the Schedule bean class representing the Schedule entity.
 * @author srevin
 */
@Entity(name = "Schedule")
@Table(name = "Schedule")
@Data
public class Schedule implements Comparable<Schedule> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @JsonProperty("startTime")
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_Schedule_User1"))
    private User user;

    /**
     * The parameterized Schedule.
     * @param date The date of this schedule.
     * @param startTime The starting time of this schedule.
     * @param endTime The end time of this schedule.
     * @param user The reference to the user object.
     */
    public Schedule(LocalDate date, LocalTime startTime, LocalTime endTime, User user) {

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
    }

    /**
     * The empty constructor.
     */
    public Schedule() {

    }

    /**
     * The method to compare two different schedules.
     * @param anotherSchedule
     * @return
     */
    public int compareTo(Schedule anotherSchedule) {

        int startTimeComparison = this.startTime.compareTo(anotherSchedule.getStartTime());
        if (startTimeComparison != 0)
            return startTimeComparison;

        return 0;
    }

}
