package com.basejava.webapp.model;

import lombok.Getter;

@Getter
public enum ContactType {
    LOCATION("Место проживания"),
    PHONE_NUMBER("Номер телефона"),
    TELEGRAM("Telegram"),
    SKYPE("Skype") {
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("skype:" + value, value);
    }},
    EMAIL("Email"){
        @Override
        public String toHtml0(String value) {
            return getTitle() + ": " + toLink("mailto:" + value, value);
    }},
    LINKEDIN("LinkedIn"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
        }},
    GITHUB("GitHub"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
    }},
    STACKOVERFLOW("StackOverFlow"){
        @Override
        public String toHtml0(String value) {
            return toLink(value);
    }},
    SOCIAL_MEDIA("Личный профиль"){
        public String toHtml0(String value) {
            return toLink(value);
    }};

    private final String title;

    ContactType(String title) {
        this.title = title;
    }
    protected String toHtml0(String value) {
        return title + ": " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }

    public String toLink(String href) {
        return toLink(href, title);
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

}
