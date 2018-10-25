<%@page import="util.DefineUtil"%>
<%@page import="model.dao.catDao"%>
<%@page import="util.MessageUtil"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý danh mục</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
								String msgText = "";
								int msg = 10;
								if(request.getParameter("msg") != null){
									msg = Integer.valueOf(request.getParameter("msg"));
									switch(msg){
									case 1: msgText ="Thêm Thành Công"; break;
									case 2: msgText ="Sửa Thành Công"; break;
									case 3: msgText ="Xóa Thành Công"; break;
									case 0: msgText ="Có lỗi trong quá trình thực hiện"; break;
									}
								}
								if(msg==0){
							%>
							<div class="alert alert-danger" role="alert">
								<p><%=msgText %></p>
							</div>
							<% } else if(msg==1||msg==2||msg==3) { %>
							<div class="alert alert-success" role="alert">
								<p><%=msgText %></p>
							</div>
							<% } %>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		ArrayList<Category> listCat = null;
                                		if(request.getAttribute("listCat") != null){
                                		 listCat =(ArrayList<Category>) request.getAttribute("listCat");
                                		if(listCat.size()>0){
                                			for(Category objCat:listCat){
                                				String urlEdit = request.getContextPath()+"/admin/cat/edit?id="+objCat.getCatId();
                                				String urlDel = request.getContextPath()+"/admin/cat/del?id="+objCat.getCatId();
                                	%>
                                    <tr>
                                        <td><%=objCat.getCatId() %></td>
                                        <td class="center"><%=objCat.getCatName() %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="Sửa" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" onclick="return confirm('Bạn đã chắc xóa ?');" title="Xóa" class="btn btn-danger"><i onclick="return confirm('Bạn có muốn xóa không?')" class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%}}} %>
                                </tbody>
                            </table>
                              <%
                        	   	int numberOfPage = (Integer) request.getAttribute("numberOfPage");
                          	  	 int currentPage = (Integer) request.getAttribute("currentPage");
                         		catDao objCat = (catDao) request.getAttribute("objCat");
                         		if(listCat!=null&&listCat.size()>0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=(currentPage-1)*DefineUtil.NUMBER_PER_PAGE+1 %> đến <%=currentPage*DefineUtil.NUMBER_PER_PAGE %> của <%=objCat.NumberOfItem() %> truyện</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/cat/index?page=1">Trang đầu</a></li>
                                            <%
                                            	String active = "";
                                            	for(int i=1; i<=numberOfPage; i++){
                                            		if(currentPage==i){
                                            			active="active";
                                            		} else{
                                            			active="";
                                            		}
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%=i%>"><%=i%></a></li>
                                            <% } %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/cat/index?page=<%= numberOfPage%>">Trang cuối</a></li>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>