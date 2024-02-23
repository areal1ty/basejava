package com.basejava.webapp.test;

import com.basejava.webapp.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
                add("Personal information");
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

        Organization experience1 = new Organization("Organization1", "https://organization11.ru",
                new Period(2005, Month.JANUARY, "position1", "content1"),
                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "description"));

        Organization education1 = new Organization("Education2", "https://education11.ru",
                new Period(2005, Month.JANUARY, "position1", "content1"),
                new Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "description"));

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

        r.addSection(SectionType.PERSONAL, personalSection);
        r.addSection(SectionType.OBJECTIVE, positionsSection);
        r.addSection(SectionType.ACHIEVEMENTS, achievementsSection);
        r.addSection(SectionType.QUALIFICATIONS, qualificationsSection);
        r.addSection(SectionType.EXPERIENCE, experienceSection);
        r.addSection(SectionType.EDUCATION, educationSection);

        return r;
    }
}
