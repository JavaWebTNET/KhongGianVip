<%@ page pageEncoding="utf-8"%>
<!-- ---------- -->
<div class="col-sm-9 col-xs-12 contai-center-content">
	<div class="col-sm-12 col-xs-12 center-content-title">
		<span>Thông Báo</span>
	</div>
	<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
			%>

			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong>Thông báo Lỗi: </strong><span style="color: red;"><%=error%></span>
			</div>

			<%
				}
			%>
	
		
</div>
<!-- ---------- -->