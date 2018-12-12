package com.danceUpByStas.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * This is the User bean class representing the User entity.
 * @author srevin
 */
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserDance> dances = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLesson> lessons = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name="User_Location",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")}
    )
    private Set<Location> locations = new HashSet<>();

    /**
     * The empty constructor.
     */
    public User() {

    }


    /**
     * The parameterized constructor.
     * @param username The username of this user.
     * @param password The user password.
     * @param isDeleted The flag of whether the user is deleted.
     * @param firstName The first name.
     * @param lastName The last name.
     * @param addressOne The first address.
     * @param city The city where user resides.
     * @param state The state where user resides.
     * @param postalCode The user's postal code.
     * @param payRate The pay rate.
     * @param photoName The name of user's photo.
     */
    public User(String username, String password, int isDeleted, String firstName, String lastName,
                String addressOne, String city, String state, String postalCode,
                double payRate, String photoName) {

        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressOne = addressOne;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.payRate = payRate;
        this.photoName = photoName;

    }

    /**
     * The method to add teaching location.
     * @param location The teaching location.
     */
    public void addTeachingLocation(Location location) {

        locations.add(location);
        location.getUsers().add(this);
    }

    /**
     * The method to remove teaching location.
     * @param location The teaching location.
     */
    public void removeTeachingLocation(Location location) {

        locations.remove(location);
        location.getUsers().remove(this);

    }
}
