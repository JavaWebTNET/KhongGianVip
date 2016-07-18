<%@page import="model.Category"%>
<%@page import="model.Slider"%>
<%@page import="model.ImageStore"%>
<%@page import="model.Information"%>
<%@page import="java.util.Vector"%>
<%@ page pageEncoding="utf-8"%>
<%
@SuppressWarnings("unchecked")	
	Vector<Category> vtsl = (Vector<Category>) request.getAttribute("catedishtype");	
    int sp_id=(Integer)(request.getAttribute("id_dm"));
    String name_typecustomer=(String) request.getAttribute("name-typecustomer");	 
    
    
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		<div class="col-sm-12 center-title">
			<strong>Quản Lý Suất Danh Mục</strong>
		</div>
		<div class=" col-sm-12 contai-include-flash">
			<%@ include file="/View/Admin/flash.jsp"%>
		</div>
		<div class="col-sm-12 contai-error">
			<div class="alert alert-danger">
				<button type="button" class="close location-show-error" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong id="show-error"></strong>
			</div>
		</div>
		<!-- ----------content--------------- -->

		<div class=" col-sm-12 content-main ">
		
		
			<div class="col-sm-offset-2 col-sm-8 form-group  form-add_news">
				 <label class="col-sm-offset-4 col-sm-3 col-xs-12 title-add-news">Thêm Suất</label>
				<form role="form" method="post"
								action="${ pageContext.request.contextPath}/admin/category/create-dishtype" class="col-sm-12">
				<label class="control-label  title-add-cate col-sm-3">Tên Suất (*)</label>
								<input type="hidden" name="sp_id" value="<%= sp_id %>" readonly>
								<div class="col-sm-9 name-add">
									<input type="text" class="name-dishtype-add form-control"
										name="name_dishtype_add" value="">
								</div>
								
								<label class="control-label title-add-cate col-sm-3">Nội Dung</label>
								 <div class="col-sm-9 name-adddishtype-no">
									<textarea class="form-control">	</textarea>
								</div>
								<div class="col-sm-9 name-adddishtype-have">
									<textarea class="form-control" name="conten_dishtype_add" id="area_add_category">	</textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2">
									<button type="submit" class="btn-add-dishtype  btn btn-info ">Thêm Mới</button>
								</div>
									  
									  
				</form>
			</div>
			
			<table class="col-sm-12  table table-bordered table-condensed  table-category">
			<tr class="tr-title-head-table"><th colspan="4"> <%=name_typecustomer%></th></tr>
			<tr class="title-table"	style="color: #00ff21; height: 45px; background-color: #808080">
				<th>Stt</th>
				<th>Nội Dung</th>			
				<th>Danh Mục Con</th>	
				<th>Chức Năng</th>			
			</tr>
			
			<% int stt=1;
			   for(int i=0;i<vtsl.size();i++){
				   
			%>
			<tr>
			<td><%= stt %></td>
			<td>
			<form role="form" method="post"
								action="${ pageContext.request.contextPath}/admin/category/update-dishtype" class="col-sm-12">
				<input class="id_dm" type="hidden" name="id_dishtype" value="<%=vtsl.get(i).getId_dm()%>">
				<input type="hidden" name="sp_id" value="<%=vtsl.get(i).getSp_id()%>" readonly>
				<label class="control-label  title-add-cate col-sm-2">Tên suất (*)</label>
								<div class="col-sm-10 name-add">
									<input class="name-cateparent" type="text" name="name_dishtype_edit" value="<%= vtsl.get(i).getTitle()%>">
								</div>
								
								<label class="control-label title-add-cate col-sm-2">Nội dung</label>
								 <div class="col-sm-10 name-editcategory-no">
									<textarea class="form-control"><%= vtsl.get(i).content_Shortened()%></textarea>
								</div>
								<div class="col-sm-10 name-editcategory-have">
									<textarea class="form-control" name="conten_dishtype_edit" id="area_edit_category_<%=vtsl.get(i).getId_dm()%>"><%= vtsl.get(i).getContent_dm()%></textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2">
									<button type="submit" class="btn-edit-dishtype btn-edit-cate btn btn-info ">Cập Nhật </button>
								</div>
									  
									  
				</form>			
			</td>
			<td class="a-xoa"><a class="a-child-cate" href="${pageContext.request.contextPath}/admin/category/dish-types-menu?id=<%=vtsl.get(i).getId_dm()%>">Xem</a></td>
			<td class="a-xoa"><a class="a-xoa-cate" href="${pageContext.request.contextPath}/admin/category/dish-types/delete?id=<%=vtsl.get(i).getId_dm()%>&sp_id=<%=vtsl.get(i).getSp_id()%>">Xóa</a></td>
			</tr>
			<%
				stt++;
			   }
			%>	
			</table>			
		</div>
		<!-- ----------content--------------- -->
	</div>
