<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String fmsg_dish = (String)request.getSession().getAttribute("flash_success_dish");
	request.getSession().removeAttribute("flash_success_dish");
   if(fmsg_dish!=null){
%>
    <div class="alert alert-success">
        <button type="button" class="close flash_dish_location" data-dismiss="alert" aria-hidden="true ">&times;</button>
        <strong>Success! </strong><%=fmsg_dish%>
    </div>
<%} %>

<% fmsg_dish = (String)request.getSession().getAttribute("flash_error_dish");
	request.getSession().removeAttribute("flash_error_dish");
   if(fmsg_dish!=null){
%>
    <div class="alert alert-danger">
        <button type="button" class="close flash_dish_location" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Error! </strong><%=fmsg_dish%>
    </div>
<%} %>

<% fmsg_dish = (String)request.getSession().getAttribute("flash_info_dish");
	request.getSession().removeAttribute("flash_info_dish");
   if(fmsg_dish!=null){
%>
	<div class="alert alert-info">
        <button type="button" class="close flash_dish_location" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Info! </strong><%=fmsg_dish%>
    </div>
<%} %>

<% String[] fvalids_dish= (String[])request.getSession().getAttribute("flash_valid_dish");
	request.getSession().removeAttribute("flash_valid_dish");
   if(fvalids_dish!=null) {
%>
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      <%for(String fvalid_dish:fvalids_dish){ %>  
        <strong>Error! </strong><%=fvalid_dish%><br/>
      <%} %>
    </div>   
<%} %>