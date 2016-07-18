<%@page import="model.Slider"%>
<%@page import="model.ImageStore"%>
<%@page import="model.Information"%>
<%@page import="java.util.Vector"%>
<%@ page pageEncoding="utf-8"%>
<%@ page import="model.News"%>
<%
if(request.getAttribute("allnews")!=null){
	@SuppressWarnings("unchecked")
	Vector<News> vtns = (Vector<News>) request.getAttribute("allnews");
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		<div class="col-sm-12 center-title">
			<strong>Quản Lý Tin Tức</strong>
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
			<div class=" col-sm-12 form-group  form-add_news">
				<label class="col-sm-offset-5 col-sm-2 col-xs-12 title-add-news">Thêm Tin Mới</label>
				<form role="form" method="post"
								action="${ pageContext.request.contextPath}/admin/news/create" class="col-sm-12">
								
									 
							 <label class="control-label td-title title-edit-news col-sm-2">Tiêu đề (*)</label>
								<div class="col-sm-10 title_news">
									<input type="text" class="add_title_news form-control"
										name="title_add_news" value="">
								</div>
								<label class="control-label td-title title-edit-news col-sm-2">Nội dung (*)</label>
								<div class="col-sm-10 area-add-news">
									<textarea class="form-control">	</textarea>
								</div>
								<div class="col-sm-offset-2 col-sm-10 area-addhide-news">
									<textarea class="form-control" name="add_content" id="area_add_news">	</textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2 news_btn_add">
									<button type="submit" class="btn-add-news btn btn-info ">Thêm Mới</button>
								</div>
									  
									 
				</form>
			</div>
			
			<table	class="col-sm-12  table table-bordered table-condensed  table-news">
					<tr class="title-table"
						style="color: #00ff21; height: 45px; background-color: #808080">
						<th>&nbsp;&nbsp;stt&nbsp;&nbsp;</th>
						<th>Nội Dung</th>
						<th>Chức năng</th>
					</tr>
					<%
					if(vtns.size()>0){
						int stt = 1;
						for (int i = 0; i < vtns.size(); i++) {
					%>
					<tr>
						<td><%=stt%></td>
						<td>
							<form role="form" method="post"
								action="${ pageContext.request.contextPath}/admin/news/update" class="form-edit_news">
								<input type="hidden" name="id_news" class="id_news"
									value="<%=vtns.get(i).getId()%>">
								<label class="control-label td-title title-edit-news col-sm-2">Tiêu đề (*)</label>
								<div class="col-sm-10 title_news">
									<input type="text" class="input_title_news form-control"
										name="title_news" value="<%=vtns.get(i).getTitle()%>">
								</div>
								<label class="control-label td-title title-edit-news col-sm-2">Nội dung (*)</label>
								<div class="col-sm-10 area-contencut-news">
									<textarea class="form-control"  ><%=vtns.get(i).subDetail()+"...."%>
		    			  			</textarea>
								</div>
								<div class="col-sm-10 area-content-news">
									<textarea class="conten_news form-control" name="content_news" id="area-content-news-<%=vtns.get(i).getId()%>" name="content-news"><%=vtns.get(i).getContent()%>
		    			  			</textarea>
								</div>
								<div class="col-sm-offset-10 col-sm-2 news_btn_edit">
									<button type="submit" class="btn-edit-news btn btn-info ">Cập Nhật</button>
								</div>
							</form>
						</td>
						<td class="a-xoa"><a class="a-xoa-slider"
							href="${pageContext.request.contextPath}/admin/news/delete?id=<%=vtns.get(i).getId() %>">Xóa</a></td>

					</tr>
					<%
						stt++;
						}
					}
					%>
			</table>
		</div>
		
		<!-- ----------content--------------- -->
	</div>
</div>
<%} %>
<script src="<%=request.getContextPath()%>/View/ckeditor/ckeditor.js"></script>

<script>
	$(document).ready(function(){	
		$('.area-contencut-news').click(function(){	
			var form=$(this).parent("form");
			var content=$('form').parents('table').find('.area-content-news'); /* tìm area chứa content chính,mặc định ẩn  */
			var contentcut=$('form').parents('table').find('.area-contencut-news'); /* tìm area chứa 1 đoạn content,nhân vào area này sẽ tự động ẩn và show .area-content-news */ 
			content.each(function(index,el){
				if($(el).is(':visible')){
					$(el).hide();					
				}
			});
			contentcut.each(function(index,el){
				if($(el).is(':hidden')){
					$(el).show();					
					$(el).parents('form').find(".btn-edit-news").hide();
				}
			});	
		
			var id=$(form).find('.id_news').val();			
			$(this).hide();						
			$(form).find(".area-content-news").show();
			$(form).find(".btn-edit-news").show();
			if(($(form).find(".area-content-news").find('div')).length>0){		
				// kiểm tra sự tồn tại của cheditor,nêu tồn tai thì không replace
			}
			else{
				CKEDITOR.replace('area-content-news-'+id);
			}		
			
		});
		$('.btn-edit-news').click(function(){
			
			 if ($('.contai-include-flash').is(':visible')) {
				$('.contai-include-flash').hide();
			}// ẩn ô thông báo của flash
			var parent=$(this).parents('form');			
			var title=$(parent).find('.input_title_news');				
			 if(title.val()==""){
				$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
				//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
				$('.contai-error').show();// set lại display cho class chứa ô báo error
				$(title).focus();
				return false;
			} 			
			return true;
			
		});
		
		
		$('.area-add-news').click(function(){	
			var form=$(this).parent("form");	
			$(this).hide();						
			$(form).find(".area-addhide-news").show();
			$(form).find(".btn-add-news").show();			
			CKEDITOR.replace('area_add_news');				
			
		});
		$('.btn-add-news').click(function(){
			
			 if ($('.contai-include-flash').is(':visible')) {
				$('.contai-include-flash').hide();
			}// ẩn ô thông báo của flash
			var parent=$(this).parents('form');			
			var title=$(parent).find('.add_title_news');				
			 if(title.val()==""){
				$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
				//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
				$('.contai-error').show();// set lại display cho class chứa ô báo error
				$(title).focus();
				return false;
			} 			
			return true;
			
		});
		
		$('.a-xoa-slider').click(function(){
			if ($('.contai-include-flash').is(':visible')) {
				$('.contai-include-flash').hide();
			}// ẩn ô thông báo của flash
			if($('.contai-error').is(':visible')){
				$('.contai-error').hide();
			}
			return confirm('Bạn muốn xóa..?');		
		});
	});
</script>
