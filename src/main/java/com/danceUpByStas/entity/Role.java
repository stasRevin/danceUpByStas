package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Role")
@Table(name = "Role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> users = new ArrayList<>();

    @Column(name = "name")
    private String name;

    public Role() {

    }

    public  Role(String name) {
        this.name = name;
    }


}
