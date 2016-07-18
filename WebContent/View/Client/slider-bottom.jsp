<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="model.Dish" %>
<%
@SuppressWarnings("unchecked")
Vector<Dish> dishtasty=(Vector<Dish>) request.getAttribute("dishtasty");
%>
<div class="row">
	<div class="col-sm-12 col-xs-12 contai-slider-boottom">
		<div class="slider-bottom-title">
			<p>Moùn Ngon</p>
		</div>

		<div class="owl-carousel slider-boottom-owl-carousel">
		<% for(Dish item:dishtasty){ %>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%= item.imageLink(request)%>" class="img-responsive"></a>
			</div>
			<%} %>
			<%-- <div class="slider-item owl-item">
				<a href="#"> <img src="<%=request.getContextPath()%>/View/Image/sp2.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp1.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp3.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp1.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"> <img src="<%=request.getContextPath()%>/View/Image/sp2.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp3.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp1.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"> <img src="<%=request.getContextPath()%>/View/Image/sp2.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp1.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"> <img src="<%=request.getContextPath()%>/View/Image/sp2.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp3.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"><img src="<%=request.getContextPath()%>/View/Image/sp1.png" class="img-responsive"></a>
			</div>
			<div class="slider-item owl-item">
				<a href="#"> <img src="<%=request.getContextPath()%>/View/Image/sp2.png" class="img-responsive"></a>
			</div> --%>
		</div>
		<span class="next"></span> <span class="prev"></span>

	</div>
	<div class="col-sm-12 col-xs-12 div-empty"></div>
</div>

<!-- ------------------------- -->