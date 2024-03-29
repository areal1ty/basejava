package com.basejava.webapp.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public final class Link implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String url;

    public Link(String title, String url) {
        this.title = title;
        this.url = url;
    }

}
