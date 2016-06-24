<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="w@idth=device-width, initial-scale=1.0">
<title>Bootstrap 3 Tabs</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/CSS/CssAdmin.css">
</head>
<body>
	<div class="container-fluid contai-main">

		<!-- ----------row header-------------- -->
		<div class="row">
			<div class="col-sm-12 col-xs-12 contai-header">
				<img src="<%=request.getContextPath()%>/View/Image/logo.png">
				<span class="name-hotel">KHÔNG GIAN VIP</span>
			</div>
		</div>
		<!-- ---------------------------------------- -->
		<!-- --------------------center----------------- -->
		<div class="row">
			<div class="col-sm-12 col-xs-12 contai-center">
				<!-- ------------- -->
				<div class="col-sm-2 col-xs-12 center-left">
					<div class="center-title">
						<strong> CHỨC NĂNG </strong>
					</div>
					<!-- <div class="dd"></div> -->
					<!-- ------------- contai menu left--------------- -->
					<div class="div-menu-one">
						<ul class="nav ul-menu-one">

							<li><a
								href="<%=request.getContextPath()%>/admin/information">QUẢN
									LÝ THÔNG TIN</a></li>

							<li><a href="#">QUẢN LÝ SẢN PHẨM</a></li>

							<li><a href="#">QUẢN LÝ DỊCH VỤ </a></li>
							<li><a href="<%= request.getContextPath()%>/admin/news">QUẢN LÝ TIN TỨC</a></li>

							<li><a href="<%= request.getContextPath()%>/admin/slider">QUẢN LÝ SLIDER</a></li>
							<li><a href="<%=request.getContextPath()%>/admin/Imagestore">QUẢN LÝ ẢNH</a></li>
						</ul>
					</div>

				</div>
				<!-- ------------- -->
				<!-- include content in here -->
				<!-- ------Contai center main------- -->


				<% String layout = (String) request.getAttribute("layout"); %>
				<% if(layout.equals("home")){%>
				<%@ include file="/View/Admin/HomeAdmin.jsp"%>
				
				<%}else if(layout.equals("information")){%>
					<%@ include file="/View/Admin/Information.jsp"%>
					
				<%}else if(layout.equals("imagestoreddmin")){%>
					 <%@ include file="/View/Admin/ImageStoreAdmin.jsp"%>
					 
				<%}else if(layout.equals("error")){%>
					 <%@ include file="/View/Admin/error.jsp"%>
				<%}else if(layout.equals("slider")){%>
					 <%@ include file="/View/Admin/SliderHead.jsp"%>
				<%}else if(layout.equals("news")){%>
					 <%@ include file="/View/Admin/news.jsp"%>
				<%} %>
				
				<!-- ------------- -->
				<!-- ------------- -->

				<!-- ------------- -->

			</div>
		</div>

		<!-- ------------------ -->

	</div>
</body>
</html>