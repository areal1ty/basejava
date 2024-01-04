package com.basejava.webapp.model;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

public record Period(@Getter LocalDate dateOfStart, @Getter LocalDate dateOfEnd) {
    public Period(@NonNull LocalDate dateOfStart, @NonNull LocalDate dateOfEnd) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }
}
