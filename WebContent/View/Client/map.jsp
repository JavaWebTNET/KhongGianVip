<!DOCTYPE html>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@ page import="java.util.Vector" %>
<%  
	String content_connected=(String) request.getAttribute("conten_connected");
%>

<div class="col-sm-9 col-xs-12 contai-center-content contai_content_info" id="location-focus">
	
	<div class="col-sm-12 col-xs-12 center-content-title title_content">
		<span class="title_introduce ">Bản Đồ</span>
	</div>
	
	<div class="col-sm-12 center-content content-map">
		<div class="change_map"><img src="<%= request.getContextPath()%>/View/Image/refresh.png" class="img-responsive" alt="thay đổi loại bản đồ"></div>
		<div class="location_map">
		<div class="turn_map"><img src="<%= request.getContextPath()%>/View/Image/turn_map.png" class="img-responsive"></div>
		<img src="<%= request.getContextPath()%>/View/Image/map1.png" class="img-responsive map1">
		<img src="<%= request.getContextPath()%>/View/Image/map2.png" class="img-responsive map2">
		
		</div>
		<div class="location_map_google">
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3834.308345283273!2d108.20714351401999!3d16.04948124423187!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314219b946ffafed%3A0xb12ade47f7f11430!2zMzQgTmd1eeG7hW4gSOG7r3UgVGjhu40sIFRoYW5oIEtow6osIMSQw6AgTuG6tW5nLCBWaWV0bmFt!5e0!3m2!1sen!2s!4v1468471182658" width="800" height="600"  style="border:0" ></iframe>
		</div>
		
	</div>
	
</div>
<!-- ---------- -->
<script>
$(document).ready(function(){
	$('.change_map').click(function(){		
		if($('.location_map').is(':hidden')){		
			$('.location_map_google').hide();
			$('.location_map').show();		
		}
		else{			
			$('.location_map').hide();
			$('.location_map_google').show();
		}
	});
	$('.turn_map').click(function(){		
		if($('.map1').is(':hidden')){		
			$('.map2').hide();
			$('.map1').show();		
		}
		else{			
			$('.map1').hide();
			$('.map2').show();
		}
	});
});
</script>