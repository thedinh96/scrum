<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/template/auth/signup/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/template/auth/signup/js/jquery.validate.min.js"></script>
	
    <!-- Font Icon -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/template/auth/signup/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/template/auth/signup/css/style.css">
</head>
<body>
	<div class="wrapper" style="background-image: url('<%=request.getContextPath() %>/template/auth/images/bg-01.jpg');">
    <div class="main">
		<%
			String msg = request.getParameter("msg");
			if(msg!=null){
				int num = Integer.parseInt(msg);
				if(num==1){
		%>
		<script type="text/javascript">
			alert("Đăng ký thành công");
		</script>
		<% }} %>
		<%
		String err = request.getParameter("err");
		if(err!=null){ 
				int num = Integer.parseInt(err);
				if(num==1){
		%>
		<script type="text/javascript">
			alert("Có lỗi khi đăng ký");
		</script>
		<% } 
				else if(num==2){
		%>
		<script type="text/javascript">
			alert("Tài khoản đã tồn tại");
		</script>
		<% }} %>
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2>Sign Up</h2>
                        <div class="form-group">
                            <input type="text" class="form-input" name="accountName" id="accountname" required="required" placeholder="Account Name"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="fullName" id="fullname" required="required" placeholder="Full Name""/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="password" id="password" required="required" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="re_password" id="re_password" placeholder="Repeat your password"/>
                        </div>
                        <div class="form-group">
                        </div>
                        <div class="form-group">
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                        </div>
							<script type="text/javascript">
								window.onload = function() {
									var txtPassword = document
											.getElementById("password");
									var txtConfirmPassword = document
											.getElementById("re_password");
									txtPassword.onchange = ConfirmPassword;
									txtConfirmPassword.onkeyup = ConfirmPassword;
									function ConfirmPassword() {
										txtConfirmPassword
												.setCustomValidity("");
										if (txtPassword.value != txtConfirmPassword.value) {
											txtConfirmPassword
													.setCustomValidity("Mật khẩu không khớp");
										}
									}
								}
							</script>
						</form>
                    <p class="loginhere">
                        Have already an account ? <a href="<%=request.getContextPath() %>/auth/login" class="loginhere-link">Login here</a>
                    </p>
                </div>
            </div>
        </section>

    </div>

    <script src="<%=request.getContextPath() %>/template/auth/signup/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/template/auth/signup/js/main.js"></script>
</div>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>