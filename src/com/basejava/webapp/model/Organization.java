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

    public Organization(String title, String url, List<Period> periods) {
        this.title = title;
        this.website = new Link(title, url);
        this.periods = periods;
    }

}
