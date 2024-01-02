package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */

@EqualsAndHashCode
public class Resume implements Comparable<Resume> {

    // Unique identifier
    @NonNull
    @Getter
    private final String uuid;
    @NonNull
    @Getter
    private final String fullName;
    @Getter
    private final Map<ContactsTypes, String> contacts = new EnumMap<>(ContactsTypes.class);
    @Getter
    private final Map<UnitTypes, Unit> units = new EnumMap<>(UnitTypes.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

   public void addContact(ContactsTypes type, String value) {
        contacts.put(type, value);
   }

   public void addUnit(UnitTypes type, Unit unit) {
        units.put(type, unit);
    }

    @Override
    public String toString() {
        return uuid + "(" + fullName + ")";
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}