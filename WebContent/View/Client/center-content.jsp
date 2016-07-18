<%String layout = (String) request.getAttribute("layout");%>


<% 	if (layout.equals("menu_dish")) {%>
<%@ include file="/View/Client/menu_dish.jsp"%>

<%} else if (layout.equals("home")) {%>
<%@ include file="/View/Client/home.jsp"%>

<%} else if (layout.equals("dish_detail")) {%>
<%@ include file="/View/Client/detail_dish.jsp"%>

<%} else if (layout.equals("introduce")) {%>
<%@ include file="/View/Client/introduce.jsp"%>

<%} else if (layout.equals("recruitment")) {%>
<%@ include file="/View/Client/recruitment.jsp"%>

<%} else if (layout.equals("connected")) {%>
<%@ include file="/View/Client/connected.jsp"%>

<%} else if (layout.equals("map")) {%>
<%@ include file="/View/Client/map.jsp"%>

<%} else if (layout.equals("service")) {%>
<%@ include file="/View/Client/service.jsp"%>

<%} else if (layout.equals("service_detail")) {%>
<%@ include file="/View/Client/detail_service.jsp"%>

<%} else if (layout.equals("news")) {%>
<%@ include file="/View/Client/news.jsp"%>

<%} else if (layout.equals("error")) {%>
<%@ include file="/View/Client/error.jsp"%> 

<%}%>


