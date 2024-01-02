package com.basejava.webapp.model;

import lombok.Getter;

public enum ContactsTypes {
    LOCATION("Место проживания"),
    PHONE_NUMBER("Номер телефона"),
    TELEGRAM("Telegram"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDIN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("StackOverFlow"),
    SOCIAL_MEDIA("Личный профиль");

    @Getter
    private final String title;

    ContactsTypes(String title) {
        this.title = title;
    }
}
