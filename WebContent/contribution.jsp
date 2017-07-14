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

<div class ="contributionForm shadow">
<form action="contribution" method="post"><br />
	<div class="row">

	<div class="six columns">
		<label for="subject">件名(30文字以下)</label>
		<input type="text" class="u-full-width" name="subject" id="subject" value="${contribution.subject}" /><br />
	</div>

	<div class="three columns">
		<label for="selectCategory">カテゴリー</label>
		<select name="selectCategory" id="selectCategory">
		<option value=""><c:out value="選択してください" />
		<c:forEach items="${categories}" var="category">
		<option value="${category}"
		<c:if test="${selectCategory == category}">selected</c:if>
		>
		<c:out value="${category}" />
		</option>
		</c:forEach>
		</select>
	</div>

	<div class="three columns">
		<label for="newCategory">新規カテゴリー</label>
		<input type="text" name="newCategory" id="newCategory" value="${newCategory}"/>
	</div>

	</div>


	<label for="text">本文(1000文字以下)</label>
	<textarea class="u-full-width" name="text" id="text"  >${contribution.text}</textarea>

	<input class="button-submit" type="submit" value="登録" /> <br />
</form>
</div>
</div>
</body>
</html>