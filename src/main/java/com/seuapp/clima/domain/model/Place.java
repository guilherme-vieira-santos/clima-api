package com.seuapp.clima.domain.model;

public record Place(String value) {

    public Place {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Localidade não pode ser vazia");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}