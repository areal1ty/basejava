package com.basejava.webapp.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */

@Data
@XmlRootElement
public class Resume implements Comparable<Resume>, Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);
    public Resume() {}

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(@NonNull String uuid, @NonNull String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

   public void addContact(ContactType type, String value) {
        contacts.put(type, value);
   }

   public String getContact(ContactType type) {
        return contacts.get(type);
   }

   public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume r = (Resume) o;
        if (uuid.equals(r.uuid)) return true;
        return fullName.equals(r.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}