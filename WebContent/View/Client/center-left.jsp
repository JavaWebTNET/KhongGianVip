<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page pageEncoding="utf-8"%>
<%@page import="model.Category" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList" %>
<% @SuppressWarnings("unchecked")
	HashMap<Integer, ArrayList<Category>> hmAllDM=(HashMap<Integer, ArrayList<Category>>)request.getAttribute("hmcategorymenu");
	%>	
<div class="col-sm-3 col-xs-12 contai-left">
	<div class="menuleft">
	<% if(hmAllDM.size()>0){%>
		<ul class="nav nav-menuleft">
		<% for(Category itemlv0:hmAllDM.get(0)){ %>
			<li class="li-lv1"><a href="javascript:void(0)" class="a-parent"><%=itemlv0.getTitle() %></a>
				<!-- 	2------- -->
					<% if(hmAllDM.containsKey(itemlv0.getId_dm())){ %>	
						<ul class="nav menu-hide" id="1">
							<% for(Category itemlv1:hmAllDM.get(itemlv0.getId_dm())){ %>
								<li><a href="<%=request.getContextPath()%>/menu/dish-type?category=<%=itemlv1.getId_dm()%>"><%=itemlv1.getTitle() %></a></li>
							<%} %>
							
						</ul>
					<%} %>		
						<span class="I"></span>
			</li>			
		<%} %>
	<%} %>
	
		</ul>
	</div>

	<!-- ----------------------- -->
	<div class="bookingtable">
		<span>ĐẶT BÀN</span>
		<div class="number_phone">
			<span>0511.3.789.887</span>
		</div>
	</div>

	<!-- ----------------------- -->

	<!-- ----------------------- -->
	<div class="link_face">
		<iframe
			src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2Ffacebook&tabs&width=340&height=150&small_header=false&data-adapt_container_width=true&hide_cover=false&show_facepile=false&appId"
			width="340" height="150" style="border: none; overflow: hidden"></iframe>

	</div>

	<!-- ----------------------- -->
</div>