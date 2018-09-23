package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dance")
@Table(name = "DANCE")
public class Dance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;


    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "dance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDance> users = new ArrayList<>();

    public Dance() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dance(String name, String description) {

        this.name = name;
        this.description = description;
    }


    public List<UserDance> getUsers() {
        return users;
    }

    public void setUsers(List<UserDance> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        return "Dance{" +
                "dance='}" + name + '\'' +
                ", description=" + description +
                '}';
    }


}
