<!DOCTYPE html>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Service"%>
<%@ page import="java.util.Vector" %>
<%  @SuppressWarnings("unchecked")
	Vector<Service> vtservice = (Vector<Service>) request.getAttribute("allservice");
%>

<div class="col-sm-9 col-xs-12 contai-center-content" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title">
		<img src="<%=request.getContextPath()%>/View/Image/chef.png"><span
			class="name_parent ">Dịch Vụ</span>
	</div>
	
	<div class="col-sm-12 center-content content-service">
		<% for(Service item:vtservice){ %>
		<div class="content-service-child">
			<div class="title-service-child"><%= item.getTitle_sv() %></div>
			<div class="content-parimary-service-child"><%= item.subDetail() %>...</div>
			<div class="show-detail-service"><a href="<%=request.getContextPath()%>/detail-service?sv=<%= item.getId_sv()%>">Xem Thêm</a></div>
		</div>
		<%}%>
		
	
	</div>
	
</div>
<!-- ---------- -->

<script>
$(document).ready(function(){
	 
	if(GetParameterValues('page')>=1){		
			$('html, body').animate({ scrollTop: $('#location-focus-home').offset().top }, 'slow');		
	}
	// function get url and return value parameter not exist return -1
    function GetParameterValues(param) {  
         var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
         for (var i = 0; i < url.length; i++) {  
             var urlparam = url[i].split('=');  
             if (urlparam[0] == param) {  
                 return urlparam[1];  
             }  
             else{
          	   return -1;
             }
         }  
     }    
	
});

</script>