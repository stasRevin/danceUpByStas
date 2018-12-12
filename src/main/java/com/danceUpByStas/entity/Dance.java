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

/**
 * This is the Dance bean class representing the Dance entity.
 * @author srevin
 */
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

    /**
     * Empty constructor.
     */
    public Dance() {

    }

    /**
     * The Dance object constructor with name and description as parameters.
     * @param name The name of the dance.
     * @param description The description of the dance.
     */
    public Dance(String name, String description) {

        this.name = name;
        this.description = description;
    }

    /**
     * This method returns the name of the dance.
     * @return name The name of the dance.
     */
    @XmlElement
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of the dance.
     * @param name The name of the dance.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the description of the dance.
     * @return description The description of the dance.
     */
    @XmlElement
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description of the dance.
     * @param description The description of the dance.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method overrides the equals method to provide means to determine if two dance objects are equal.
     * @param o The object to which the current Dance object will be compared.
     * @return true or false on whether objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dance)) return false;
        Dance dance = (Dance) o;
        return getId() == dance.getId() &&
                Objects.equals(getName(), dance.getName()) &&
                Objects.equals(getDescription(), dance.getDescription());
    }

    /**
     * This method returns the hash code value of this object.
     * @return hashCode The hash code value of this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }
}
