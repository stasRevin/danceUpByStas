package com.danceUpByStas.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the Location bean class representing the Location entity.
 * @author srevin
 */
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Double latitude;

    @Column(name = "lon")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Double longtitude;
    @Column
    private String postalCode;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "locations", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    /**
     * The empty constructor.
     */
    public Location() {

    }

    /**
     * The parameterized constructor.
     * @param name The name of the location.
     * @param address1 The first address of the location.
     * @param address2 The second address of the location.
     * @param city The city of this location.
     * @param state The state of this location.
     * @param postalCode The postal code of this location.
     */
    public Location(String name, String address1, String address2, String city, String state, String postalCode) {

        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;

    }
}
