package com.basejava.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final ListSection EMPTY_SECTION = new ListSection("");
    private List<String> items;

    public ListSection() {}
    public ListSection(String... items) {
        this(Arrays.asList(items));
    }
    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "content cannot be null");
        this.items = items;
    }
    public String toString() {
        return items.toString();
    }
}
