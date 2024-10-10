<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">

<head>
  <title>主页</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
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

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- 左侧Logo -->
      <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/redirect/index">
          <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-house"
               viewBox="0 0 16 16">
            <path
                    d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5z">
            </path>
          </svg>
        </a>
      </div>

      <!-- 中间表单 -->
      <div class="collapse navbar-collapse">
        <form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath}/listing/search" method="post">
          <div class="form-group">
            <select class="form-control" name="province" id="provinceSelect" onchange="loadCities()">
              <option value="">省</option>
              <!-- 显示省的数据 -->
            </select>
          </div>
          <div class="form-group">
            <select class="form-control" name="city" id="citySelect" onchange="loadDistricts()">
              <option value="">市</option>
              <!-- 显示省对应的市的数据 -->
            </select>
          </div>
          <div class="form-group">
            <select class="form-control" name="district" id="districtSelect">
              <option value="">区</option>
              <!-- 显示省对应的市下对应的区的数据 -->
            </select>
          </div>
          <input type="text" name="key" id="keyInput">
          <button type="submit" class="btn btn-default">搜索</button>
        </form>
      </div>

      <!-- 右侧用户头像和下拉框 -->
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor"
                 class="bi bi-person-circle" viewBox="0 0 16 16">
              <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"></path>
              <path fill-rule="evenodd"
                    d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1">
              </path>
            </svg>
          </a>
          <ul class="dropdown-menu">
            <li><a href="${pageContext.request.contextPath}/redirect/login">登录</a></li>
            <li><a href="${pageContext.request.contextPath}/redirect/register">注册</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>

  <div id="frame-container">
    <iframe id="content-frame" src="${pageContext.request.contextPath}/listing/find_all_listings"></iframe>
  </div>


  <script src="${pageContext.request.contextPath}/js/loadlocation.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>

</html>