</div>

<script src="<%=request.getContextPath()%>/View/ckeditor/ckeditor.js"></script>
<script>
$(document).ready(function(){
	$('.name-cateparent').click(function(){
		var arr_btn=$('.btn-edit-dishtype');
		var form=$(this).parent("form");
		var contentcut=$('form').parents('table').find('.name-editcategory-no'); /* tìm area chứa 1 đoạn content,nhân vào area này sẽ tự động ẩn và show .area-content-news */ 
		var content=$('form').parents('table').find('.name-editcategory-have'); /* tìm area chứa content chính,mặc định ẩn  */
		content.each(function(index,el){
			if($(el).is(':visible')){
				$(el).hide();					
			}
		});
		contentcut.each(function(index,el){
			if($(el).is(':hidden')){
				$(el).show();		
				
			}
		});	
		arr_btn.each(function(){
			if($(this).is(':visible')){
				$(this).hide();
			}
		})
		$(this).parents('form').find('.btn-edit-dishtype').show();
	});
	//Thêm
	$('.btn-add-dishtype').click(function(){
		if ($('.contai-include-flash').is(':visible')) {
			$('.contai-include-flash').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error').is(':visible')){
			$('.contai-error').hide();
		}
		var name=$('.name-dishtype-add').val();
		if(name==""){
			$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
			//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
			$('.contai-error').show();// set lại display cho class chứa ô báo error
			$('.name-dishtype-add').focus();
			return false;
		}
		return true;
	});
	// sửa
	$('.btn-edit-dishtype').click(function(){
		if ($('.contai-include-flash').is(':visible')) {
			$('.contai-include-flash').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error').is(':visible')){
			$('.contai-error').hide();
		}
		var form=$(this).parents('form');
		var name=$(form).find('.name-cateparent');
		if($(name).val()==""){
			$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
			//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
			$('.contai-error').show();// set lại display cho class chứa ô báo error
			$('.location-show-error').focus();
			return false;
		}
		return true;
	});
	//xóa
	$('.a-xoa-cate').click(function(){
		if ($('.contai-include-flash').is(':visible')) {
			$('.contai-include-flash').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error').is(':visible')){
			$('.contai-error').hide();
		}
		return confirm('Bạn muốn xóa..?');		
	});
	// click show area content
	$('.name-adddishtype-no').click(function(){/* 	khi click vào area đang hiển thị nhưng ko set CKEDITOR thì sẽ tự động ẩn và gọi show của name_add_have */
		var form=$(this).parent("form");	
		$(this).hide();						
		$(form).find(".name-adddishtype-have").show();			
		CKEDITOR.replace('area_add_category');				
		
	});
	
	$('.name-editcategory-no').click(function(){/* 	khi click vào area đang hiển thị nhưng ko set CKEDITOR thì sẽ tự động ẩn và gọi show của name_edit_have */
		var form=$(this).parent("form");
		var arr_btn=$('.btn-edit-dishtype');
		var contentcut=$('form').parents('table').find('.name-editcategory-no'); /* tìm area chứa 1 đoạn content,nhân vào area này sẽ tự động ẩn và show .area-content-news */ 
		var content=$('form').parents('table').find('.name-editcategory-have'); /* tìm area chứa content chính,mặc định ẩn  */
		content.each(function(index,el){
			if($(el).is(':visible')){
				$(el).hide();					
			}
		});
		contentcut.each(function(index,el){
			if($(el).is(':hidden')){
				$(el).show();					
				$(el).parents('form').find(".btn-edit-dishtype").hide();
			}
		});	
		arr_btn.each(function(){
			if($(this).is(':visible')){
				$(this).hide();
			}
		})
		$(this).hide();		
		var id=$(form).find('.id_dm').val();
		$(form).find('.btn-edit-dishtype').show();
		$(form).find(".name-editcategory-have").show();			
		if(($(form).find(".name-editcategory-have").find('div')).length>0){		
			// kiểm tra sự tồn tại của cheditor,nêu tồn tai thì không replace
		}
		else{
			CKEDITOR.replace('area_edit_category_'+id);
		}						
		
	});
});

</script>

