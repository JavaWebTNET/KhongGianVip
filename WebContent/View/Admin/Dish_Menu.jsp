<%@ page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@page import="model.Category"%>
<%@page import="model.Slider"%>
<%@page import="model.ImageStore"%>
<%@page import="model.Information"%>
<%@page import="java.util.Vector"%>
<%
@SuppressWarnings("unchecked")
Vector<Dish> vtdish = (Vector<Dish>) request.getAttribute("dish-menu");		
int sp_id=(Integer)(request.getAttribute("id_dm"));
String name_typedish=(String) request.getAttribute("name-menu");
@SuppressWarnings("unchecked")	
Vector<Dish> vtalldish =(Vector<Dish>) request.getAttribute("alldish");	
    
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		
		<!-- ----------content--------------- -->
		<div class="col-sm-12 center-title title-dish">
			<strong>Quản Lý Món Ăn Trong Menu</strong>
		</div>  
		<div class=" col-sm-12 contai-include-flash-dish">
			<%@ include file="/View/Admin/flash_dish.jsp"%>
		</div>
		<div class="col-sm-12 contai-error-dish">
			<div class="alert alert-danger">
				<button type="button" class="close location-show-error-dish" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong id="show-error-dish"></strong>
			</div>
		</div>
		<div class=" col-sm-12 content-main ">
		
		
			<div class="col-sm-offset-2 col-sm-8 form-group  form-add_news">
				 <label class="col-sm-offset-4 col-sm-4 col-xs-12 title-add-news">Thêm Món Mới</label>
				<form role="form" method="post" enctype="multipart/form-data"
								action="${ pageContext.request.contextPath}/admin/category/create-dish-menu" class="col-sm-12">
								<input type="hidden" name="id_cate" value="<%=sp_id %>">
								<label class="control-label  title-add-cate col-sm-3">Tên món ăn (*)</label>								
								<div class="col-sm-9 name-add">
									<input type="text" class="name-dish-dishtype-add form-control"
										name="name_dish_dishtype" value="">
								</div>
								
								<label class="control-label  title-add-cate col-sm-3">Giá</label>								
								<div class="col-sm-9 name-add">
									<input type="text" class="price-dish-dishtype-add form-control"
										name="price_dish_dishtype" value="">
								</div>							
																
								<label class="control-label  title-add-cate col-sm-3">Ảnh</label>								
								<div class="col-sm-9 div-addimage div-file-image-dish">
									<input type="file" name="file-image" id="file_image" class="file-input">
								</div>				
														
								<label class="control-label title-add-cate col-sm-3">Nội Dung</label>
								 <div class="col-sm-9 content_dish_menu_no">
									<textarea class="form-control">	</textarea>
								</div>								
								<div class="col-sm-9 content_dish_menu_have">
									<textarea class="form-control" name="conten_dish_dishtype_add" id="area_add_dish_menu">	</textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2">
									<button type="submit" class="btn-add-dish-dishtype  btn btn-info ">Thêm Mới</button>
								</div>
									  
									  
				</form>
			</div>
			
			<div class="col-sm-offset-3 col-sm-6 form-add-select">
			<label class="col-sm-offset-3 col-sm-6 col-xs-12 title-add-news title-add-dish-select">Thêm Món Từ Danh Sách</label>
				<form name="" method="post" 	action="${ pageContext.request.contextPath}/admin/category/create-dish-menu-select" class="col-sm-12"> 
				<input type="hidden" name="id_cate" value="<%=sp_id %>">
 					<div class="col-sm-offset-1 col-sm-10 name-add select-id-dish-dishtype">
						<select name="id_dish" class="form-control id_dish_select">
							<option value=""  class="select-first"> Vui lòng chọn món </option>
							<% for(int d=0;d<vtalldish.size();d++) { %>
							<option value="<%= vtalldish.get(d).getId_dish()%>"> <%=vtalldish.get(d).getName_dish()%> </option>							
							
							<%} %>
						</select>								
					</div>	
					
										
					<div class="col-sm-offset-8 col-sm-2">
						<button type="submit" class="btn-add-dish-dishtype-select  btn btn-info ">Thêm Mới</button>
					</div>
									  
											
				</form>
			</div>
			
			<table class="col-sm-12  table table-bordered table-condensed  table-dish">
			<tr class="tr-title-head-table"><th colspan="6"><%=name_typedish%> </th></tr>
			<tr class="title-table"	style="color: #00ff21; height: 45px; background-color: #003300">
				<th>Stt</th>
				<th>Tên Món Ăn</th>		
				<th>Hình Ảnh</th>	
				<th>Giá</th>					
				<th colspan="2">Chức Năng</th>			
			</tr>
			
			<% int j=1;
			   for(int i=0;i<vtdish.size();i++){
				   
			%>
			<tr>
			<td><%= j %></td>
			<td class="td-name-dish"><%= vtdish.get(i).getName_dish()%></td>
			<td class="td-image-dish">
			<% if(vtdish.get(i).getImage_dish()!=null){ %>
			<img src="<%= vtdish.get(i).imageLink(request)%>">
			<%} %>
			</td>
			<td><%= vtdish.get(i).getPrice() %></td>			
			<td class="a-xoa"><a class="a-child-cate" href="${pageContext.request.contextPath}/admin/dish/detail-dish?id_dish=<%=vtdish.get(i).getId_dish()%>">Chi Tiết</a></td>
			<td class="a-xoa"><a class="a-xoa-dish" href="${pageContext.request.contextPath}/admin/category/dish-menu/delete?id_cate=<%=sp_id%>&id_dish=<%=vtdish.get(i).getId_dish()%>">Xóa</a></td>
			</tr>
			<%
				j++;
			   }
			%>	
			</table>			
			 
			
		</div>
		<!-- ----------content--------------- -->
	</div>
