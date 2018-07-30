<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="_title" fragment="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
	String date = sdf.format(new java.util.Date());
	request.setAttribute("date", date);
%>
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