
<%String layout = (String) request.getAttribute("layout");%>
<% 	if (layout.equals("home")) {%>
<%@ include file="/View/Client/menu_dish.jsp"%>

<%} else if (layout.equals("information")) {%>
<%@ include file="/View/Admin/Information.jsp"%>

<%}%>