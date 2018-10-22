package com.danceUpByStas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.*;

@XmlRootElement(name = "dance")
@Entity(name = "Dance")
@Table(name = "Dance")
@Data
public class Dance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @XmlTransient
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Transient
    @OneToMany(mappedBy = "dance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDance> users = new ArrayList<>();

    public Dance() {

    }

    public Dance(String name, String description) {

        this.name = name;
        this.description = description;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dance)) return false;
        Dance dance = (Dance) o;
        return getId() == dance.getId() &&
                Objects.equals(getName(), dance.getName()) &&
                Objects.equals(getDescription(), dance.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
