<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Category" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Vector" %>
<%@page import="model.Dish" %>
<% @SuppressWarnings("unchecked")
	HashMap<Integer, Vector<Dish>> hm_dish_menu=(HashMap<Integer, Vector<Dish>>)request.getAttribute("hm_dish_menu");
	String name_dishtype=(String) request.getAttribute("name_dishtype");
	String name_parent=(String) request.getAttribute("name_parent");
	String content_dishtype=(String) request.getAttribute("content_dishtype");	
	 @SuppressWarnings("unchecked")
	Vector<Category> vtcatemenu=(Vector<Category>) request.getAttribute("menu");
	 @SuppressWarnings("unchecked")
	Vector<Dish> vtdish_dishtype=(Vector<Dish>) request.getAttribute("vt_dish_dishtype");

%>

<div class="col-sm-9 col-xs-12 contai-center-content" id="location-focus">
	<div class="col-sm-12 col-xs-12 center-content-title">
		<img src="<%=request.getContextPath()%>/View/Image/chef.png"><span class="name_parent"><%=name_parent%></span> &raquo; <span class="name_dishtype"><%= name_dishtype %></span>
	</div>
	<div class="col-sm-12 col-xs-12 center-content contain-dish " >
	<div class="col-sm-12 content_category_parent"><%= content_dishtype %> </div>
	
		<%
		for(Category item:vtcatemenu){
			if(hm_dish_menu.containsKey(item.getId_dm())){
		%>
		<div class="col-xs-12 contain-dish-child">
			<div class="title-menu-table col-sm-12"><%= item.getTitle() %></div>
			<table
				class="col-sm-12  table table-bordered table-condensed  table-menu-dish">
			
				<tr class="title-table"	>
					<th>Tên món</th>
					<th>Hình ảnh</th>
					<th>Chi tiết</th>
				</tr>
				<% for(Dish dish:hm_dish_menu.get(item.getId_dm())){ 
				%>
				<tr>
					<td><%= dish.getName_dish()%></td>
					<td class="td-image-dish">
						<% if(dish.getImage_dish()!=null){ %>
							<img src="<%= dish.imageLink(request)%>"  class="img-responsive">
							<%} %>
					</td>
					<td class="a-xem"><a href="<%= request.getContextPath()%>/menu/detail?dish=<%=dish.getId_dish()%>">Xem</a></td>
				
				</tr>
				<%}%>

			</table>

		</div>
		
	<%} 
		}%>
	<%if(vtdish_dishtype.size()>0){%>
		<div class="col-xs-12 contain-dish-child">				
				<table
				class="col-sm-12  table table-bordered table-condensed  table-menu-dish">
			
				<tr class="title-table"	>
					<th>Tên món</th>
					<th>Hình ảnh</th>
					<th>Chi tiết</th>
				</tr>
				
				<% for(Dish item_dish:vtdish_dishtype){%>
				
				<tr>
					<td><%= item_dish.getName_dish()%></td>
					<td class="td-image-dish">
						<% if(item_dish.getImage_dish()!=null){ %>
							<img src="<%= item_dish.imageLink(request)%>"  class="img-responsive">
							<%} %>
					</td>
					<td class="a-xem"><a href="<%= request.getContextPath()%>/menu/detail?dish=<%=item_dish.getId_dish()%>">Xem</a></td>				
				</tr>				
				<%}%>
			</table>			
		</div>
	<%}%>		
	</div>
</div>
<!-- ---------- -->