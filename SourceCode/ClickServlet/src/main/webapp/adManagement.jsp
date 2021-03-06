<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" %>
<head>
	<title>Quản lý Ad</title>
</head>
<t:template>
	<div style="padding-bottom: 20px;">
		<a href="#" onclick="create()">Tạo mới</a>
		<div id="form-action">
			<form action="${pageContext.request.contextPath}/adManagement" method="post">
				<table>
					<tr>
						<td>Tên</td>
						<td><input type="text" name="name" id="txt-name"></td>
					</tr>
					<tr>
						<td>Liên kết (URL)</td>
						<td><input type="text" name="url" id="txt-url"></td>
					</tr>
					<tr>
						<td>Trạng thái</td>
						<td>
							<select name="status" id="sl-status">
								<option value="1">ON</option>
								<option value="2">OFF</option>
								<option value="-1">DELETED</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="button" value="Đóng" id="btn-close"></td>
						<td>
							<input type="hidden" name="id" value="" id="txt-id">
							<input type="hidden" name="actionType" value="default" id="action-type">
							<input type="submit" value="Ghi lại" id="btn-submit">
							<input type="reset" value="Làm mới" id="btn-reset">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<strong>${message }</strong>
		</div>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>Tên</th>
				<th>Liên kết (URL)</th>
				<th>Trạng thái</th>
				<th>Chức năng</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adsList }" var="ad">
			<tr>
				<td>${ad.name }</td>
				<td>${ad.url }</td>
				<td>
					<c:if test="${ad.status eq 1 }">ON</c:if>
					<c:if test="${ad.status eq 2 }">OFF</c:if>
					<c:if test="${ad.status eq -1 }">DELETED</c:if>
				</td>
				<td>
					<a href="#" onclick="prepareEdit(${ad.id}, '${ad.name }', '${ad.url }', ${ad.status })">Sửa</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="padding-top: 20px;">
		<a href="${pageContext.request.contextPath}">Quay lại</a> | 
		<a href="javascript:window.location.reload(true)">Tải lại trang</a>
	</div>
</t:template>
<script type="text/javascript">
	var formAction = document.getElementById("form-action");
	var btnReset = document.getElementById("btn-reset");
	var btnSubmit = document.getElementById("btn-submit");
	var btnClose = document.getElementById("btn-close");
	var adName = document.getElementById("txt-name"),
		url = document.getElementById("txt-url"),
		status = document.getElementById("sl-status"),
		id = document.getElementById("txt-id"),
		actionType = document.getElementById("action-type");
	
	formAction.style.display = "none";

	function create() {
		btnReset.click();
		actionType.value = "create";
		formAction.style.display = "block";
	}

	function prepareEdit(idVal, nameVal, urlVal, statusVal) {
		id.value = idVal;
		adName.value = nameVal;
		url.value = urlVal;
		status.value = statusVal;
		status.selected = "selected";

		actionType.value = "edit";
		formAction.style.display = "block";
	}

	// submit
	btnSubmit.onclick = function() {
		if (!adName || !url) {
			alert("Vui lòng điền đầy đủ thông tin trước khi ghi lại!");
			return false;
		}

		if (!isURL(url)) {
			alert("Vui lòng điền URL đúng định dạng trước khi ghi lại!");
			return false;
		}

		return true;
	};

	btnClose.onclick = function() {
		formAction.style.display = "none";
	};

	function isURL(str) {
		var url = document.getElementById("url").value;
        var pattern = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
        return pattern.test(url);
	}
</script>