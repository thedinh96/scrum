<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>ShineMusic</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath() %>/template/public/css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/public/css/coin-slider.css" />
<link rel="shortcut icon" href="<%=request.getContextPath()%>/template/public/images/ico.jpg" type="image/x-icon"/>
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
      	<img class="imgHeader" style="width: 180px; height: 60px; padding-top: 10px;" src="<%=request.getContextPath()%>/template/public/images/ico.jpg" />
        <h1><a class="logoHeader" style="color: red" href="<%=request.getContextPath()%>/public/index">ShineMusic <small></small></a></h1>
      </div>
      <div class="menu_nav">
        <ul>
          <li  id="public"><a href="<%=request.getContextPath()%>/public/index"><b>Trang chủ</b></a>
          <%
  			if (session.getAttribute("userInfo") == null) {
  		  %>
  			<li><a href="<%=request.getContextPath()%>/auth/login"><b>Đăng Nhập</spban></a></li>
  		  <%
  		  	} else {
  	  		User objUser = (User)	session.getAttribute("userInfo");  
  		  %> 
  			<li  class="active"><a href="<%=request.getContextPath()%>/admin/index"><b><%=objUser.getUsername() %></b></a></li>
          <%
            }
  		  %>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="slider">
        <div id="coin-slider"><a href=""><img src="<%=request.getContextPath() %>/files/slide1.jpg" width="935" height="307" alt="" /></a> <a href=""><img src="<%=request.getContextPath() %>/files/slide2.jpg" width="935" height="307" alt="" /></a> <a href=""><img src="<%=request.getContextPath() %>/files//slide3.jpg" width="935" height="307" alt="" /></a></div>
        <div class="clr"></div>
      </div>
      <div class="clr"></div>
    </div>
  </div>
  <div class="content">