<% int pageno = (int) request.getAttribute("pageno");%>
<% int totalPage = (int) request.getAttribute("totalPage");%>
<% if(pageno<1) pageno = 1;  %>
<% if(pageno>totalPage) pageno = totalPage;  %>
<% int frompage = (totalPage>10)?(totalPage-pageno>9)?pageno:totalPage-9:1; %>
<% int topage = (totalPage>10)?frompage+9:totalPage; %>

<ul class="pagination">

	<%if(totalPage>10 && pageno>2) { %>
		<li><a href="?page=1" rel="first"><%="<<" %></a></li>
	<%}%>

	<%if(pageno>1) { %>
		<li><a href="?page=<%=pageno-1 %>" rel="prev"><%="<" %></a>
	<%} else { %>
		<li class="disabled"><span><%="<" %></span>
	<%} %>
	</li>
	
	<%for(int i=frompage; i<=topage; i++) { %>
		<%if(i==pageno){ %>	
			<li class="active"><span><%=i %></span></li>
		<%} else { %>	
			<li><a href="?page=<%=i %>"><%=i %></a></li>
		<%}
	} %>
	
	<%if(pageno<totalPage) { %>
		<li><a href="?page=<%=pageno+1 %>" rel="next">></a>
	<%} else { %>
		<li class="disabled"><span>></span>
	<%} %>
	</li>	
	
	<%if(totalPage>10 && totalPage-pageno>9) { %>
		<li><a href="?page=<%=totalPage%>" rel="last">>></a></li>
	<%}%>
	
</ul>
