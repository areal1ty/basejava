package com.basejava.webapp.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Organization {
    private final Link website;
    @NonNull
    private final String title;
    private final List<Period> periods;
    @NonNull
    private final String position;
    private final String description;

    public Organization(String title, String url, String position, String desc, List<Period> periods) {
        this.title = title;
        this.website = new Link(title, url);
        this.position = position;
        this.description = desc;
        this.periods = periods;
    }

}
