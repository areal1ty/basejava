<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.OrganizationSection" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
<%@ page import="com.basejava.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<section> <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <h1>Имя :</h1> <dl>
    <textarea type="text" name="fullName" cols="55">${resume.fullName}</textarea>
</dl> <h2>Контакты:</h2> <c:forEach var="type" items="<%=ContactType.values()%>">
    <dl> <dt>${type.title}</dt>
        <dd><textarea type="text" name="${type.name()}" cols="30">${resume.getContact(type)}</textarea></dd>
    </dl> </c:forEach>
    <hr> <c:forEach var="type" items="<%=SectionType.values()%>">
    <c:set var="section" value="${resume.getSection(type)}"/>
    <jsp:useBean id="section" type="com.basejava.webapp.model.Section"/>
    <h2><a>${type.title}</a>
    </h2> <c:choose> <c:when test="${type=='OBJECTIVE' || type=='PERSONAL' || type=='QUALIFICATIONS' || type=='ACHIEVEMENTS'}">
    <textarea name='${type}' cols="75" rows="5"><%=String.join("\n", ((ListSection) section).getItems())%></textarea>
</c:when> <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}"> <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>" varStatus="counter">
    <dl> <dt>Название учереждения:</dt>
        <dd><textarea name='${type}' cols="100" >${org.website.title}</textarea></dd>
    </dl> <dl> <dt>Сайт учереждения:</dt>
    <dd><input type="text" name='${type}url' size="100" value="${org.website.url}"></dd>
</dl> <br> <div style="margin-left: 30px"> <c:forEach var="period" items="${org.periods}">
    <jsp:useBean id="period" type="com.basejava.webapp.model.Period"/> <dl> <dt>С:</dt>
    <dd><textarea type="text" name="${type}${counter.index}startDate" cols="10">${DateUtil.format(period.getDateOfStart())}</textarea></dd>
</dl> <dl> <dt>По:</dt> <dd><textarea name="${type}${counter.index}endDate" cols="10">${DateUtil.format(period.getDateOfEnd())}</textarea></dd>
</dl> <dl> <dt>Должность:</dt> <dd><textarea name='${type}${counter.index}title' cols="75">${period.title}</textarea></dd>
</dl> <dl> <dt>Описание:</dt> <dd><textarea name="${type}${counter.index}description" rows="5" cols="75">${period.description}</textarea></dd>
</dl> </c:forEach> </div> </c:forEach> </c:when> </c:choose> </c:forEach>
    <button type="submit">Сохранить</button>
    <button type="button" onclick="window.history.back()">Отменить</button>
</form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>