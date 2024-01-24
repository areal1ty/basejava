package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public final class Period implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private final LocalDate dateOfStart;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private final LocalDate dateOfEnd;
    private final String title;
    private final String description;

    public Period(@NonNull LocalDate dateOfStart, @NonNull LocalDate dateOfEnd, String title, String description) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.title = title;
        this.description = description;
    }
}
