package com.enigma.restservice.models.item;

import com.enigma.restservice.validation.annotations.MinLength;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.validation.constraints.NotBlank;


public class ItemModel {

    private Integer id;

    @MinLength(3)
    @NotBlank(message = "{name.notblank}")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }



}
