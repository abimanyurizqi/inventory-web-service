package com.enigma.restservice.models.unit;

import com.enigma.restservice.validation.annotations.MinLength;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

public class UnitModel {


    private Integer id;

    @MinLength(1)
    @NotBlank(message = "{name.notblank}")
    private String name;

    @MinLength(3)
    @NotBlank(message = "{description.notblank}")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnitModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
