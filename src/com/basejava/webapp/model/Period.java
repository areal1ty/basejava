package com.basejava.webapp.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public final class Period {
    private final LocalDate dateOfStart;
    private final LocalDate dateOfEnd;
    private final String title;
    private final String description;

    public Period(@NonNull LocalDate dateOfStart, @NonNull LocalDate dateOfEnd, String title, String description) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.title = title;
        this.description = description;
    }
}
