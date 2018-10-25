<%@page import="util.DefineUtil"%>
<%@page import="model.dao.UserDao"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err = request.getParameter("err");
        	String msg = request.getParameter("msg");
        	if("1".equals(msg)){
        %>
        <div class="alert alert-success" role="alert">
			<p>Thêm thành công</p>
		</div>
		<% } if("2".equals(msg)){ %>
        <div class="alert alert-success" role="alert">
			<p>Sửa người dùng thành công</p>
		</div>
		<% } if("3".equals(msg)){ %>
        <div class="alert alert-success" role="alert">
			<p>Xóa người dùng thành công</p>
		</div>
		<% } if("1".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Có lỗi khi thêm</p>
		</div>
		<% } if("2".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Id không tồn tại</p>
		</div>
		<% } if("3".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Xóa người dùng thất bại</p>
		</div>
		<% } if("4".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Bạn không có quền thêm</p>
		</div>
		<% } if("5".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Bạn không có quền sửa</p>
		</div>
		<% } if("6".equals(err)){ %>
		<div class="alert alert-danger" role="alert">
			<p>Bạn không có quền xóa</p>
		</div>
		<% } %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>User</th>
                                        <th>Fullname</th>
                                        <th width="400px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                               <%
                                User loginUser = (User) session.getAttribute("userInfo");
                               	
                             if("admin".equals(loginUser.getUsername())){
                               
                               ArrayList<User> listUser = null;
                               	if(request.getAttribute("listUser")!= null){
                               		listUser =(ArrayList<User>) request.getAttribute("listUser");
                               		if(listUser.size()>0){
                               			for(User user: listUser){ 
                               %>
                                    <tr>
                                        <td><%=user.getId() %></td>
                                        <td class="center"><%=user.getUsername() %></td>
                                        <td class="center"><%=user.getFullname() %></td>
                                        <%
                                        	if("admin".equals(loginUser.getUsername())){
                                        %>
                                        <td class="center">
                                            <a  href="<%=request.getContextPath()+"/admin/user/edit?id="+user.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()+"/admin/user/del?id="+user.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa không?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                        <% } else { %>
                                         <td class="center" style="text-align: center;">
                                         <% 
                                         	if(loginUser.getId()==user.getId()){
                                         %>
                                            <a  href="<%=request.getContextPath()+"/admin/user/edit?id="+user.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        </td>
                                        <% }} %>
                                    </tr>
                              <% }
                               	}
                               	}%>
								</tbody>
                            </table>
                            
                            <%
                        	   	int numberOfPage = (Integer) request.getAttribute("numberOfPage");
                          	   int currentPage = (Integer) request.getAttribute("currentPage");
                         		UserDao userDao = (UserDao) request.getAttribute("userDao");
                         	  	if(listUser != null && listUser.size()>0){
                            %>
                            
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ User <%=(currentPage-1)*DefineUtil.NUMBER_PER_PAGE+1 %> đến User <%=currentPage*DefineUtil.NUMBER_PER_PAGE %> của <%=userDao.NumberOfItem() %> User</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/index?page=1">Trang đầu</a></li>
                                           <%
                                           	String active="";
                                           	for(int i=1; i<=numberOfPage; i++){
                                           		if(currentPage==i){
                                           			active="active";
                                           		} else{
                                           			active="";
                                           		}
                                           %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=i%>"><%=i %></a></li>
                                           <% } %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=numberOfPage%>">Trang cuối</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>
						<%
						} else {
						%>
                               <%
                                User accUser = (User) session.getAttribute("userInfo");                               	
                               %>
                                    <tr>
                                        <td class="center"><%=accUser.getId()%></td>
                                        <td class="center"><%=accUser.getUsername() %></td>
                                        <td class="center"><%=accUser.getFullname() %></td>
                                        <td class="center" style="text-align: center;">
                                         <a  href="<%=request.getContextPath()+"/admin/user/edit?id="+accUser.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        </td>
                                    </tr>
								</tbody>
                            </table>
						<% } %>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>