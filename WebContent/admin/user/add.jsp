<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err = request.getParameter("err");
        	String username = request.getParameter("username");//không lấy lại pass, cho người dùng nhập lại
        	String fullname = request.getParameter("fullname");
        	if("1".equals(err)){
        		out.print("Có lỗi khi thêm");
        	}
        	if("2".equals(err)){
        		out.print("Tên đăng nhập đã tồn tại");
        	}
        %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" action="" id="form">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="<%if(username!=null) out.print(username); %>" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="text" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Fullname</label>
                                        <input type="text" id="fullname" value="<%if(fullname!=null) out.print(fullname); %>" name="fullname" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>