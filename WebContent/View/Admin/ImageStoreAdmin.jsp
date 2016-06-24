<%@page import="model.ImageStore"%>
<%@page import="model.Information"%>
<%@page import="java.util.Vector"%>
<%@ page pageEncoding="utf-8"%>
<%
	@SuppressWarnings("unchecked")
	Vector<ImageStore> vtimst = (Vector<ImageStore>) request.getAttribute("imagestore");
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		<div class="col-sm-12 center-title">
			<strong>Quản Lý Ảnh</strong>
		</div>
		<div class=" col-sm-12 contai-include-flash">
			<%@ include file="/View/Admin/flash.jsp"%>
		</div>
		<div class="col-sm-12 contai-error">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong id="show-error"></strong>
			</div>
		</div>
		<!-- ----------content--------------- -->

		<div class=" col-sm-12 content-main">
			<table class="col-sm-12 table table-bordered table-condensed table-imagestore">
				<tr class="title-table"
					style="color: #00ff21; height: 45px; background-color: #808080">
					<th>stt</th>
					<th>Tiêu đề</th>
					<th>ảnh</th>
					<th >Đường dẫn</th>
					<th>Chức năng</th>
				</tr>
				<%
					int stt = 1;
					for (int i = 0; i < vtimst.size(); i++) {
				%>
				<tr>
					<td><%=stt%></td>
					<td><%=vtimst.get(i).getTitle()%></td>
				
					<td class="td-image-imagestore"><img src="<%= vtimst.get(i).imageLink(request)%>" class="img-responsive" ></td>
					<td class="url-image">
					<input type="text" value="<%= vtimst.get(i).imageLink(request)%>" readonly >					
					<td class="a-xoa"><a class="a-xoa-imagestore" href="${pageContext.request.contextPath}/admin/Imagestore/delete?id=<%=vtimst.get(i).getId() %>">Xóa</a></td>
				</tr>
					
				<%
				stt++;
					}
				%>

			</table>


			<form class="col-sm-12 form-horizontal" role="form"
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/admin/Imagestore/create"
				method="post">
				<div class="form-group">
					<label class="control-label  col-sm-2">Tiêu đề (*)</label>
					<div class="col-sm-2 title-image">
						<input type="text" class="form-control" id="title_image"
							name="title_image" value="">
					</div>
					<div class="col-sm-5 div-addimage">
						<input type="file" name="file-image" id="file_image" class="file-input" />
					</div>
					<div class="col-sm-3 btn-slider">
						<button type="submit" class="btn btn-info " id="btn-addimage">Thêm Ảnh</button>
					</div>

				</div>


			</form>

		</div>


		<!-- ----------content--------------- -->





	</div>
</div>

<script>
$('document').ready(function(){
	
	$('.a-xoa-imagestore').click(function(){
		if ($('.contai-include-flash').is(':visible')) {
			$('.contai-include-flash').hide();
		}// ẩn ô thông báo của flash
		if($('.contai-error').is(':visible')){
			$('.contai-error').hide();
		}
		return confirm('Bạn muốn xóa ảnh..?');		
	});
	
	$('#btn-addimage').click(function(){
		
		 if ($('.contai-include-flash').is(':visible')) {
			$('.contai-include-flash').hide();
		}// ẩn ô thông báo của flash		
		var title=$('#title_image').val();		
		if(title==""){
			$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
			//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
			$('.contai-error').show();// set lại display cho class chứa ô báo error
			$('#title_image').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"}); // thay đổi đường viền cho ô báo lỗi
		
			return false;// gán biến làm cờ bằng fllse
		}
			$('#title_image').css({"box-shadow" : "0 0 0px rgba(255,1,1,2)"});		
				
		return true;
	});
	
});
</script>
