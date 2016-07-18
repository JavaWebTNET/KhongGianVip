<%@ page pageEncoding="utf-8"%>
<%@ page import="java.util.Vector" %>
<%@ page import="model.Slider" %>
<!-- ------- container slider top -------------------- -->
<% 
@SuppressWarnings("unchecked")
Vector<Slider> slidertop=(Vector<Slider>) request.getAttribute("slidertop");
%>
<div class="row">
	<div class="col-sm-12 col-xs-12 contai-slidertop">
		<div class="slidertop-content  carousel slide" data-ride="carousel"
			id="myCarousel">
			<!-- <ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				
			</ol> -->

			<div class="carousel-inner slidertop-img" role="listbox">
			<% if(slidertop.size()>0){ %>
				<div class="item active">
					<img src="<%= slidertop.get(0).imageLink(request)%>" alt="Chania">
				</div>
				<% for(int i=1;i<slidertop.size();i++){ %>
				<div class="item">
					<img src="<%= slidertop.get(i).imageLink(request)%>" alt="Chania">
				</div>
			<%}} %>
				

			</div>

		</div>
	</div>
</div>
<!-- --------------------- -->