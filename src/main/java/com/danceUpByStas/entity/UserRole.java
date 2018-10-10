package com.danceUpByStas.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This entity was created with the purpose of accomodation for the future expansion.
 * Some extra fields like the primary key or timestamp may be added to provide
 * more data about the relationship between User and Role entities.
 */

@Entity(name = "UserRole")
@Table(name = "User_Role")
public class UserRole implements Serializable {
    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private Role role;

    public UserRole() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRole(User user, Role role) {

        this.user = user;
        this.role = role;
    }

}
