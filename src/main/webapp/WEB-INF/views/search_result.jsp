<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="listing" items="${listings}">
    <tr>
        <td><a href="${pageContext.request.contextPath}/listing/details/${listing.id}">${listing.id}</a></td>
        <td>${listing.title}</td>
        <td>${listing.description}</td>
        <td>${listing.location.province_name} ${listing.location.city_name} ${listing.location.district_name}</td>
        <td>${listing.price}</td>
        <td>${listing.score}</td>
        <td>${listing.created_at}</td>
    </tr>
</c:forEach>
</body>
</html>
