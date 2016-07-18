<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish" %>
<%   Dish dish=(Dish) request.getAttribute("dish");%>

<div class="col-sm-9 col-xs-12 contai-center-content" id="location-focus">
	<div class="col-sm-12 col-xs-12 center-content-title">
		<img src="<%=request.getContextPath()%>/View/Image/chef.png"><span class="name_parent">Chi tiết món</span> 
	</div>
	<div class="col-sm-12 center-content contain-dish">
		<div class="col-sm-10 contai-dish-detail">
		<% if(dish!=null){%>
			<div class="item-dish-detail col-sm-12">
				<label class="control-label title col-sm-3">Tên món</label>
				<label class="control-label content-name  name-dish col-sm-9"><%= dish.getName_dish() %></label>			
			</div>			
			<div class="item-dish-detail col-sm-12">
				<label class="control-label title col-sm-3">Giá</label>
				<label class="control-label content-name col-sm-9"><%= dish.getPrice()!=""?dish.getPrice():"Chưa cập nhật" %></label>
			
			</div>
			<div class="item-dish-detail col-sm-12">
				<label class="control-label title col-sm-3">Hình ảnh</label>
				<label class="control-label content-name col-sm-9">
					 <% if(dish.getImage_dish()!=null){%>					
					<img src="<%= dish.imageLink(request)%>" class="img-responsive">					
					<%}else{%>
					Chưa cập nhật
					<%} %>
				</label>
				
			
			</div>
			<div class="item-dish-detail col-sm-12">
				<label class="control-label title col-sm-3">Chi tiết</label>
				<label class="control-label content-name col-sm-9">
					 <% if(dish.getContent_dish()!=null && dish.getContent_dish().length()>1){%>					
					<%= dish.getContent_dish()%>				
					<%}else{%>
					Chưa cập nhật
					<%} %>
				</label>
				
			
			</div>
			
			<%}%>
		</div>
		
	</div>
</div>
<!-- ---------- -->