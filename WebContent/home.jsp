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

<a href="contribution">新規投稿</a>
<a href="management">ユーザー管理</a>
<a href="logout">ログアウト</a>
<c:if test="${not empty loginUser }">
	<div class="profile">
		<div class="name"><h2><c:out value="${loginUser.name}"/></h2>
</div>
	</div>
</c:if>
<div class="contributions">
	<c:forEach items="${contributions}" var="contribution">
		<div class="contribution">
			<div class="subject">件名:<c:out value="${contribution.subject}"/></div>
			<div class="text"><c:out value="${contribution.text}"/></div>
			<div class="category">カテゴリー:<c:out value="${contribution.category}" /></div>
			<div class="user_id">名前:<c:out value="${contribution.user_id}" /></div>
			<div class="insert_date">投稿日時:<fmt:formatDate value="${contribution.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<form action="comment" method="post"><br />
				<label for="text"></label>
				<input name="text" id="text" /><br />
				<input type="hidden" name="contributionId" id="contribution" value="${contribution.contribution_id}" /><br />
				<input type="submit" value="コメントする" /><br />
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${contribution.contribution_id == comment.contribution_id}" >
							<div class="name">名前<c:out value="${comment.user_id}" /></div>
							<div class="text"><c:out value="${comment.text}" /></div>
							<div class="insert_date">投稿日時:<fmt:formatDate value="${comment.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
						</c:if>
					</div>
				</c:forEach>
				<br>
			</form>
		</div>
		<br>
	</c:forEach>
</div>
</div>
</body>
</html>
