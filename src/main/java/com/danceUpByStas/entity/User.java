package com.danceUpByStas.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getIsDeleted() == user.getIsDeleted() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getAddressOne(), user.getAddressOne()) &&
                Objects.equals(getAddressTwo(), user.getAddressTwo()) &&
                Objects.equals(getCity(), user.getCity()) &&
                Objects.equals(getState(), user.getState()) &&
                Objects.equals(getPostalCode(), user.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getIsDeleted(), getFirstName(), getLastName(), getAddressOne(), getAddressTwo(), getCity(), getState(), getPostalCode());
    }

}
