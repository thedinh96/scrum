<%@page import="model.dao.ContactDao"%>
<%@page import="model.dao.SongDao"%>
<%@page import="model.dao.UserDao"%>
<%@page import="model.dao.catDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>TRANG QUẢN TRỊ VIÊN</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
        <%
         	catDao objcat = (catDao)request.getAttribute("objCat");
	        ContactDao objContact = (ContactDao)request.getAttribute("objContact");
	        SongDao objSong = (SongDao)request.getAttribute("objSong");
	        UserDao objUse = (UserDao)request.getAttribute("objUser");
	        User userLogin =(User) session.getAttribute("userInfo");
         %>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/cat/index" title="">Quản lý danh mục</a></p>
                        <p class="text-muted">Có <%=objcat.NumberOfItem() %> danh mục</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/song/index" title="">Quản lý bài hát</a></p>
                        <p class="text-muted">Có <%=objSong.NumberOfItem() %> bài hát</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/user/index" title="">Quản lý người dùng</a></p>
                      	<%
                      		if("admin".equals(userLogin.getUsername())){
                      	%>
                        <p class="text-muted">Có <%=objUse.NumberOfItem() %> người dùng</p>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>