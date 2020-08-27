package com.enigma.restservice.entities;

import javax.persistence.*;

@Table(name = "unit")

@Entity
public class Unit extends AbstractEntity {


    @Column (name = "unit_name")
    private String name;

    private String description;

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + getName() +
                ", name='" + name + '\'' +
                '}';
    }

    public Unit(String name, String description) {

        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
