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
			<div class="user_id">名前:<c:out value="${contribution.name}" /></div>
			<div class="insert_date">投稿日時:<fmt:formatDate value="${contribution.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			<form action="comment" method="post">
				<label for="text"></label>
				<input name="text" id="text" />
				<input type="hidden" name="contributionId" id="contributionId" value="${contribution.id}" />
				<input type="submit" value="コメントする" />
			</form>
			<form action="deleteContribution" method="post">
				<input type="hidden" name="contributionId" id="contributionId" value="${contribution.id}" />
				<c:choose>
					<c:when test="${contribution.user_id == loginUser.id}" ><input type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.department_id == 2}" ><input type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.department_id == 3 && contribution.department_id == 4 && loginUser.branch_id == contribution.branch_id}" >
					<input type="submit" value="投稿削除" />
					</c:when>
				</c:choose>
			</form>

			<c:forEach items="${comments}" var="comment">
				<div class="comment">
					<c:if test="${contribution.id == comment.contribution_id}" >
						<div class="name">名前:<c:out value="${comment.name}" /></div>
						<div class="text"><c:out value="${comment.text}" /></div>
						<div class="insert_date">投稿日時:<fmt:formatDate value="${comment.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
						<form action="commentDelete" method="post">
							<input type="hidden" name="commentId" id="commentId" value="${comment.id}" />
								<c:choose>
									<c:when test="${comment.user_id == loginUser.id}" ><input type="submit" value="コメント削除" /></c:when>
									<c:when test="${loginUser.department_id == 2}" ><input type="submit" value="コメント削除" /></c:when>
									<c:when test="${loginUser.department_id == 3 && comment.department_id == 4 && loginUser.branch_id == comment.branch_id}" >
									<input type="submit" value="コメント削除" />
									</c:when>
								</c:choose>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<br>
	</c:forEach>
</div>
</div>
</body>
</html>