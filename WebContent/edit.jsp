<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー編集</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css" rel="stylesheet" type="text/css">
<link href="./css/main.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="main-contents">
<a href="./">ホーム</a>
<a href="logout">ログアウト</a>
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

<form action="edit" method="post" ><br />
	<label for="editName">${editUser.name}さんの編集</label><br />
	<input type="hidden" name="id" id="id" value="${editUser.id}" /><br />

	<label for="loginId">ログインID</label>
	<input name="loginId" value="${editUser.login_id}" />半角英数6文字以上20字以下<br />

	<label for="name">名前</label>
	<input name="name" value="${editUser.name}" id="name"/>10文字以下<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password" />半角英数記号6文字以上20字以下<br />

	<label for="checkPassword">パスワード（確認用）</label>
	<input name="checkPassword" type="password" id="checkPassword"/> <br />

	<label for="branchId">支店</label>
	<select name="branchId" id="branchId">
	<c:forEach items="${branches}" var="branch">
		<option value="${branch.id}"
		<c:if test="${editUser.branch_id == branch.id}" >selected</c:if>
		>
		<c:out value="${branch.name}" />
		</option>
	</c:forEach>
	</select> <br />

	<label for="departmentId">部署</label>
	<select name="departmentId" id="departmentId">
	<c:forEach items="${departments}" var="department">
		<option value="${department.id}"
		<c:if test="${editUser.department_id == department.id}" >selected</c:if>
		>
		<c:out value="${department.name}" />
		</option>
	</c:forEach>
	</select> <br />

	<input type="submit" value="登録" /> <br />
</form>
</div>
</body>
</html>