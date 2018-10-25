<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@page import="model.dao.ContactDao"%>
<%@page import="model.dao.SongDao"%>
<%@page import="model.dao.UserDao"%>
<%@page import="model.dao.catDao"%>
<%@ page language="java" import="java.lang.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Scrum Master</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="<%=request.getContextPath() %>/template/auth/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/css/util.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/template/auth/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('<%=request.getContextPath() %>/template/auth/images/bg-01.jpg');">
			<div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
				<%
					if(request.getParameter("msg")!=null){
				%>
				<p style="color: red;">Sai tên đăng nhập hoặc mật khẩu</p>
				<% } %>
				
				<form class="login100-form validate-form flex-sb flex-w" action="" method="POST">
					<span style="color: blue;" class="login100-form-title p-b-53">
						LogIn
					</span>

					
					<div class="p-t-31 p-b-9">
						<span class="txt1">
							Username
						</span>
					</div>
					<div class="wrap-input100 validate-input" maxlength="30" data-validate = "Nhập	 tên đăng nhập">
						<input class="input100" type="text" name="username" >
						<span class="focus-input100"></span>
					</div>
					
					<div class="p-t-13 p-b-9">
						<span class="txt1">
							Password
						</span>

						<a href="#" class="txt2 bo1 m-l-5">
							Forgot?
						</a>
					</div>
					<div class="wrap-input100 validate-input" maxlength="30" data-validate = "Nhập mật khẩu">
						<input class="input100" type="password" name="password" >
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn">
							Sign In
						</button>
					</div>
										
					<div class="w-full text-center p-t-55">
						<a href="<%=request.getContextPath() %>/public/index" class="txt2 bo1">
							<b style="color: red;font-size: 20px;">Back Public</b>
						</a>
						
						<span class="txt2" style="padding-left: 40px;">
							Not a member?
						</span>

						<a href="<%=request.getContextPath() %>/auth/signup" class="txt2 bo1">
							<b style="color: red;font-size: 20px;"> Sign up now</b>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/template/auth/vendor/validate/jquery.validate.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/bootstrap/js/popper.js"></script>
	<script src="<%=request.getContextPath() %>/template/auth/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/daterangepicker/moment.min.js"></script>
	<script src="<%=request.getContextPath() %>/template/auth/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="<%=request.getContextPath() %>/template/auth/js/main.js"></script>
	

</body>
</html>