<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
  <head>
    <title>主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
  </head>
  <body>

  <nav class="navbar navbar-default">
    <div class="container-fluid">

      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" onclick="loadPage('${pageContext.request.contextPath}/listing/find_all_listings')">返回主页</a>
      </div>

      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li>
            <form>
              <input type="text" placeholder="省">
              <input type="text" placeholder="市">
              <input type="text" name="district" placeholder="区">
            </form>
          </li>
          <li class="dropdown">
            <a href="${pageContext.request.contextPath}/location/get_provinces" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">地区筛选 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <c:forEach var="province" items="${provinces}">
                <li onclick="cityController(${province})">${province}</li>
                <ul class="dropdown-menu">
                  <c:forEach var="city" items="${cities}">
                    <li onclick="districtController(${city})">${city}</li>
                    <ul class="dropdown-menu">
                      <c:forEach var="district" items="${districts}">
                        <li>${district}</li>
                      </c:forEach>
                    </ul>
                  </c:forEach>
                </ul>
              </c:forEach>
            </ul>
          </li>
          <form class="navbar-form navbar-left">
            <div class="form-group">
              <input type="text" class="form-control" name="key" placeholder="关键词">
            </div>
            <button type="submit" class="btn btn-default" onclick='loadPage("${pageContext.request.contextPath}/listing/search")'>搜索</button>
          </form>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="${pageContext.request.contextPath}/location/get_provinces#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="${pageContext.request.contextPath}/user/show_login">登录</a></li>
              <li><a href="${pageContext.request.contextPath}/user/show_login">登录</a></li>
              <li role="separator" class="divider"></li>
              <li><a>获取帮助</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

  <div id="frame-container">
    <iframe id="content-frame" src="${pageContext.request.contextPath}/listing/find_all_listings">

    </iframe>
  </div>

  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
  </body>
</html>
