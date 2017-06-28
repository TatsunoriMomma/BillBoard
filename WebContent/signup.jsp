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
<div class="main-contents">
<form action="signup" method="post"><br />
	<label for="loginId">ログインID</label>
	<input name="loginId" id="loginId" value="${user.login_id}"/>半角英数6文字以上20字以下<br />

	<label for="name">名前</label>
	<input name="name" id="name"/>10文字以下<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" id="password"/> <br />

	<label for="checkPassword">パスワード(確認用)</label>
	<input name="checkPassword" id="checkPassword"/> <br />

	<label for="branch">支店</label>
	<select name="branch" id="branch">
	<option value=1>1</option>
	</select> <br />

	<label for="department">部署・役職</label>
	<select name="department" id="department">
	<option value=1>1</option>
	</select> <br />

	<input type="submit" value="登録" /> <br />
	<a href="./">戻る</a>
</form>
</div>
</body>
</html>
