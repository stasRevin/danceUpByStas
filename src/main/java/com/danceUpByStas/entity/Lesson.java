package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Lesson")
@Table(name = "Lesson")
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name="start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date startTime;
    @Column(name="end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date endTime;

    @ManyToOne
    @JoinColumn(name = "location_id", foreignKey = @ForeignKey(name = "fk_Lesson_Location1"))
    private Location location;

}
