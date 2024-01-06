package com.basejava.webapp.model;

import lombok.Data;
import lombok.NonNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */

@Data
public class Resume implements Comparable<Resume> {

    // Unique identifier
    @NonNull
    private final String uuid;
    @NonNull
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

   public void addContact(ContactType type, String value) {
        contacts.put(type, value);
   }

   public void addUnit(SectionType type, Section section) {
        sections.put(type, section);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}