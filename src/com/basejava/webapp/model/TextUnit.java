package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
public class TextUnit extends Unit {

    @Getter
    private final String text;

    public TextUnit(String text) {
        Objects.requireNonNull(text, "text cannot be null");
        this.text = text;
    }

}
