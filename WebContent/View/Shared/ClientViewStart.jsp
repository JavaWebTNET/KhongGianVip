<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Không Gian Vip</title>
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

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"
	media="all" rel="stylesheet" type="text/css">

<!-- Basic stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/owl-carousel/owl.carousel.css">
<!-- Default Theme -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/owl-carousel/owl.theme.css">
<!-- Include js plugin -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/owl-carousel/Css.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/CSS/clientCss.css">

</head>

<body>

	<div class="container contai-main">

		<!-- ----------- container header---------- -->
		<%@ include file="/View/Client/header.jsp"%>
		<!-- ------------------------------------------- -->
		<%@ include file="/View/Client/sliderheader.jsp"%>
		<!-- ------------------ceanter------------------------- -->

		<div class="row row-center-content">
			<div class="row ">
				<div class="col-sm-12 col-xs-12 contai-center">
					<!-- ----------- container header---------- -->
					<%@ include file="/View/Client/center-left.jsp"%>
					<!-- ------------------------------------------- -->

					<!-- ----------- container header---------- -->
					<%@ include file="/View/Client/center-content.jsp"%>
					<!-- ------------------------------------------- -->
				</div>
			</div>
		</div>
		<!-- ------------------------------------------- -->

		<!-- ----------- container header---------- -->
		<%@ include file="/View/Client/slider-bottom.jsp"%>
		<!-- ------------------------------------------- -->

		<!-- ----------- container header---------- -->
		<%@ include file="/View/Client/footer.jsp"%>
		<!-- ------------------------------------------- -->


	</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="<%= request.getContextPath()%>/View/owl-carousel/owl.carousel.js"></script>
<script>
/* a-parent */
 
 
$(document).ready(function(){
if( $('#location-focus').length>0){	
	$('html, body').animate({ scrollTop: $('#location-focus').offset().top }, 'slow');
}	
 $(".I, .a-parent").click(function(){

 	hide_li($(this).parents("ul").find("li"));
 	var child=$(this).parents("li").find("ul");
 	// check in tag ul have exist ul child?
 	if(child.length!=0){
        if(child.is(':visible'))
        {
        	child.slideUp();
        	$(this).parents("li").removeClass('li-menu-show')
        }
        else{
        	child.slideDown();
        	$(this).parents("li").addClass('li-menu-show');
        	
        }
    }
    });

 function hide_li(li) {
 	li.each(function(index, el) {
 		var child=$(el).find("ul");
 		if(child.length!=0){
 			if(child.is(':visible'))
	        {
	        	child.slideUp();
	        	$(el).removeClass('li-menu-show')
	        }
 		}
 	});
 }


var owl = $('.owl-carousel');
owl.owlCarousel({
    items:6,
    loop:true,

    margin:10,
    autoPlay:true,
    autoplayTimeout:1000,
    autoplayHoverPause:true

});
$(".next").click(function(){
    owl.trigger('owl.next');
  })
  $(".prev").click(function(){
    owl.trigger('owl.prev');
  })
owl.on('mousewheel', '.owl-stage', function (e) {
    if (e.deltaY>0) {
        owl.trigger('next.owl');
    } else {
        owl.trigger('prev.owl');
    }
    e.preventDefault();


});
   

    
});


</script>
</html>