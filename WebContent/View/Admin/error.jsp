<%@ page pageEncoding="utf-8"%>
<!-- ------Contai center main------- -->
<div class="col-sm-10 col-xs-12 center-right">
	<div class="center-right-child">
		<div class="center-title">

			<strong>Thông Báo</strong>
		</div>
		<!-- ----------content--------------- -->
		<div class="content-main">
			<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
			%>

			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong>Thông báo lỗi từ trang Admin của nhà hàng Không
					Gian Vip: </strong><span style="color: red;"><%=error%></span>
			</div>

			<%
				}
			%>

		</div>
		<!-- ----------content--------------- -->

	</div>
</div>

<!-- ------------- -->