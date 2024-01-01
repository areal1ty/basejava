package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDate;

@EqualsAndHashCode
public class Content {
    private final Link link;
    @NonNull
    private final LocalDate dateOfStart;
    @NonNull
    private final LocalDate dateOfEnd;
    @NonNull
    private final String position;
    private final String desc;

    public Content(String title, String url, String position, String desc, LocalDate dateOfStart, LocalDate dateOfEnd) {
        this.link = new Link(title, url);
        this.position = position;
        this.desc = desc;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
    }

    @Override
    public String toString() {
        return "Content[" +
                "Link = " + link +
                ", dateOfStart = " + dateOfStart +
                ", dateOfEnd = " + dateOfEnd +
                ", position = " + position +
                "(desc = " + desc + ")]";

    }
}
