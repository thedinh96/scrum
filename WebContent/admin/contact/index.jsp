<%@page import="util.DefineUtil"%>
<%@page import="model.dao.ContactDao"%>
<%@page import="model.bean.Contact"%>
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
                <h2>Quản lý liên hệ</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	String err = request.getParameter("err");
        	String msg = request.getParameter("msg");
        	if("1".equals(msg)){
        %>
		<div class="alert alert-success" role="alert">
			<p>Xóa liên hệ thành công</p>
		</div>
		<% } else if("1".equals(err)) { %>
		<div class="alert alert-warning" role="alert">
			<p>ID không tồn tại</p>
		</div>
		<% } else if("2".equals(err)) { %>
		<div class="alert alert-danger" role="alert">
			<p>Có lỗi xảy ra</p>
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
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>Message</th>
                                        <th width="90px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                ArrayList<Contact> contacts = null;
                                	if(request.getAttribute("list") != null){
                                	contacts = (ArrayList<Contact>) request.getAttribute("list");
                                	if (contacts.size()>0){
                                		for(Contact item : contacts){
                                %>
                                    <tr>
                                        <td><%=item.getID() %></td>
                                        <td class="center"><%=item.getName() %></td>
                                        <td class="center"><%=item.getEmail() %></td>
                                        <td class="center"><%=item.getWebsite() %></td>
                                        <td class="center">
                                        	<%=item.getMessage() %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/contact/del?id=<%=item.getID() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc muốn xóa?');"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                   <%}}}else %>
                                   <tr><td colspan ="5" align="center">Chưa có liên hệ nào</td></tr>
                                </tbody>
                            </table>
                            <%
                        	   	int numberOfPage = (Integer) request.getAttribute("numberOfPage");
                          	  	 int currentPage = (Integer) request.getAttribute("currentPage");
                         		ContactDao objContact = (ContactDao) request.getAttribute("objContact");
                         		if(contacts!=null&&contacts.size()>0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(currentPage-1)*DefineUtil.NUMBER_PER_PAGE+1 %> đến <%=currentPage*DefineUtil.NUMBER_PER_PAGE %> của <%=objContact.NumberOfItem() %> truyện</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contacts?pages=1">Trang đầu</a></li>
                                            <%
                                            	String active="";
                                            	for(int i=1; i<=numberOfPage; i++){
                                            		if(currentPage==i){
                                            			active="active";
                                            		} else{
                                            			active="";
                                            		}
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contacts?pages=<%=i%>"><%=i %></a></li>
                                            <% } %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contacts?page=<%=numberOfPage%>">Trang cuối</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>