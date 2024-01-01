package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ContentUnit extends Unit {
    @NonNull
    @Getter
    private final List<Content> content;

    public ContentUnit(List<Content> content) {
        this.content = content;
    }

    public String toString() {
        return content.toString();
    }
}
