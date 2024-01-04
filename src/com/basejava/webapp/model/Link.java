package com.basejava.webapp.model;

import lombok.*;

@Data
public final class Link {
    private final String title;
    private final String url;

    @NonNull
    public Link(String title, String url) {
        this.title = title;
        this.url = url;
    }

}
