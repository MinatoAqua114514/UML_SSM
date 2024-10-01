<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2024/10/1
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
  <head>
    <title>INDEX</title>
  </head>
  <body>
<table>
    <c:forEach var="listing" items="${listings}">
      <tr>
        <td>${listing.id}</td>
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
