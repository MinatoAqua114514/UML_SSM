<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登入</title>
</head>
<body>
    <div>欢迎登录！</div>
    <form onsubmit="handleLogin(event)">
        <label>
            <input type="text" id="username" placeholder="输入您的用户名" required>
        </label>
        <label>
            <input type="password" id="password" placeholder="输入您的密码" required>
        </label>
        <button type="submit">登  录</button>
    </form>
</body>
</html>
