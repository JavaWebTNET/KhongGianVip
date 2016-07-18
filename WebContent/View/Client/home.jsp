<!DOCTYPE html>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  @SuppressWarnings("unchecked")
	Vector<Dish> vtdishhome = (Vector<Dish>) request.getAttribute("dishhome");
%>

<div class="col-sm-9 col-xs-12 contai-center-content" id="location-focus-home">
	
	<div class="col-sm-12 col-xs-12 center-content-title">
		<img src="<%=request.getContextPath()%>/View/Image/chef.png"><span
			class="name_parent name_home">Thöïc Ñôn</span>
	</div>
	<div class="col-sm-12 center-content contain-dish contai-dish-menu">
	<% for(Dish item:vtdishhome){ %>
		<div class="contai-dish-home-child">
			<div class="name-dish-home"><%= item.getName_dish() %></div>
			<a href="<%= request.getContextPath()%>/menu/detail?dish=<%=item.getId_dish()%>">
			<img src="<%= item.imageLink(request)%>" class="img-responsive">
			</a>
		</div>
	<%} %>
		
	<div class="col-sm-12 location_paging">
		<%@ include file="/View/Client/paginate.jsp"%>
	</div>
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