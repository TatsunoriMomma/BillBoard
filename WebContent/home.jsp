<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main-contents">


<a href="newContribution">新規投稿</a>
<a href="userManagement.jsp">ユーザー管理</a>
<a href="logout">ログアウト</a>
<c:if test="${not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}"/></h2>
</div>
	</div>
</c:if>
<div class="messages">
	<c:forEach items="${messages}" var="message">
		<div class="message-icon">
			<div class="message">
				<div class="account-name">
					<span class="account"><c:out value="${message.account}"/></span>
					<span class="name"><c:out value="${message.name}"/></span>
					</div>
					<div class="text"><c:out value="${message.text}" /></div>
					<div class="date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			</div>
		</div>
	</c:forEach>
</div>
</div>
</body>
</html>
