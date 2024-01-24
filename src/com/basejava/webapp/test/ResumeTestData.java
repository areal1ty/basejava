package com.basejava.webapp.test;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        r.addContact(ContactType.LOCATION, "-");
        r.addContact(ContactType.PHONE_NUMBER, "-");
        r.addContact(ContactType.EMAIL, "-");
        r.addContact(ContactType.TELEGRAM, "-");
        r.addContact(ContactType.SKYPE, "-");
        r.addContact(ContactType.SOCIAL_MEDIA, "-");
        r.addContact(ContactType.STACKOVERFLOW, "-");
        r.addContact(ContactType.GITHUB, "-");
        r.addContact(ContactType.LINKEDIN, "-");

        List<String> personal = new ArrayList<>() {
            {
                add("-");
            }
        };

        List<String> positions = new ArrayList<>() {
            {
                add("-");
            }};

        List<String> achievements = new ArrayList<>() {
            {
                add("-");
            }
        };

        List<String> qualifications = new ArrayList<>() {
            {
                add("-");
            }
        };

        Organization experience1 = new Organization("-", "-", List.of());

        Organization education1 = new Organization("-", "-", List.of());

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

        r.addUnit(SectionType.PERSONAL, personalSection);
        r.addUnit(SectionType.OBJECTIVE, positionsSection);
        r.addUnit(SectionType.ACHIEVEMENTS, achievementsSection);
        r.addUnit(SectionType.QUALIFICATIONS, qualificationsSection);
        r.addUnit(SectionType.EXPERIENCE, experienceSection);
        r.addUnit(SectionType.EDUCATION, educationSection);

        return r;
    }

    public static void main(String[] args) {

        Resume testResume = new Resume("Григорий Кислин");
        testResume.addContact(ContactType.LOCATION, "Россия, г. Санкт-Петербург");
        testResume.addContact(ContactType.PHONE_NUMBER, "+7 (921) 855 0482");
        testResume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        testResume.addContact(ContactType.TELEGRAM, "@gkislin");
        testResume.addContact(ContactType.SKYPE, "grigory.kislin");
        testResume.addContact(ContactType.SOCIAL_MEDIA, "https://habr.com/ru/users/gkislin/");
        testResume.addContact(ContactType.STACKOVERFLOW, "http://stackoverflow.com/users/548473/gkislin");
        testResume.addContact(ContactType.GITHUB, "https://github.com/gkislin/`");
        testResume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");

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

        Organization experience1 = new Organization("Java Online Projects", "http://javaops.ru/", List.of(new Period(LocalDate.of(2018, 1, 1), LocalDate.now(), "Организация команды бэкэнда и фронтэнда, архитектура, постановка и" +
                " ведение задач, успешная реализация проектов для сторонних заказчиков","Приложение «MiniJira»: реализация багтрекинговой системы для обучения и " +
                "внутреннего использования с настраиваемым графом перехода состояний и древовидной организацией проктов, спринтов и задач. Spring Boot, Thymeleaf, Postgres, " +
                "REST OpenAPI, OAuth2, Mapstruct, Liquibase" )));

        Organization education1 = new Organization("Санкт-Петербургский национальный исследовательский университет информации и оптики", "http://www.ifmo.ru/",
                List.of(new Period(LocalDate.of(1987, 1, 1), LocalDate.of(1993, 1, 1), "Инженер", "программист Fortran, C")));

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

        testResume.addUnit(SectionType.PERSONAL, personalSection);
        testResume.addUnit(SectionType.OBJECTIVE, positionsSection);
        testResume.addUnit(SectionType.ACHIEVEMENTS, achievementsSection);
        testResume.addUnit(SectionType.QUALIFICATIONS, qualificationsSection);
        testResume.addUnit(SectionType.EXPERIENCE, experienceSection);
        testResume.addUnit(SectionType.EDUCATION, educationSection);

        System.out.println(testResume.getFullName());
        for (Map.Entry<ContactType, String> contact: testResume.getContacts().entrySet()) {
            System.out.println(contact);
        }

        for (Map.Entry<SectionType, Section> unit : testResume.getSections().entrySet()) {
            System.out.println(unit);
        }
    }
}
