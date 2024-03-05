package com.basejava.webapp.model;

import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
public class Organization implements Serializable{
    public static final Organization EMPTY_ORGANIZATION = new Organization("", "", Period.EMPTY_PERIOD) ;
    @Serial
    private static final long serialVersionUID = 1L;
    private Link website;
    @NonNull
    private String title;
    private List<Period> periods;

    public Organization() {}

    public Organization(String title, String url, Period... periods) {
        this(new Link(title, url), Arrays.asList(periods));
    }

    public Organization(Link website, List<Period> periods) {
        this.website = website;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Organization(" + website + "," + periods + ')';
    }
}
