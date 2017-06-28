<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
</head>
<body>
<div class="main-contents">
<form action="contribution" method="post"><br />
	<label for="subject">件名</label>
	<input name="subject" id="subject" /><br />

	<label for="text">本文</label>
	<input name="text" id="text"/><br />

	<label for="category">カテゴリー</label>
	<select name="category" id="category">
	<option value=1>カテゴリー1</option>
	<option value=2>カテゴリー2</option>
	</select> <br />

	<input type="submit" value="登録" /> <br />
</form>
</div>
</body>
</html>