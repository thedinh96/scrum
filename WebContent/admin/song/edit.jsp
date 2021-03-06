﻿<%@page import="model.bean.Song"%>
<%@page import="model.dao.catDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/admin/inc/header.jsp" %>
<%@ include file="/template/admin/inc/leftbar.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/assets/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/template/admin/assets/js/ckeditor/ckeditor.js"></script>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
            <%
        		String errText = request.getParameter("err");
				if(request.getParameter("msg") != null){
					int msg = Integer.valueOf(request.getParameter("msg"));
					if ( msg == 0){
			%>
			<div class="alert alert-danger" role="alert">
				<p>Có lỗi trong quá trình sửa.</p>
			</div>
			<% }} else if("1".equals(errText)){ %>
			<div class="alert alert-danger" role="alert">
				<p>ID không tồn tại</p>
			</div>
			<% } %>
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" enctype="multipart/form-data" id="form" action="">
                                <%
                                	if(request.getAttribute("song") != null){
                                		Song song =(Song)request.getAttribute("song");
                                %>
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%=song.getSongName() %>" name="name" class="form-control" />
                                    </div>
                       
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="cat_id" class="form-control" required title="Vui lòng chọn">
                                        <%
                                        	if(request.getAttribute("listCat")!=null){
                                        	ArrayList<Category> listCat =(ArrayList<Category>) request.getAttribute("listCat");
                                        	if(listCat.size()>0){
                                        	for(Category objCat : listCat){
                                        %>
                          						<option value="<%=objCat.getCatId()%>"><%=objCat.getCatName() %></option>
                        				<%
                                        	}
                                        	}
                                        	}%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                        <%
                                        if(!song.getPicture().isEmpty()){                                      	
                                        %>
                                        <img width="200px" height="200px" src="<%=request.getContextPath()%>/files/<%=song.getPicture()%>"/>
                                    	<%} %>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea class="ckeditor" id="preview" class="form-control" rows="3" name="preview"><%=song.getDescription() %></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="ckeditor" id="detail" class="form-control" id="detail" rows="5" name="detail"><%=song.getDetail() %></textarea>
                                    </div>
                                    <% } %>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
<script type="text/javascript">
    document.getElementById("song").classList.add('active-menu');
    $(document).ready(function(){
		$('#form').validate({ 
			rules:{
				name:{
					required: true,
				},
				picture:{
					required: true,
				},
				preview:{
					required: true,
				},
				detail:{
					required: true,
				},
			},
			messages:{
				name:{
					required: "Vui lòng nhập!",
				},
				picture:{
					required: "Vui lòng chọn hình ảnh!",
				},
				preview:{
					required: "Vui lòng nhập",
				},
				detail:{
					required: "Vui lòng nhập",
				},
			submitHandler: function(){
				alert('Thành công');
			}
			}
		});
	});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/template/admin/inc/footer.jsp" %>