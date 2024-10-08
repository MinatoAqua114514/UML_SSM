<%--
  Created by IntelliJ IDEA.
  User: Lin
  Date: 2024/10/3
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
    <title>Register</title>
  </head>
  
  <body>
  
    <c:if test="${not empty error}">
      <script>
        alert('${error}');
      </script>
    </c:if>

    <c:if test="${not empty success}">
      <script>
        alert('${success}');
      </script>
    </c:if>
  
    <div class="container">
      <h1>Please Register</h1>
      <form action="">
        <div class="form-control">
          <input type="text" required>
          <label>Email</label>
        </div>
  
        <div class="form-control">
          <input type="text" required>
          <label>Username</label>
        </div>
  
        <div class="form-control">
          <input type="password" required>
          <label>Password</label>
        </div>
  
        <button class="btn">Register</button>
  
        <p class="text">Already have an account? <a href="login.jsp">Login</a> </p>
      </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/register.js"></script>
  </body>
  
  </html>