</div>

<!-- ------------- -->
<script src="<%=request.getContextPath()%>/View/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	$('.btn-add-dish-dishtype').click(function(){
		if ($('.contai-include-flash-dish').is(':visible')) {
			$('.contai-include-flash-dish').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error-dish').is(':visible')){
			$('.contai-error-dish').hide();
		}
		var form=$(this).parents('form');
		var name_dish=$(form).find('.name-dish-dishtype-add');
		if($(name_dish).val()==""){
			$('#show-error-dish').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
			//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
			$('.contai-error-dish').show();// set lại display cho class chứa ô báo error
			$(name_dish).focus();
			return false;
		}
		return true;
	});
	
	$('.btn-add-dish-dishtype-select').click(function(){/* 	khi click vào area đang hiển thị nhưng ko set CKEDITOR thì sẽ tự động ẩn và gọi show của name_add_have */
		if ($('.contai-include-flash-dish').is(':visible')) {
			$('.contai-include-flash-dish').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error-dish').is(':visible')){
			$('.contai-error-dish').hide();
		} 
		var form=$(this).parents('form');
		var id_dish=$(form).find('.id_dish_select');
		if($(id_dish).val()==""){
			$('#show-error-dish').html('Bạn chưa chọn món..!');
			//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
			$('.contai-error-dish').show();// set lại display cho class chứa ô báo error
			$(id_dish).css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"});		
			$('.location-show-error-dish').focus();
			return false;			
		} 		
		return true;
	});
	
	$('.content_dish_typedish_no').click(function(){/* 	khi click vào area đang hiển thị nhưng ko set CKEDITOR thì sẽ tự động ẩn và gọi show của name_add_have */
		var form=$(this).parent("form");	
		$(this).hide();						
		$(form).find(".content_dish_typedish_have").show();			
		CKEDITOR.replace('area_add_dish_disgtype');				
		
	});
	//xóa
	$('.a-xoa-dish').click(function(){
		if ($('.contai-include-flash-dish').is(':visible')) {
			$('.contai-include-flash-dish').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error-dish').is(':visible')){
			$('.contai-error-dish').hide();
		}
		return confirm('Bạn muốn xóa..?');		
	});
	$('.content_dish_menu_no').click(function(){/* 	khi click vào area đang hiển thị nhưng ko set CKEDITOR thì sẽ tự động ẩn và gọi show của name_add_have */
		var form=$(this).parent("form");	
		$(this).hide();						
		$(form).find(".content_dish_menu_have").show();			
		CKEDITOR.replace('area_add_dish_menu');				
		
	});
	
});
</script>