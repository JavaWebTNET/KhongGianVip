<!DOCTYPE html>
<%@page import="model.News"%>
<%@page import="model.Service"%>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  
	News content_service=(News) request.getAttribute("content_news");
   @SuppressWarnings("unchecked")
	Vector<News> vtnews=(Vector<News>) request.getAttribute("allnews");
%>

<div class="col-sm-9 col-xs-12 contai-center-content contai_content_info" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title title_content">
		<span class="title_introduce ">Tin Tức</span>
	</div>
	<div class="col-sm-12 center-content">
	<div class="news-content-child"><%= content_service.getContent() %></div>		
	<div class="col-sm-12 news-time">Cập nhật : <%= content_service.getTime_news() %></div>
	<div class="col-sm-12 news-move">
	<div class="title-service-child title-news">Tin Khác</div>
	<ul>
	<% for(News item:vtnews){ %>

	<li class="li-move-news"><a href="<%=request.getContextPath()%>/news?ns=<%=item.getId()%>"> <%= item.getTitle() %></a></li>	
	
	<%} %>
	</ul>

	
	</div>
	
	
	</div>
	
	
	
</div>
<!-- ---------- -->