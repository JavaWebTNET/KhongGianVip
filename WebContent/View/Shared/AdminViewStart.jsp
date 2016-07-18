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

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css">
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
							<li><a href="<%=request.getContextPath()%>/admin/category">QUẢN LÝ DANH MỤC</a></li>
							<li><a href="<%=request.getContextPath()%>/admin/dish">QUẢN LÝ MÓN ĂN</a></li>
							<li><a
								href="<%=request.getContextPath()%>/admin/information">QUẢN
									LÝ THÔNG TIN</a></li>

							<li><a href="#">QUẢN LÝ SẢN PHẨM</a></li>

							<li><a href="<%= request.getContextPath()%>/admin/news">QUẢN LÝ TIN TỨC</a></li>
							<li><a href="<%=request.getContextPath()%>/admin/service">QUẢN LÝ DỊCH VỤ </a></li>

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
					 			
				<%}else if(layout.equals("dish_type")){%>
					 <%@ include file="/View/Admin/Category_dish_type.jsp"%>
					 
				<%}else if(layout.equals("dishtype_menu")){%>
					 <%@ include file="/View/Admin/menuInDishType.jsp"%>	
					
				<%}else if(layout.equals("dish_menu")){%>
					 <%@ include file="/View/Admin/Dish_Menu.jsp"%>	
					 
				<%}else if(layout.equals("detail_dish")){%>
					 <%@ include file="/View/Admin/Detail_Dish.jsp"%>	
					 
				<%}else if(layout.equals("dish")){%>
					 <%@ include file="/View/Admin/Dish.jsp"%>	
					 
				<%}else if(layout.equals("result_search_dish")){%>
					 <%@ include file="/View/Admin/result.jsp"%>
					 
				<%}else if(layout.equals("service")){%>
				 <%@ include file="/View/Admin/service.jsp"%>
					 		   
				<%}else if(layout.equals("cate_parent")){%>
					 <%@ include file="/View/Admin/CategoryParent.jsp"%>
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