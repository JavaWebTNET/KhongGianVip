<!DOCTYPE html>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  
	String content_connected=(String) request.getAttribute("conten_connected");
%>

<div class="col-sm-9 col-xs-12 contai-center-content contai_content_info" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title title_content">
		<span class="title_introduce ">Liên Hệ</span>
	</div>
	<div class="col-sm-12 center-content">
	<%= content_connected %>
	</div>
	
</div>
<!-- ---------- -->