package com.enigma.restservice.entities;

import javax.persistence.*;


@Table(name = "item")

@Entity
public class Item extends AbstractEntity {


    private String name;


    @Override
    public String toString() {
        return "Item{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }


    public Item(String name) {
        this.name = name;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
