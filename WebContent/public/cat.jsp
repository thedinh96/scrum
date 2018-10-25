<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
    if(request.getAttribute("objCat") != null){
    	Category objCat = (Category)request.getAttribute("objCat");
    %>
		<h1 style="color: purple;"><%=objCat.getCatName() %></h1>
	<%} %>
    </div>
    <div class="article">
    <%
    if(request.getAttribute("listSongById") != null){
    	ArrayList<Song> listSongById = (ArrayList<Song>)request.getAttribute("listSongById");
    		if(listSongById.size() > 0){
    			int i = 0;
    			for(Song objSong : listSongById){
    				i++;
    %>
      <h2><a style="text-decoration: none;" href="<%=request.getContextPath() %>/public/detail?sid=<%=objSong.getSongId() %>" title=""><%=objSong.getSongName() %></a></h2>
      <p style="background-color: green;" class="infopost">Ngày đăng: <%=objSong.getDate_create() %> Lượt xem: <%=objSong.getCouter() %> <a href="#" class="com"><span><%=i %></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/files/<%=objSong.getPicture() %>" width="177" height="213" alt="img" class="fl" /></div>
      <div class="post_content">
        <p><%=objSong.getDescription() %></p>
        <p class="spec"><a href="<%=request.getContextPath() %>/public/detail?sid=<%=objSong.getSongId() %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    <%}}} %>
    </div>
    <p class="pages"><small>Trang 1 của 3</small>
    <span>1</span>
    <a href="">2</a>
    <a href="">3</a>
    <a href="#">&raquo;</a></p>
  </div>
  <div class="sidebar">
      <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/template/public/inc/footer.jsp" %>