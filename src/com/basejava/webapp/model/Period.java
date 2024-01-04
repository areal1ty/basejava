package com.basejava.webapp.model;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

public record Period(@Getter LocalDate dateOfStart, @Getter LocalDate dateOfEnd, String title, String description) {
    public Period(@NonNull LocalDate dateOfStart, @NonNull LocalDate dateOfEnd, String title, String description) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.title = title;
        this.description = description;
    }
}
