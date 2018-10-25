<%@page import="util.DefineUtil"%>
<%@page import="model.dao.SongDao"%>
<%@page import="util.MessageUtil"%>
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
                <h2>Quản lý bài hát</h2>
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
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="get" action="<%=request.getContextPath()%>/admin/song/search">
                                        <input type="submit" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input required="required" name="search" type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
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
                                        <th>Tên bài hát</th>
                                        <th>Danh mục</th>
                                        <th>Lượt đọc</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                               		 ArrayList<Song> listSong =null;
                                	if(request.getAttribute("listSong")!= null){
                                		listSong = (ArrayList<Song>) request.getAttribute("listSong");
                                		if(listSong.size()>0){
                                			for(Song song : listSong){
                                				String urlEdit = request.getContextPath()+"/admin/song/edit?id="+song.getSongId();
                                				String urlDel = request.getContextPath()+"/admin/song/del?id="+song.getSongId();
                                			
                                %>
                                    <tr>
                                        <td><%=song.getSongId() %></td>
                                        <td class="center"><%=song.getSongName() %></td>
                                        <td class="center"><%=song.getObjCat().getCatName() %></td>
                                        <td class="center"><%=song.getCouter() %></td>
                                        <td class="center">
                                        	<%
                                        		if(!"".equals((song.getPicture()))){
                                        	%>
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=song.getPicture() %>" alt="<%=song.getSongName()%>"/>
                                        	<%
                                        		}else{
                                        			song.setPicture("nopicture.png");
                                        	%>
                                        	<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=song.getPicture() %>" alt="<%=song.getSongName()%>"/>
                                        	
                                        	<%} %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=urlDel %>" title="" onclick="return confirm('Bạn đã chắc xóa ?');" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
								<%}}} %>
                                </tbody>
                            </table>
                             <%
                        	   	int numberOfPage = (Integer) request.getAttribute("numberOfPage");
                          	   int currentPage = (Integer) request.getAttribute("currentPage");
                         		SongDao objSong = (SongDao) request.getAttribute("objSong");
                         	  	if(listSong != null && listSong.size()>0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ bài <%=((currentPage-1)*DefineUtil.NUMBER_PER_PAGE + 1) %> đến bài hát <%=currentPage*DefineUtil.NUMBER_PER_PAGE %> của <%=objSong.NumberOfItem() %> bài hát</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song/index?page=1">Trang đầu</a></li>
                                         	<%
                                         		String active="";
                                         		for(int i=1; i<=numberOfPage; i++){
                                         			if(currentPage==i){
                                         				active="active";
                                         			} else{
                                         				active="";
                                         			}
                                         	%>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=i%>"><%=i%></a></li>
                                           <% } %>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=numberOfPage%>">Trang cuối</a></li>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>