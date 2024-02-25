<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.OrganizationSection" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
<%@ page import="com.basejava.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h1>Имя:</h1>
        <dl>
            <label>
                <input type="text" name="fullName" size=55 value="${resume.fullName}">
            </label>
        </dl>
        <h2>Контакты:</h2>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><label>
                    <input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}">
                </label></dd>
            </dl>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.basejava.webapp.model.Section"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <label>
                        <input type='text' name='${type}' size=75 value='<%=section%>'>
                    </label>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <label>
                        <textarea name='${type}' cols=75 rows=5><%=section%></textarea>
                    </label>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <label>
                        <textarea name='${type}' cols=75 rows=5><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
                    </label>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>"
                               varStatus="counter">
                        <dl>
                            <dt>Организация:</dt>
                            <dd><label>
                                <input type="text" name='${type}' size=100 value="${org.website.title}">
                            </label></dd>
                        </dl>
                        <dl>
                            <dt>Сайт:</dt>
                            <dd><label>
                                <input type="text" name='${type}url' size=100 value="${org.website.url}">
                            </label></dd>
                        </dl>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="periods" items="${org.periods}">
                                <jsp:useBean id="periods" type="com.basejava.webapp.model.Period"/>
                                <dl>
                                    <dt>С:</dt>
                                    <dd>
                                        <label>
                                            <input type="text" name="${type}${counter.index}dateOfStart" size=10
                                                   value="<%=DateUtil.format(periods.getDateOfStart())%>" placeholder="MM/yyyy">
                                        </label>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>До:</dt>
                                    <dd>
                                        <label>
                                            <input type="text" name="${type}${counter.index}dateOfEnd" size=10
                                                   value="<%=DateUtil.format(periods.getDateOfEnd())%>" placeholder="MM/yyyy">
                                        </label>
                                </dl>
                                <dl>
                                    <dt>Позиция:</dt>
                                    <dd><label>
                                        <input type="text" name='${type}${counter.index}title' size=75
                                                   value="${periods.title}">
                                    </label>
                                </dl>
                                <dl>
                                    <dt>Описание:</dt>
                                    <dd><label>
                                            <textarea name="${type}${counter.index}description" rows=5 cols=75>${periods.description}</textarea>
                                    </label></dd>
                                </dl>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отмена</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>