<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/template/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar" >
    <div class="article" style="background: #E9EBEE;" >
    <%
    if(request.getAttribute("objSong") != null){
    	Song objSong = (Song)request.getAttribute("objSong");
    %>
      <h1 style="color: orange;"><%=objSong.getSongName() %></h1>
      <div class="clr"></div>
      <p style="">Ngày đăng: <%=objSong.getDate_create() %>. Lượt xem: <%=objSong.getCouter() %></p>
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
			<div class="clr"></div>
      <div class="vnecontent">
          <%=objSong.getDetail() %>
      </div>
    <%} %>
    <form action="" class="cmt">
    	<input style="width: 400px;; height: 30px;word-break: break-all;" type="text" placeholder="  Your comment" name="comment"/>
    	<input style="background: blue; color: white; height: 30px;" type="submit" value="Đăng bình luận" name="submit">
    </form>
    </div>
    <br /></br>
    <div class="article">
      <h2 style="color: red;">Bài viết liên quan</h2>
      <%
      if(request.getAttribute("relatedSongs") != null){
    	  ArrayList<Song> listSongRelated = (ArrayList<Song>)request.getAttribute("relatedSongs");
    	  if(listSongRelated.size() > 0){
    		   for(Song objSong : listSongRelated){
    			   String urlSlug = request.getContextPath()+"/chi-tiet/"+StringUtil.makeSlug(objSong.getSongName())+"-"+objSong.getSongId();
      %>
      <div class="clr"></div>
      <div class="comment"> <a href="<%=urlSlug%>"><img src="<%=request.getContextPath() %>/files/<%=objSong.getPicture() %>" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a style="text-decoration: none" href="<%=request.getContextPath() %>/public/detail?sid=<%=objSong.getSongId() %>"><%=objSong.getSongName() %></a></h2>
        <p><%=objSong.getDescription() %></p>
      </div>
     <%}}} %>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/template/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/template/public/inc/footer.jsp" %>
