<%@ page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@page import="model.Category"%>
<%@page import="java.util.Vector"%>
<%
Dish dish=(Dish) request.getAttribute("dish");    
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		
		<!-- ----------content--------------- -->
		<div class="col-sm-12 center-title title-dish">
			<strong>Chi Tiết Món Ăn</strong>
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
		<% if(dish!=null){%>
		
			<div class="col-sm-offset-2 col-sm-8 form-group  form-add_news">
				 <label class="col-sm-offset-4 col-sm-4 col-xs-12 title-add-news">Chi Tiết Món Ăn</label>
				<form role="form" method="post" enctype="multipart/form-data"
								action="${ pageContext.request.contextPath}/admin/dish/update" class="col-sm-12">
								<input type="hidden" name="id_dish" value="<%=dish.getId_dish() %>">
								<label class="control-label  title-add-cate col-sm-3">Tên món ăn (*)</label>								
								<div class="col-sm-9 name-add">
									<input type="text" class="name-dish-dishtype-add form-control"
										name="name_dish" value="<%= dish.getName_dish()%>">
								</div>						
								<label class="control-label  title-add-cate col-sm-3">Giá</label>								
								<div class="col-sm-9 name-add">
									<input type="text" class="price-dish-dishtype-add form-control"
										name="price_dish" value="<%= dish.getPrice()%>">
								</div>							
																
								<label class="control-label  title-add-cate  title-img-dish col-sm-3">Ảnh</label>								
								<div class="col-sm-9 div-addimage div-file-image-dish">
									 <div class="col-sm-3 div-stt-imglogo">
									 <% if(dish.getImage_dish()!=null){%>
										<img src="<%=dish.imageLink(request)%>" class="img-responsive">
									<%} %>
									</div> 
									<div class="col-sm-9 div-uploadimage">
									<input type="file" name="file-image" id="file_image" class="file-input">
									</div>
								</div>	
													
								<label class="control-label title-add-cate col-sm-3">Nội Dung</label>
								 <div class="col-sm-9 content_dish_menu_no">
									<textarea class="form-control">	<%= dish.subContent() %></textarea>
								</div>		
														
								<div class="col-sm-9 content_dish_menu_have">
									<textarea class="form-control" name="conten_dish" id="area_add_dish_menu"><%= dish.getContent_dish() %></textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2">
									<button type="submit" class="btn-add-dish-dishtype  btn btn-info ">Cập Nhật</button>
								</div>							  
									  
				</form>
			</div>		
			<% } %>	
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