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
	<input name="password" type="password" id="password"/> <br />

	<label for="check_password">パスワード(確認用)</label>
	<input name="check_password" id="check_password"/> <br />

	<label for="branch_id">支店</label>
	<select name="branch_id" id="branch_id">
	<option value=1>本社</option>
	<option value=2>支店A</option>
	<option value=3>支店B</option>
	<option value=4>支店C</option>
	</select> <br />

	<label for="department_id">部署・役職</label>
	<select name="department_id" id="department_id">
	<option value=1>総務人事</option>
	<option value=2>情報管理</option>
	<option value=3>社員</option>
	<option value=4>支店長</option>
	</select> <br />

	<input type="submit" value="登録" /> <br />
</form>
</div>
</body>
</html>
