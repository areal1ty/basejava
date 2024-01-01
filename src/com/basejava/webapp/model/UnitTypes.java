package com.basejava.webapp.model;

import lombok.Getter;

public enum UnitTypes {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENTS("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    @Getter
    private final String title;

    UnitTypes(String title) {
        this.title = title;
    }

}
