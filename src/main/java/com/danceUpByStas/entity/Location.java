package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Location")
@Table(name = "Location")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column
    private String name;
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String city;
    @Column
    private String state;
    @Column(name = "lat")
    private Double latitude;
    @Column(name = "lon")
    private Double longtitude;
    @Column
    private String postalCode;

    public Location() {

    }
}
