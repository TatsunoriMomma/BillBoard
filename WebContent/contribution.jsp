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
<link href="./css/contribution.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src='<c:url value="./js/main.js"/>'></script>

<script language="JavaScript">
<!-- ダブルクリック防止(通信が遅い場合など未対応) -- >
function double(form) {
	var elements = form.elements;
	for (var i = 0; i < elements.length; i++) {
		if (elements[i].type == 'submit') {
			elements[i].disabled = true;
		}
	}
}
</script>


</head>
<body>

<div class="header shadow">
<h4>わったい菜掲示板</h4>
<div class="links">
<a class="home-tran" href="./">ホーム</a>
<a class="logout-tran" href="logout">ログアウト</a>
</div>
</div>

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

<div class ="contributionForm shadow">
<form action="contribution" method="post" onSubmit="return double(this)"><br />
	<div class="row">

	<div class="six columns">
		<label for="subject">件名(30文字以下)</label>
		<input type="text" class="u-full-width" name="subject" id="subject" maxlength=30 value="${contribution.subject}" /><br />
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
		<label for="newCategory">新規カテゴリー(10文字以下)</label>
		<input type="text" name="newCategory" id="newCategory" maxlength=10 value="${newCategory}"/>
	</div>

	</div>


	<label for="text">本文(1000文字以下)</label>
	<textarea class="u-full-width" rows="20" name="text" id="text" maxlength=1000 onkeyup ="CountDownLength('text',value,1000);">${contribution.text}</textarea>
	<span id="maxtext">あと1000文字</span>
	<input class="button-submit" type="submit" value="登録" /> <br />
</form>
</div>
</div>
</body>
</html>