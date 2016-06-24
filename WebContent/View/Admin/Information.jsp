<%@page import="model.Information"%>
<%@ page pageEncoding="utf-8"%>
<%
	Information info = (Information) request.getAttribute("AllInfo");
%>
<!-- ------Contai center main--- ---- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		<div class="col-sm-12 center-title">
			<strong>Quản Lý Thông Tin</strong>
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
		<%
			if (info != null) {
		%>
		<div class=" col-sm-12 content-main">


			<form class="col-sm-12 form-horizontal" role="form"
				enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/admin/information/update"
				method="post">



				<div class="col-sm-12 form-group">

					<label class="control-label td-title col-sm-2">Tên Nhà Hàng
						(*)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name_res"
							name="name_res" value="<%=info.getName_res()%>">
					</div>
				</div>


				<div class="col-sm-12 form-group">
					<label class="control-label td-title col-sm-2">Địa chỉ (*)</label>
					<div class="col-sm-10 ">
						<input type="text" class="form-control" id="add_res"
							name="add_res" value="<%=info.getAdd_res()%>">
					</div>
				</div>
				<div class="col-sm-12 form-group">
					<label class="control-label td-title col-sm-2">Tell (*)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tell" name="tell"
							value="<%=info.getTell()%>">
					</div>
				</div>
				<div class="col-sm-12 form-group">
					<label class="control-label td-title col-sm-2">Email (*)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="email" name="email"
							value="<%=info.getEmail()%>">
					</div>
				</div>

				<div class="col-sm-12 form-group info-logo">
					<label class="control-label td-title col-sm-2">Logo</label>
					<div class="col-sm-10">

						<div class="col-sm-2 div-stt-imglogo">
							<img src="<%=info.imageLink(request)%>" class="img-responsive">
						</div>

						<div class="col-sm-10 div-uploadimage">
							<input type="file" name="file-image" class="file-input" />
						</div>
					</div>
				</div>
				

			<div class="form-group col-sm-2 col-xs-12 show-addvan-infor">
				<input type="button" class="btn" id="btn-advan" value="Hiển thị Tuyển dụng và Giới thiệu">
					
			</div>
		

				

			<div class="form-group col-sm-12 contai-advan">
		<label class="control-label td-title col-sm-2">Giới thiệu</label>
			<div class="col-sm-10">
		      <textarea class="form-control" id="detail-gt" name="gioithieu">
		      	<%= info.getGioithieu() %>
		      </textarea>
		    </div>	    
		   </div>
		   
		   	<div class="form-group col-sm-12 contai-advan">
		<label class="control-label td-title col-sm-2">Tuyển Dụng</label>
			<div class="col-sm-10">
		      <textarea class="form-control" id="detail-td" name="tuyendung">
		      	<%= info.getTuyendung() %>
		      </textarea>
		    </div>	    
		   </div>
				
				<div class="col-sm-12 form-group">
					<div class="col-sm-offset-7 col-sm-5 btn-editinfo">
						<button type="submit" class="btn btn-info " id="btn-suathongtin">Cập
							Nhật</button>
					</div>
				</div>
			</form>



		

		</div>

		<%
			}
		%>
		<!-- ----------content--------------- -->





	</div>
</div>

<!-- ------------- -->

<script src="<%=request.getContextPath()%>/View/ckeditor/ckeditor.js"></script>
<SCRIPT> 
	CKEDITOR.replace( 'detail-gt' );
	CKEDITOR.replace( 'detail-td' );
</script>
<script>
	$(document).ready(function() {
		$('#btn-advan').click(function(){
			if($('.contai-advan').is(':visible')){
				$('.contai-advan').hide();
				$('#btn-advan').val("Hiển thị Tuyển dụng và Giới thiệu");
			}
			else{
				$('.contai-advan').show();
				$('#btn-advan').val("Ẩn nội dung");
			}
		});
		
		
		$('#btn-suathongtin').click(function() {
					if ($('.contai-include-flash').is(':visible')) {
						$('.contai-include-flash').hide();
					}// ẩn ô thông báo của flash
					var result = true;
					var name_res = $('#name_res').val();
					var add_res = $('#add_res').val();
					var tell = $('#tell').val();
					var email = $('#email').val();

					if (name_res == "") {
						$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
						//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
						$('.contai-error').show();// set lại display cho class chứa ô báo error
						$('#name_res').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"}); // thay đổi đường viền cho ô báo lỗi

						result = false;// gán biến làm cờ bằng fllse
					} else {$('#name_res').css(
						{
							"box-shadow" : "0 0 0px rgba(255,1,1,2)"
						});
					}

					if (add_res == "") {
						$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
						//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
						$('.contai-error').show();
						$('#add_res').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"});
						result = false;
					} else {
						$('#add_res').css({"box-shadow" : "0 0 0px rgba(255,1,1,2)"});
					}
					if (tell == "") {
						$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
						//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
						$('.contai-error').show();

						$('#tell').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"});
						result = false;
					} else {
						$('#tell').css({"box-shadow" : "0 0 0px rgba(255,1,1,2)"});
					}
					if (email == "") {
						$('#show-error').html('Bạn phải nhập đầy đủ thông tin ở các trường (*)');
						//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
						$('.contai-error').show();

						$('#email').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"});
						result = false;
					} else {
						$('#email').css({"box-shadow" : "0 0 0px rgba(255,1,1,2)"});
					}
					if (!isEmail(email)) {
						$('#show-error').html('Email không đúng định dạng');
						//	alert("Bạn phải nhập đầy đủ thông tin ở các trường (*)")
						$('.contai-error').show();

						$('#email').css({"box-shadow" : "0 0 10px rgba(255,1,1,2)"});
						result = false;
					} else {
						$('#email').css({"box-shadow" : "0 0 0px rgba(255,1,1,2)"});
					}

					return result;

				});
		});
	
	function isEmail(emailStr)
	 {
	         var emailPat=/^(.+)@(.+)$/
	         var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]"
	         var validChars="\[^\\s" + specialChars + "\]"
	         var quotedUser="(\"[^\"]*\")"
	         var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/
	         var atom=validChars + '+'
	         var word="(" + atom + "|" + quotedUser + ")"
	         var userPat=new RegExp("^" + word + "(\\." + word + ")*$")
	         var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$")
	         var matchArray=emailStr.match(emailPat)
	         if (matchArray==null) {
	                 return false
	         }
	         var user=matchArray[1]
	         var domain=matchArray[2]
	  
	         // See if "user" is valid
	         if (user.match(userPat)==null) {
	             return false
	         }
	         var IPArray=domain.match(ipDomainPat)
	         if (IPArray!=null) {
	             // this is an IP address
	                   for (var i=1;i<=4;i++) {
	                     if (IPArray[i]>255) {
	                         return false
	                     }
	             }
	             return true
	         }
	         var domainArray=domain.match(domainPat)
	         if (domainArray==null) {
	             return false
	         }
	  
	         var atomPat=new RegExp(atom,"g")
	         var domArr=domain.match(atomPat)
	         var len=domArr.length
	  
	         if (domArr[domArr.length-1].length<2 ||
	             domArr[domArr.length-1].length>3) {
	            return false
	         }
	  
	         if (len<2)
	         {
	            return false
	         }
	  
	         return true;
	 }	
</script>






