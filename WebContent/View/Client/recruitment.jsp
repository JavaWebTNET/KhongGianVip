<!DOCTYPE html>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  
	String content_recruitment=(String) request.getAttribute("conten_recruitment");
%>

<div class="col-sm-9 col-xs-12 contai-center-content contai_content_info" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title title_content">
		<span class="title_introduce ">Thông Tin Tuyển Dụng</span>
	</div>
	<div class="col-sm-12 center-content">
	<%= content_recruitment %>
	</div>
	
</div>
<!-- ---------- -->