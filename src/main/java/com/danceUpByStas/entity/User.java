package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name="is_deleted")
    private int isDeleted;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="address1")
    private String addressOne;
    @Column(name="address2")
    private String addressTwo;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="postalcode")
    private String postalCode;
    @Column(name="pay_rate")
    private double payRate;
    @Column(name="photo_name")
    private String photoName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDance> dances = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLesson> lessons = new ArrayList<>();

    public User() {

    }

    public User(String username, String password, int isDeleted, String firstName, String lastName,
                String addressOne, String addressTwo, String city, String state, String postalCode,
                double payRate, String photoName) {

        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.payRate = payRate;
        this.photoName = photoName;

    }
}
