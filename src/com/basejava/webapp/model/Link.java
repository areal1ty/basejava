package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode()
public final class Link {
    @Getter
    private final String title;
    @Getter
    private final String url;

    @NonNull
    public Link(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String toString() {
        return "Link[" + title + ", " + url + "]";
    }

}
