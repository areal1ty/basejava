package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
public record Link(@Getter String title, @Getter String url) {
    @NonNull
    public Link {
    }

    public String toString() {
        return "Link[" + title + ", " + url + "]";
    }

}
