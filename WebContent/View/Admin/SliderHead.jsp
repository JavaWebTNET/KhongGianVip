<%@page import="model.Slider"%>
<%@page import="model.ImageStore"%>
<%@page import="model.Information"%>
<%@page import="java.util.Vector"%>
<%@ page pageEncoding="utf-8"%>
<%
	@SuppressWarnings("unchecked")
	Vector<Slider> vtsl = (Vector<Slider>) request.getAttribute("allslider");
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		<div class="col-sm-12 center-title">
			<strong>Quản Lý Slider</strong>
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
		<div class="contai-table-slider">
			<table class="col-sm-12  table table-bordered table-condensed  table-slider">
				<tr class="title-table"
					style="color: #00ff21; height: 45px; background-color: #808080">
					<th>&nbsp;&nbsp;stt&nbsp;&nbsp; </th>					
					<th>Ảnh</th>											
					<th colspan="2">Chức năng</th>					
				</tr>
				<%
					int stt = 1;
					for (int i = 0; i < vtsl.size(); i++) {
				%>
				<tr>
					<td><%=stt%></td>
					
					
					<td class="td-image-imagestore"><img src="<%=vtsl.get(i).imageLink(request)%>" class="img-responsive"> </td>
					<td>
				
						<form role="form" method="post" enctype="multipart/form-data"
							action="${ pageContext.request.contextPath}/admin/slider/update">
							<input type="hidden" name="id_slider" value="<%=vtsl.get(i).getId()%>">
							<div class="col-sm-4 title-input-slider">
								<input type="text" class="form-control" id="title_slider"
							name="title_slider" value="<%=vtsl.get(i).getTitle()%>">
							</div> 
							<div class="col-sm-6 slider_uploadimage file-upload-image" >
								<input type="file" name="file-image" class="file-input"/>
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn-edit-slider btn btn-info ">Sửa</button>  <!-- Sữa sẽ đổi thành 'CCapj Nhật' khi click vào input name=file-image -->
							</div>
						</form>
						
					</td>

					<td class="a-xoa"><a class="a-xoa-slider" href="${pageContext.request.contextPath}/admin/slider/delete?id=<%=vtsl.get(i).getId() %>">Xóa</a></td>

				</tr>

				<%
					stt++;
					}
				%>

			</table>
			</div>


			<form class="col-sm-12 form-horizontal" role="form"
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/admin/slider/create"
				method="post">
				<div class="form-group">
					<label class="control-label  col-sm-2">Tiêu đề</label>
					<div class="col-sm-2 title-image">
						<input type="text" class="form-control" id="title_image"
							name="title_image" value="">
					</div>
					<div class="col-sm-5 div-addimage">
						<input type="file" name="file-image" id="file_image"
							class="file-input" />
					</div>
					<div class="col-sm-3 btn-slider">
						<button type="submit" class="btn btn-info " id="btn-addslider">Thêm
							Ảnh</button>
					</div>

				</div>


			</form>

		</div>


		<!-- ----------content--------------- -->





	</div>
</div>

<script>
	$(document).ready(function() {
		$('.a-xoa-slider').click(function(){
			if ($('.contai-include-flash').is(':visible')) {
				$('.contai-include-flash').hide();
			}// ẩn ô thông báo của flash
			if($('.contai-error').is(':visible')){
				$('.contai-error').hide();
			}
			return confirm('Bạn muốn xóa..?');		
		});
		
		$('#btn-addslider').click(function() {
			if ($('.contai-include-flash').is(':visible')) {
				$('.contai-include-flash').hide();
			}// ẩn ô thông báo của flash
		});
		
		$('.file-upload-image').click(function(){				
			 var hh=$(this).parent('form').find('button').html("Cập Nhật");		
		
		});
		$('.title-input-slider').click(function(){				
			 var hh=$(this).parent('form').find('button').html("Cập Nhật");		
		
		});
	});
	
	
</script>
