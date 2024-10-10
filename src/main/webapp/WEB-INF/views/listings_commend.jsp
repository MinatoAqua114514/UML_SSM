<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>民宿列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>

    <table>
        <c:forEach var="listing" items="${listings}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/listing/details/${listing.id}">${listing.id}</a></td>
                <td>${listing.title}</td>
                <td>${listing.description}</td>
                <td><p>位置：${listing.location.province_name}${listing.location.city_name}${listing.location.district_name}</p></td>
                <td>${listing.price}</td>
                <td>${listing.score}</td>
                <td>${listing.created_at}</td>
            </tr>
        </c:forEach>
    </table>


    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>
