<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>

<table>
    <c:forEach var="listing" items="${listings}">
        <tr>
            <td><a href="${pageContext.request.contextPath}/listing/details/${listing.id}">${listing.id}</a></td>
            <td>${listing.title}</td>
            <td>${listing.description}</td>
            <td>${listing.location}</td>
            <td>${listing.price}</td>
            <td>${listing.score}</td>
            <td>${listing.created_at}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
