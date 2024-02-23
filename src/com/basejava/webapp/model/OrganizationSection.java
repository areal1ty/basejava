package com.basejava.webapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrganizationSection extends Section {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Organization> organization;

    public OrganizationSection() {}

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(@NonNull List<Organization> organization) {
        this.organization = organization;
    }

    public String toString() {
        return organization.toString();
    }
}
