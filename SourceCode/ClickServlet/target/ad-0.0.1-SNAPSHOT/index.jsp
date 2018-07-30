<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8" %>
<head>
	<title>Click Servlet Example</title>
</head>
<t:template>
	<div>Link quản lý:</div>
	<div>
   		<ul>
   			<li>
   				<a href="${pageContext.request.contextPath }/adManagement">Quản lý Ad</a>
   			</li>
   			<li>
   				<a href="${pageContext.request.contextPath }/mediaManagement">Quản lý Media</a>
   			</li>
   			<li>
   				<a href="${pageContext.request.contextPath }/auditLog">Lịch sử log</a>
   			</li>
   		</ul>
   	</div>
	
    <div>Click vào một link phía dưới để xem kết quả:</div>
    <div>
    	<div>Link hợp lệ:</div>
    	<div>
    		<ul>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=1">${pageContext.request.contextPath }/p/click?_media=1&_ad=1</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=2">${pageContext.request.contextPath }/p/click?_media=1&_ad=2</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=3">${pageContext.request.contextPath }/p/click?_media=1&_ad=3</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=4">${pageContext.request.contextPath }/p/click?_media=1&_ad=4</a>
    			</li>
    		</ul>
    	</div>
    	<div>Link không hợp lệ:</div>
    	<div>
    		<ul>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=10">${pageContext.request.contextPath }/p/click?_media=1&_ad=10</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=20">${pageContext.request.contextPath }/p/click?_media=1&_ad=20</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=30">${pageContext.request.contextPath }/p/click?_media=1&_ad=30</a>
    			</li>
    			<li>
    				<a href="${pageContext.request.contextPath }/p/click?_media=1&_ad=40">${pageContext.request.contextPath }/p/click?_media=1&_ad=40</a>
    			</li>
    		</ul>
    	</div>
    </div>
</t:template>