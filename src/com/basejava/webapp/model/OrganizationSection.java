package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class OrganizationSection extends Section {
    @NonNull
    @Getter
    private final List<Organization> organization;

    public OrganizationSection(List<Organization> organization) {
        this.organization = organization;
    }

    public String toString() {
        return organization.toString();
    }
}
