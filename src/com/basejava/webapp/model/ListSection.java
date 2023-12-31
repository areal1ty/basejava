package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ListSection extends Section {
    @NonNull
    @Getter
    private final List<String> items;

    public ListSection(List<String> items) {
        this.items = items;
    }

    public String toString() {
        return items.toString();
    }
}
