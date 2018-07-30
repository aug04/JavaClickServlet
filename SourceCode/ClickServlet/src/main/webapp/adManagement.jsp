<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page pageEncoding="UTF-8" %>
<%@page import="com.example.click.util.Constants" %>
<head>
	<title>Quản lý Ad</title>
</head>
<t:template>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Status</th>
				<th>URL</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adsList }" var="ad">
			<tr>
				<td>${ad.name }</td>
				<td>
					<c:if test="${ad.status eq Constants.Status.ON }">ON</c:if>
					<c:if test="${ad.status eq Constants.Status.OFF }">OFF</c:if>
					<c:if test="${ad.status eq Constants.Status.DELETED }">DELETED</c:if>
					${Constants.Status.ON }
				</td>
				<td>${ad.url }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</t:template>