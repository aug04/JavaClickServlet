<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<head>
	<title>Lịch sử log</title>
</head>
<t:template>
	<table border="1">
		<thead>
			<tr>
				<th>date</th>
				<th>ad_id</th>
				<th>media_id</th>
				<th>user_agent</th>
				<th>query_string</th>
				<th>error_type</th>
				<th>error_msg</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${deliverLogList }" var="dl">
			<tr>
				<td>${dl.date }</td>
				<td>${dl.adId }</td>
				<td>${dl.mediaId }</td>
				<td>${dl.userAgent }</td>
				<td>${dl.queryString }</td>
				<td>${dl.errorType }</td>
				<td>${dl.errorMsg }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="padding-top: 20px;">
		<a href="${pageContext.request.contextPath}">Quay lại</a> | 
		<a href="#" onclick="window.location.reload(true)">Tải lại trang</a>
	</div>
</t:template>