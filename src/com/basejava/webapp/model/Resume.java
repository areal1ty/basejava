package com.basejava.webapp.model;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */

public class Resume implements Comparable<Resume> {

    // Unique identifier
    @Getter
    private final String uuid;
    @Getter
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid should not be null");
        Objects.requireNonNull(fullName, "uuid should not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return uuid + "(" + fullName + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return (uuid.equals(resume.uuid) && fullName.equals(getFullName()));
    }

    @Override
    public int hashCode() {
        int hashedUuid = uuid.hashCode();
        hashedUuid = 31 * hashedUuid + (fullName.hashCode());
        return hashedUuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}