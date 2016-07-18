<%@ page pageEncoding="utf-8"%>
<div class="row">

	<div class="col-sm-12 col-xs-12 contai-header">

		<div class="col-sm-2 col-xs-2 header-logo">
			<img src="<%=request.getContextPath()%>/View/Image/logo.png" class="img-responsive">
		</div>

		<div class="col-sm-4 col-xs-4 header-icon">
			<img src="<%=request.getContextPath()%>/View/Image/icon-coffee.png" class="img-responsive">
		</div>
		<div class="col-sm-6 col-xs-6 header-slogan">
			<img src="<%=request.getContextPath()%>/View/Image/slogan.png" class="img-responsive">
		</div>
	</div>
</div>

<!-- ----------------- -->

<!-- --------- container menu top----------- -->
<div class="row">
	<div class="col-sm-12 col-xs-12 navbar-default contai-menutop">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menu">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand visible-xs-block" href="">Menu</a>
		</div>
		<div class="navbar-collapse collapse" id="menu">

			<ul class="nav navbar-nav ul-menu-main">
				<li><a href="<%= request.getContextPath()%>">TRANG CHỦ</a></li>
				<li><a href="<%= request.getContextPath()%>/introduce">GIỚI THIỆU</a></li>
				<li><a href="<%= request.getContextPath()%>/news">TIN TỨC</a></li>
				<li><a href="<%= request.getContextPath()%>/service">DỊCH VỤ</a></li>
				<li><a href="<%= request.getContextPath()%>/recruitment">TUYỂN DỤNG</a></li>
				<li><a href="<%= request.getContextPath()%>/connected">LIÊN HỆ</a></li>
				<li><a href="<%= request.getContextPath()%>/map">BẢN ĐỒ</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- --------------- -->