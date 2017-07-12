<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿</title>
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

<form action="contribution" method="post"><br />
	<div class="row">

	<div class="six columns">
		<label for="subject">件名</label>
		<input type="text" class="u-full-width" name="subject" id="subject" value="${contribution.subject}" />30文字以下<br />
	</div>

	<div class="four columns">
		<label for="category">カテゴリー</label>
		<select name="category" id="category">
		<c:forEach items="${categories}" var="category">
		<option value="${category}"
		<c:if test="${contribution.category == category}">selected</c:if>
		>
		<c:out value="${category}" />
		</option>
		</c:forEach>
		</select>
	</div>

	</div>


	<label for="text">本文</label>
	<textarea class="u-full-width" name="text" id="text"  >${contribution.text}</textarea>

	<input class="button-primary" type="submit" value="登録" /> <br />
</form>
</div>
</body>
</html>