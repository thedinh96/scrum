<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDao"%>
<%@page import="model.dao.catDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
  <form class="bg" id="formsearch" name="formsearch" method="post" action="#">
    <span>
    <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Tìm kiếm bài hát" type="text" />
    </span>
    <input name="button_search" src="<%=request.getContextPath() %>/template/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget" style="background: #E9EBEE;">
  <h2 style="color: red" class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  	catDao catDao = new catDao();
  	ArrayList<Category> cat = catDao.getItems();
  	if(cat.size()>0){
  		for(Category item: cat){
  			String urlSlug = request.getContextPath()+"/danh-muc/"+StringUtil.makeSlug(item.getCatName())+"-"+item.getCatId();
  %>
    <li><b><a style="color: purple;"  href="<%=urlSlug%>"><%=item.getCatName() %></a></b></li>
  <%}
  	} %>
  </ul>
</div>

<div class="gadget" style="background: #E9EBEE;">
  <h2 style="color: red" class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  <%
  	SongDao songDao = new SongDao();
	ArrayList<Song> recentSong = songDao.getItems1(6);
	if(recentSong.size()>0){
		for(Song item: recentSong){
  %>
    <li><a style="color: purple;" href="<%=request.getContextPath()%>/public/detail?sid=<%=item.getSongId()%>"><%=item.getSongName() %></a><br />
      <%if(item.getDescription().length()>50) out.print(item.getDescription().substring(0,50)+"..."); else out.print(item.getDescription()); %></li>
  <%}} %>
  </ul>
</div>