<%@ page pageEncoding="utf-8"%>
<%@page import="model.Dish"%>
<%@page import="java.util.Vector"%>
<%
@SuppressWarnings("unchecked")
Vector<Dish> vtdish_seach =(Vector<Dish>) request.getAttribute("dish_seach");	
String name_search_rs=(String) request.getAttribute("name_search_input");
    
%>
	<table class="col-sm-12  table table-bordered table-condensed  table-dish">		
			<tr class="title-table"	style="color: #00ff21; height: 45px; background-color: #003300">
				<th>Stt</th>
				<th>Tên Món Ăn</th>		
				<th>Hình Ảnh</th>	
				<th>Giá</th>
				<th>Tasty</th>					
				<th colspan="2">Chức Năng</th>			
			</tr>
			
			<% int j=1;
			   for(int i=0;i<vtdish_seach.size();i++){
				   
			%>
			<tr>
			<td><%= j %></td>
			<td class="td-name-dish"><%= vtdish_seach.get(i).getName_dish()%></td>
			<td class="td-image-dish">
			<% if(vtdish_seach.get(i).getImage_dish()!=null){ %>
			<img src="<%= vtdish_seach.get(i).imageLink(request)%>">
			<%} %>
			</td>			
			<td><%= vtdish_seach.get(i).getPrice() %></td>			
			<td>
			<input class="checkbox_staty" type="checkbox" value="<%=vtdish_seach.get(i).getId_dish()%>" <%= vtdish_seach.get(i).getTasty()==1?"checked":"" %>></td>
			<td class="a-xoa"><a class="a-child-cate" href="${pageContext.request.contextPath}/admin/dish/detail-dish?id_dish=<%=vtdish_seach.get(i).getId_dish()%>">Chi Tiết</a></td>
			<td class="a-xoa"><a class="a-xoa-dish" href="${pageContext.request.contextPath}/admin/dish/delete?id=<%=vtdish_seach.get(i).getId_dish()%>&name_search=<%=name_search_rs!=""?name_search_rs:"" %>">Xóa</a></td>
			</tr>
			<%
				j++;
			   }
			%>	
			</table>
<script type="text/javascript">
	$(document).ready(function(){
		$('.checkbox_staty').click(function(){
			var id_dish=$(this).val();
			if($(this).is(':checked')){				
				 staty(id_dish,"tasty-checked");
			}
			else{				
				 staty(id_dish,"tasty-unchecked")
			}
			
		});	
		
		//xóa
		$('.a-xoa-dish').click(function(){
			if ($('.contai-include-flash-dish').is(':visible')) {
				$('.contai-include-flash-dish').hide();
			}// ẩn ô thông báo của flash
			if($('.contai-error-dish').is(':visible')){
				$('.contai-error-dish').hide();
			}
			return confirm('Bạn muốn xóa..?');		
		});
		
	});
	
	function staty(id,status){
			
			$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/admin/dish/"+status,
					data :{id_dish:id},
					cache : false,
					//dataType:"json",
					 success : function(result) {
						 alert(result); 
					} 
			  });		  
	   }

</script>
			
			 