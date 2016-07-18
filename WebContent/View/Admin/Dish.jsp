<%@ page pageEncoding="utf-8"%>
<% String name_search_input=(String) request.getParameter("name_search"); %>
<!-- ------Contai center main------- -->

<div class="col-sm-10 col-xs-12 center-right">
	<div class="col-sm-12 center-right-child">
		
		<!-- ----------content--------------- -->
		<div class="col-sm-12 center-title title-dish">
			<strong>Quản Lý Món Ăn</strong>
		</div>  
		<div class=" col-sm-12 contai-include-flash-dish">
			<%@ include file="/View/Admin/flash_dish.jsp"%>
		</div>
		<div class="col-sm-12 contai-error-dish">
			<div class="alert alert-danger">
				<button type="button" class="close location-show-error-dish" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<strong id="show-error-dish"></strong>
			</div>
		</div>
		<!-- ----------content--------------- -->
		<div class="content-main">
			<div class="form-group col-sm-offset-3 col-sm-6 contai-input-seachdish">
					<label class="control-label title-name-search col-sm-3">Tên Món</label>
					<div class="col-sm-8 name-dish">
						<input type="text" class="name-dish-search form-control" id="searchname"
							name="title_image" value="<%= name_search_input!=null?name_search_input:""%>">
							<span class="icon-search"></span>
					</div>
					

			</div>
			<div id="location_insert_seach"></div>
				
				
			
		
		</div>
		<!-- ----------content--------------- -->

	</div>
	</div>


<!-- ------------- -->

<script>
	$(document).ready(function() {			
			var cl=$('.name-dish-search');
			 Search(cl); //call function Search when page load finish, cl="" show all dish		
		
		$(".name-dish-search").keyup(function() {
			var cl=$(this);
		 Search(cl);			
		});			
	}); 
	
	function Search(cl){
		var searchname = $(cl).val();		
			$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/admin/dish/search-name",
					data :{namedish:searchname},
					cache : false,
					//dataType:"json",
					success : function(html) {
						$("#location_insert_seach").html(html).show();
					}
			  });		  
	   }
</script>
