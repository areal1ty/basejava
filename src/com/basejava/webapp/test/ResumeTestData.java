package com.basejava.webapp.test;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume testResume = new Resume("Григорий Кислин");
        testResume.addContact(ContactsType.LOCATION, "Россия, г. Санкт-Петербург");
        testResume.addContact(ContactsType.PHONE_NUMBER, "+7 (921) 855 0482");
        testResume.addContact(ContactsType.EMAIL, "gkislin@yandex.ru");
        testResume.addContact(ContactsType.TELEGRAM, "@gkislin");
        testResume.addContact(ContactsType.SKYPE, "grigory.kislin");
        testResume.addContact(ContactsType.SOCIAL_MEDIA, "https://habr.com/ru/users/gkislin/");
        testResume.addContact(ContactsType.STACKOVERFLOW, "http://stackoverflow.com/users/548473/gkislin");
        testResume.addContact(ContactsType.GITHUB, "https://github.com/gkislin/`");
        testResume.addContact(ContactsType.LINKEDIN, "https://www.linkedin.com/in/gkislin");

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

        Organization experience1 = new Organization("Java Online Projects", "http://javaops.ru/","Организация команды бэкэнда и фронтэнда, архитектура, постановка и" +
                " ведение задач, успешная реализация проектов для сторонних заказчиков", "Приложение «MiniJira»: реализация багтрекинговой системы для обучения и " +
                "внутреннего использования с настраиваемым графом перехода состояний и древовидной организацией проктов, спринтов и задач. Spring Boot, Thymeleaf, Postgres, " +
                "REST OpenAPI, OAuth2, Mapstruct, Liquibase", List.of(new Period(LocalDate.of(2018, 1, 1), LocalDate.now())));

        Organization education1 = new Organization("Санкт-Петербургский национальный исследовательский университет информации и оптики", "http://www.ifmo.ru/",
                "Инженер", "программист Fortran, C", List.of(new Period(LocalDate.of(1987, 1, 1), LocalDate.of(1993, 1, 1))));

        List<Organization> experience = new ArrayList<>() {
            {
                add(experience1);
            }
        };

        List<Organization> education = new ArrayList<>() {
            {
                add(education1);
            }
        };

        Section personalSection = new ListSection(personal);
        Section positionsSection = new ListSection(positions);
        Section achievementsSection = new ListSection(achievements);
        Section qualificationsSection = new ListSection(qualifications);

        Section experienceSection = new OrganizationSection(experience);
        Section educationSection = new OrganizationSection(education);

        testResume.addUnit(SectionTypes.PERSONAL, personalSection);
        testResume.addUnit(SectionTypes.OBJECTIVE, positionsSection);
        testResume.addUnit(SectionTypes.ACHIEVEMENTS, achievementsSection);
        testResume.addUnit(SectionTypes.QUALIFICATIONS, qualificationsSection);
        testResume.addUnit(SectionTypes.EXPERIENCE, experienceSection);
        testResume.addUnit(SectionTypes.EDUCATION, educationSection);

        System.out.println(testResume.getFullName());
        for (Map.Entry<ContactsType, String> contact: testResume.getContacts().entrySet()) {
            System.out.println(contact);
        }

        for (Map.Entry<SectionTypes, Section> unit : testResume.getSections().entrySet()) {
            System.out.println(unit);
        }
    }
}
