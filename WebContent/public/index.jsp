<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <% 
  		int numberOfPage = (Integer) request.getAttribute("numberOfPage");
	   int currentPage = (Integer) request.getAttribute("currentPage");
	  ArrayList<Song> listSong=null;
  	if(request.getAttribute("listSong")!=null){
  	listSong =(ArrayList<Song>) request.getAttribute("listSong");
  		if(listSong.size()>0){
  			int i = 0;
  			for(Song objSong: listSong){
  				i++;
  			
  %>
    <div class="article" style="background: #E9EBEE;">
      <h2><a style="text-decoration: none" href="<%=request.getContextPath() %>/public/detail?sid=<%=objSong.getSongId() %>" title="Đổi thay"><%=objSong.getSongName() %></a></h2>
      <p style="background-color: green;" class="infopost">Ngày đăng: 25-10-2018. Lượt xem: <%=objSong.getCouter() %><a href="#" class="com"><span><%=i %></span></a></p>
      <div class="clr"></div>
      <div class="img">
				<%
					if (!"".equals((objSong.getPicture()))) {
				%>
				<img width="200px" height="200px"
					src="<%=request.getContextPath()%>/files/<%=objSong.getPicture()%>"
					alt="<%=objSong.getSongName()%>" />
				<%
					} else {
						objSong.setPicture("nopicture.png");
				%>
				<img width="200px" height="200px"
					src="<%=request.getContextPath()%>/files/<%=objSong.getPicture()%>"
					alt="<%=objSong.getSongName()%>" />

				<%
					}
				%>
			</div>
      <div class="post_content">
        <p><%=objSong.getDescription()%></p>
        <p class="spec"><a href="<%=request.getContextPath()%>/public/detail?sid=<%=objSong.getSongId() %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%	
  			}
  		}
  	} %>
    <!-- <p class="pages"><small>Trang 1 của 5</small>
    <span>1</span>
    <a href="">2</a>
    <a href="">3</a>
    <a href="">4</a>
    <a href="">5</a>
    <a href="#">&raquo;</a></p> -->
    
<div class="row">
                                <div class="col-sm-6">
<%--                                     <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ bài <%=((currentPage-1)*DefineUtil.NUMBER_PER_PAGE + 1) %> đến bài hát <%=currentPage*DefineUtil.NUMBER_PER_PAGE %> của <%=objSong.NumberOfItem() %> bài hát</div>
 --%>                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul  class="pagination" style="list-style: none; font-size: 15px;">
                                            <li style="display: inline;" class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/public/index?page=1">Trang đầu</a></li>
                                         	<%
                                         		String active="";
                                         		for(int i=1; i<=numberOfPage; i++){
                                         			if(currentPage==i){
                                         				active="active";
                                         			} else{
                                         				active="";
                                         			}
                                         	%>
                                            <li style="display: inline;" class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/public/index?page=<%=i%>"><%=i%></a></li>
                                           <% } %>
                                            <li style="display: inline;" class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/public/index?page=<%=numberOfPage%>">Trang cuối</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
    
  </div>
  <div class="sidebar">
      <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
    document.getElementById("public").classList.add('active');
</script>
<%@ include file="/template/public/inc/footer.jsp" %>
