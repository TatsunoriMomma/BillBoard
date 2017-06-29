<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>
</head>
<body>
<a href="signup">ユーザー新規登録</a>
<a href="edit">ユーザー編集</a>
<div class="users">
	<c:forEach items="${users}" var="user">
		<div class="user">
			<div class="user-name">
				<span class="name"><c:out value="${user.name}" /></span>
			</div>
			<div class="login_id"><c:out value="${user.login_id}" /></div>
			<div class="branch"><c:out value="${user.branch_id}" /></div>
			<div class="department"><c:out value="${user.department_id}" /></div>
			<div class="is_working"><c:out value="${user.is_working}" /></div>
			<div class="edit"><button type="submit" >編集</button></div>
			<div class="edit"><button type="submit" >停止</button></div>
			<div class="edit"><button type="submit" >削除</button></div>
		</div>
	</c:forEach>
</div>
</body>
</html>