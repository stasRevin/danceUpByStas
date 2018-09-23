package com.danceUpByStas.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

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
