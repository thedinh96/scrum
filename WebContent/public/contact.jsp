<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/coin-slider.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/public/js/jquery.validate.min.js"></script>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
     <%
      String msg = request.getParameter("msg");
      String err = request.getParameter("err");
      
      String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
      if("1".equals(msg)){
      %>
      <script type="text/javascript">
      	alert("Gởi Liên Hệ Thành Công");
      </script>
      <% } else if("1".equals(err)){  %>
	  <script type="text/javascript">
      	alert("Gởi Liên Hệ Thất Bại");
      </script>
	  <% } %>
	  
      <h2><span style="color: red">Liên hệ</span></h2>
      <div class="clr"></div>
      <p style="color: red">Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article" style="background: #E9EBEE;">
      <h2 style="color: green;">Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      <form action="" method="post" id="sendemail" class="form">
        <ol>
          <li>
            <label for="name" style="color: blue;">Họ tên (*)</label>
            <input id="name" value="<%if (name != null) out.print(name); %>" name="name" class="text" />
          </li>
          <li>
            <label for="email" style="color: blue;">Email (*)</label>
            <input id="email" value="<%if (email != null) out.print(email); %>" name="email" class="text" />
          </li>
          <li>
            <label for="website" style="color: blue;">Website</label>
            <input id="website" value="<%if (website != null) out.print(website); %>" name="website" class="text" />
          </li>
          <li>
            <label for="message" style="color: blue;">Nội dung(*)</label>
            <textarea id="message" name="message" rows="8" cols="50"><%if (message != null) out.print(message); %></textarea>
          </li>
          <li>
            <input value="Gửi liên hệ" style="background:#FF561B;color:#fff;border-radius: 3px;margin-top: 14px;width: 88px;height: 29px;border: none;" type="submit" name="send">
            <div class="clrb"></div>
          </li>
        </ol>
      </form>
    </div>
  </div>
  
  <script type="text/javascript">
		$(document).ready(function(){
			$('.form').validate({
				rules:{
					name:{
						required:true,
					},
					email:{
						required: true,
						email: true,
					},
					message:{
						required:true,
					},
				},
				messages:{
					name:{
						required: 'Vui lòng nhập Họ Tên',
					},
					email:{
						required: 'Vui lòng nhập Email',
						email: 'Vui lòng nhập email đúng định dạng',
					},
					message:{
						required:'Vui lòng nhập nội dung',
					},
				},
			});
		});
	</script>
  <script type="text/javascript">
  	
  </script>
  <div class="sidebar">
  <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
    document.getElementById("contact").classList.add('active');
</script>
<%@ include file="/template/public/inc/footer.jsp" %>
