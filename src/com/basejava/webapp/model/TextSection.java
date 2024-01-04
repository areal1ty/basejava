package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
@EqualsAndHashCode(callSuper = true)
public class TextSection extends Section {

    @Getter
    private final String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "text cannot be null");
        this.text = text;
    }

}
