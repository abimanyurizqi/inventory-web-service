package com.enigma.restservice.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum TypeTransaction {

    BUYING("BUYING"),
    SELLING("SELLING");

    private final String type;

    TypeTransaction(String type) {
        this.type = type;
    }

    @JsonValue
    String toValue() {
        return type;
    }

    @JsonCreator
    public static TypeTransaction fromValue(String type) {
        for (TypeTransaction typeTransaction : values()) {
            if (typeTransaction.type.equalsIgnoreCase(type)) {
                return typeTransaction;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + type + ", Allowed values are " + Arrays.toString(values()));
    }
}
