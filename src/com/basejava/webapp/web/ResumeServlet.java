package com.basejava.webapp.web;

import com.basejava.webapp.Configuration;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
    super.init(config);
    storage = Configuration.getInstance().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "add":
                r = Resume.EMPTY_RESUME;
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    Section section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                        case ACHIEVEMENTS:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY_SECTION;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection orgSection = (OrganizationSection) section;
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY_ORGANIZATION);
                            if (orgSection != null) {
                                for (Organization org : orgSection.getOrganizations()) {
                                    List<Period> emptyFirstPeriods = new ArrayList<>();
                                    emptyFirstPeriods.add(Period.EMPTY_PERIOD);
                                    emptyFirstPeriods.addAll(org.getPeriods());
                                    emptyFirstOrganizations.add(new Organization(org.getWebsite(), emptyFirstPeriods));
                                }
                            }
                            section = new OrganizationSection(emptyFirstOrganizations);
                            break;
                    }
                    r.addSection(type, section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        final boolean isNotCreated = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isNotCreated) {
            if (DateUtil.HtmlUtil.isEmpty(fullName)) {
                response.sendRedirect("resume");
                return;
            }
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (DateUtil.HtmlUtil.isEmpty(value)) {
                r.getContacts().remove(type);
            } else {
                r.addContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (DateUtil.HtmlUtil.isEmpty(value) && values.length <= 1) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                    case ACHIEVEMENTS:
                    case QUALIFICATIONS:
                        String[] splitValues = value.split("\\n");
                        List<String> modifiedValues = new ArrayList<>();
                        for (String splitValue : splitValues) {
                            if (!DateUtil.HtmlUtil.isEmpty(splitValue.trim())) {
                                modifiedValues.add(splitValue);
                            }
                        }
                        if (modifiedValues.isEmpty()) {
                            response.sendRedirect("resume");
                        } else {
                            r.addSection(type, new ListSection(modifiedValues.toArray(new String[0])));
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> orgs = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!DateUtil.HtmlUtil.isEmpty(name)) {
                                List<Period> periods = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] startDates = request.getParameterValues(pfx + "startDate");
                                String[] endDates = request.getParameterValues(pfx + "endDate");
                                String[] titles = request.getParameterValues(pfx + "title");
                                String[] descriptions = request.getParameterValues(pfx + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    if (!DateUtil.HtmlUtil.isEmpty(titles[j])) {
                                        periods.add(new Period(DateUtil.parseDate(startDates[j]), DateUtil.parseDate(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                orgs.add(new Organization(new Link(name, urls[i]), periods));
                            }
                        }
                        r.addSection(type, new OrganizationSection(orgs));
                        break;
                }
            }
        }
        if (isNotCreated) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
}
}
