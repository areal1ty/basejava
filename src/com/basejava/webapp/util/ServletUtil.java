package com.basejava.webapp.util;

import com.basejava.webapp.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ServletUtil {
    public boolean isSectionEmpty(String value, String[] values) {
        return DateUtil.HtmlUtil.isEmpty(value) && values.length < 2;
    }

    public void processSection(SectionType type, String value, String[] values, Resume r, HttpServletRequest request) {
        switch (type) {
            case OBJECTIVE, PERSONAL:
                r.addSection(type, new ListSection(List.of(value.split("\\n"))));
            case ACHIEVEMENTS, QUALIFICATIONS:
                r.addSection(type, new ListSection(List.of(value.split("\\n"))));
            case EDUCATION, EXPERIENCE:
                processEducationAndExperience(type, values, r, request);
        }
    }

    private void processEducationAndExperience(SectionType type, String[] values, Resume r, HttpServletRequest request) {
        List<Organization> organizations = new ArrayList<>();
        String[] urls = request.getParameterValues(type.name() + "url");

        for (int i = 0; i < values.length; i++) {
            String name = values[i];

            if (!DateUtil.HtmlUtil.isEmpty(name)) {
                List<Period> periods = new ArrayList<>();
                String pfx = type.name() + i;
                String[] datesOfStart = request.getParameterValues(pfx + "startDate");
                String[] datesOfEnd = request.getParameterValues(pfx + "endDate");
                String[] titles = request.getParameterValues(pfx + "title");
                String[] descriptions = request.getParameterValues(pfx + "description");

                processPeriods(datesOfStart, datesOfEnd, titles, descriptions, periods);
                organizations.add(new Organization(new Link(name, urls[i]), periods));
            }
        }
        r.addSection(type, new OrganizationSection(organizations));
    }

    public void processPeriods(String[] startDates, String[] endDates, String[] titles, String[] descriptions, List<Period> periods) {
        for (int j = 0; j < titles.length; j++) {
            if (!DateUtil.HtmlUtil.isEmpty(titles[j])) {
                periods.add(new Period(DateUtil.parseDate(startDates[j]), DateUtil.parseDate(endDates[j]), titles[j], descriptions[j]));
            }
        }
    }
}
