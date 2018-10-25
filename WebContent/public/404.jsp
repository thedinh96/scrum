<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>BSong</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/template/public/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/public/css/coin-slider.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/script.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/jquery.validate.min.js"></script>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="">BSong <small>Một dự án khóa JAVA tại VinaEnter Edu</small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li><a href="<%=request.getContextPath()%>/public/index"><b>Trang chủ</b></a>
          <li><a href="<%=request.getContextPath()%>/contact"><b>Liên hệ</b></a></li>
          <%
  			if (session.getAttribute("userInfo") == null) {
  		  %>
  			<li><a href="<%=request.getContextPath()%>/auth/login"><b>Đăng Nhập</spban></a></li>
  		  <%
  		  	} else {
  	  		User objUser = (User)	session.getAttribute("userInfo");  
  		  %> 
  			<li ><a href="<%=request.getContextPath()%>/admin/index"><b><%=objUser.getUsername() %></b></a></li>
          <%
            }
  		  %>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider"><a href="#"><img src="<%=request.getContextPath() %>/files/11.png" width="935" height="395" alt="" /></a></div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">
<%@ include file="/template/public/inc/footer.jsp" %>