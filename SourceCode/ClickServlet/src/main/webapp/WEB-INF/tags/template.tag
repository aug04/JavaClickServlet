<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="_title" fragment="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
	String date = sdf.format(new java.util.Date());
	request.setAttribute("date", date);
%>
<style>
* {
	font-family: "Arial", "Tahoma", "Helvetica";
	color: #333;
}

a {
	color: #0f6bdb !important;
    text-decoration: none;
}

table {
	border-collapse: collapse;
	border-color: #555;
}

table tr,
table th,
table td {
	border-color: #555;
	padding: 10px;
}

#copyright {
	font-size: 12px;
}
</style>
<t:genericpage>
    <jsp:attribute name="header">
      	<h1>Click Servlet Example</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      	<p id="copyright">Copyright ClickServlet ${date }</p>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>