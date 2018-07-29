<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	String date = sdf.format(new Date());
	request.setAttribute("date", date);
%>
<t:genericpage>
    <jsp:attribute name="header">
      	<h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      	<p id="copyright">Copyright ClickServlet ${date }</p>
    </jsp:attribute>
    <jsp:body>
        <p>Hi I'm the heart of the message</p>
    </jsp:body>
</t:genericpage>