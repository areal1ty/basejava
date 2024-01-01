package com.basejava.webapp.model;

import lombok.Getter;

public enum ContactsTypes {
    LOCATION("Место проживания"),
    PHONE_NUMBER("Номер телефона"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow"),
    HOME_PAGE("Личный профиль");

    @Getter
    private final String title;

    ContactsTypes(String title) {
        this.title = title;
    }
}
