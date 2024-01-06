package com.basejava.webapp.model;

import lombok.Getter;

public enum SectionType {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENTS("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    @Getter
    private final String title;

    SectionType(String title) {
        this.title = title;
    }

}
