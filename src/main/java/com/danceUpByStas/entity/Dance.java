package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Dance")
@Table(name = "DANCE")
@Data
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

    public Dance(String name, String description) {

        this.name = name;
        this.description = description;
    }


}
