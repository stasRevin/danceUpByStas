package com.danceUpByStas.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This entity was created with the purpose of accommodation for the future expansion.
 * Some extra fields like the primary key or timestamp may be added to provide
 * more data about the relationship between User and Role entities.
 * @author srevin
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

    /**
     * The empty constructor.
     */
    public UserRole() {

    }

    /**
     * The parameterized constructor.
     * @param user The user object reference.
     * @param role The role object reference.
     */
    public UserRole(User user, Role role) {

        this.user = user;
        this.role = role;
    }

    /**
     * This method returns the user object reference.
     * @return user The user object reference.
     */
    public User getUser() {
        return user;
    }

    /**
     * This method sets the value to the user object.
     * @param user The user object reference.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This method returns the role object reference.
     * @return role The role object reference.
     */
    public Role getRole() {
        return role;
    }

    /**
     * This method sets the value to the role object.
     * @param role The user role
     */
    public void setRole(Role role) {
        this.role = role;
    }

}
