<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
  <head>
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
  </head>
  <body>

  <div id="guide">
    <div class="back-button" onclick="goBack()"><-返回</div>
    <div class="gui-item" onclick="loadPage('${pageContext.request.contextPath}/listing/find_all_listings')">返回主页（可换图片）</div>
    <div class="gui-item">
      <form action="${pageContext.request.contextPath}/listing/search" method="post">
        <input type="text" name="key" placeholder="关键词">
        <button type="submit">搜索</button><br>
        <input type="text" name="district" placeholder="区名" required>
      </form>
    </div>
    <div class="gui-item">
      <div class="gui-button">个人</div>
      <div>
        <div onclick="loadPage('${pageContext.request.contextPath}/user/show_login')">登录</div>
        <div onclick="loadPage('${pageContext.request.contextPath}/user/show_login')">注册</div>
      </div>
    </div>
  </div>

  <div id="frame-container">
    <iframe id="content-frame" src="${pageContext.request.contextPath}/listing/find_all_listings">

    </iframe>
  </div>

  </body>
</html>
