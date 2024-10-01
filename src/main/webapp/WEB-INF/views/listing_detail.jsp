<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${listing.title}</title>
    <h3>${listing.title}</h3>
    <p>⭐${listing.score}分~${listing.price}元/日</p>
    <p>位置：${listing.location}</p>
    <h3>${listing.description}</h3>
    <table>
        <c:forEach var="evaluate" items="${evaluate}">
            <tr>
                <td>${evaluate.username}</td>
                <td>${evaluate.score}分</td>
                <td>${evaluate.content}</td>
        </c:forEach>
    </table>

</head>
<body>

</body>
</html>
