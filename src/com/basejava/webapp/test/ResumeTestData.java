package com.basejava.webapp.test;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Григорий Кислин");
        testResume.addContact(ContactsTypes.LOCATION, "Россия, г. Санкт-Петербург");
        testResume.addContact(ContactsTypes.PHONE_NUMBER, "+7 (921) 855 0482");
        testResume.addContact(ContactsTypes.EMAIL, "gkislin@yandex.ru");
        testResume.addContact(ContactsTypes.TELEGRAM, "@gkislin");
        testResume.addContact(ContactsTypes.SKYPE, "grigory.kislin");
        testResume.addContact(ContactsTypes.SOCIAL_MEDIA, "https://habr.com/ru/users/gkislin/");
        testResume.addContact(ContactsTypes.STACKOVERFLOW, "http://stackoverflow.com/users/548473/gkislin");
        testResume.addContact(ContactsTypes.GITHUB, "https://github.com/gkislin/`");
        testResume.addContact(ContactsTypes.LINKEDIN, "https://www.linkedin.com/in/gkislin");

        List<String> personal = new ArrayList<>() {
            {
                add("аналитический склад ума");
                add("сильная логика");
                add("креативность");
                add("инициативность");
            }
        };

        List<String> positions = new ArrayList<>() {
            {
                add("Архитектор");
                add("Технический лидер");
                add("Организатор команды Java-разработки");
            }};

        List<String> achievements = new ArrayList<>() {
            {
                add("Организация команды и успешная реализация аутсорсинговых проектов");
                add("С 2014 года: проведение стажировок и корпоративных обучений, более 4000 выпускников. Создание обучающих проектов");
            }
        };

        List<String> qualifications = new ArrayList<>() {
            {
                add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
                add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
                add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
                add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
            }
        };

        Content experience1 = new Content("Java Online Projects", "http://javaops.ru/","Организация команды бэкэнда и фронтэнда, архитектура, постановка и" +
                " ведение задач, успешная реализация проектов для сторонних заказчиков", "Приложение «MiniJira»: реализация багтрекинговой системы для обучения и " +
                "внутреннего использования с настраиваемым графом перехода состояний и древовидной организацией проктов, спринтов и задач. Spring Boot, Thymeleaf, Postgres, " +
                "REST OpenAPI, OAuth2, Mapstruct, Liquibase", LocalDate.of(2018, 1, 1), LocalDate.now());

        Content education1 = new Content("Санкт-Петербургский национальный исследовательский университет информации и оптики", "http://www.ifmo.ru/",
                "Инженер", "программист Fortran, C", LocalDate.of(1987, 1, 1), LocalDate.of(1993, 1, 1));

        List<Content> experience = new ArrayList<>() {
            {
                add(experience1);
            }
        };

        List<Content> education = new ArrayList<>() {
            {
                add(education1);
            }
        };

        Unit personalUnit = new ListUnit(personal);
        Unit positionsUnit = new ListUnit(positions);
        Unit achievementsUnit = new ListUnit(achievements);
        Unit qualificationsUnit = new ListUnit(qualifications);

        Unit experienceUnit = new ContentUnit(experience);
        Unit educationUnit = new ContentUnit(education);

        testResume.addUnit(UnitTypes.PERSONAL,personalUnit);
        testResume.addUnit(UnitTypes.OBJECTIVE, positionsUnit );
        testResume.addUnit(UnitTypes.ACHIEVEMENTS, achievementsUnit);
        testResume.addUnit(UnitTypes.QUALIFICATIONS, qualificationsUnit);
        testResume.addUnit(UnitTypes.EXPERIENCE, experienceUnit);
        testResume.addUnit(UnitTypes.EDUCATION, educationUnit);

        System.out.println(testResume.getFullName());
        for (Map.Entry<ContactsTypes, String> contact: testResume.getContacts().entrySet()) {
            System.out.println(contact);
        }

        for (Map.Entry<UnitTypes, Unit> unit : testResume.getUnits().entrySet()) {
            System.out.println(unit);
        }
    }
}
