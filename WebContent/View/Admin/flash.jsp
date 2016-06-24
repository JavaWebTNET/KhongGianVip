<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String fmsg = (String)request.getSession().getAttribute("flash_success");
	request.getSession().removeAttribute("flash_success");
   if(fmsg!=null){
%>
    <div class="alert alert-success">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Success! </strong><%=fmsg%>
    </div>
<%} %>

<% fmsg = (String)request.getSession().getAttribute("flash_error");
	request.getSession().removeAttribute("flash_error");
   if(fmsg!=null){
%>
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Error! </strong><%=fmsg%>
    </div>
<%} %>

<% fmsg = (String)request.getSession().getAttribute("flash_info");
	request.getSession().removeAttribute("flash_info");
   if(fmsg!=null){
%>
	<div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <strong>Info! </strong><%=fmsg%>
    </div>
<%} %>

<% String[] fvalids = (String[])request.getSession().getAttribute("flash_valid");
	request.getSession().removeAttribute("flash_valid");
   if(fvalids!=null) {
%>
    <div class="alert alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      <%for(String fvalid:fvalids){ %>  
        <strong>Error! </strong><%=fvalid%><br/>
      <%} %>
    </div>   
<%} %>