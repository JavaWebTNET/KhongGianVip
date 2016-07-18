<!DOCTYPE html>
<%@page import="model.Service"%>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  
	Service content_service=(Service) request.getAttribute("content_service");
%>

<div class="col-sm-9 col-xs-12 contai-center-content contai_content_info" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title title_content">
		<span class="title_introduce ">Dịch Vụ</span>
	</div>
	<div class="col-sm-12 center-content">
		<%= content_service.getContent_sv() %>
	</div>
	
</div>
<!-- ---------- -->