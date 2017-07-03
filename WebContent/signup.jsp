<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー新規登録</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="./">ホーム</a>
<a href="logout">ログアウト</a>
<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"><br />
	<label for="loginId">ログインID</label>
	<input name="loginId" id="loginId" value="${user.login_id}"/>半角英数6文字以上20字以下<br />

	<label for="name">名前</label>
	<input name="name" id="name" value="${user.name}"/>10文字以下<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/>6文字以上20字以下<br />

	<label for="checkPassword">パスワード(確認用)</label>
	<input name="checkPassword" id="checkPassword"/> <br />

	<label for="branchId">支店</label>
	<select name="branchId" id="branchId">

	<c:forEach items="${branches}" var="branch">
		<option value="${branch.branch_id}">
		<c:out value="${branch.name}" />
		</option>
	</c:forEach>
	</select> <br />

	<label for="departmentId">部署</label>
	<select name="departmentId" id="departmentId">
	<c:forEach items="${departments}" var="department">
		<option value="${department.department_id}">
		<c:out value="${department.name}" />
		</option>
	</c:forEach>
	</select> <br />

	<input type="submit" value="登録" /> <br />
</form>
</div>
</body>
</html>
