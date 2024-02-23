package com.basejava.webapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

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
    public ListSection(@NonNull List<String> items) {
        this.items = items;
    }
    public String toString() {
        return items.toString();
    }
}
