<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${listing.title}</title>
</head>
<body>
    <h3>${listing.title}</h3>
    <p>⭐${listing.score}分~${listing.price}元/日</p>
    <p>位置：${listing.location.province_name} ${listing.location.city_name} ${listing.location.district_name}</p>
    <h3>${listing.description}</h3>
    <table>
        <c:forEach var="evaluate" items="${evaluate}">
        <tr>
            <td>${evaluate.username}</td>
            <td>${evaluate.score}分</td>
            <td>${evaluate.content}</td>
            </c:forEach>
    </table>

    <form action="${pageContext.request.contextPath}/listing/addBook/${listing.id}" method="post">
        <input type="date" name="checkInDate" id="checkInDate" placeholder="入住时间：yyyy-mm-dd">
        <input type="date" name="checkOutDate" id="checkOutDate" placeholder="退房时间：yyyy-mm-dd">
        <button type="submit">预定</button>
    </form>

    <form action="${pageContext.request.contextPath}/listing/addEvaluate/${listing.id}" method="post">
        <input type="text" name="content" id="content" placeholder="评论">
        <button type="submit">提交评论</button>
    </form>

</body>
</html>
