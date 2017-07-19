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

<div class="editForm shadow">
<form action="edit" method="post" ><br />
	<!-- <label for="editName">${editUser.name}さんの編集</label><br /> -->
	<input type="hidden" name="id" id="id" value="${editUser.id}" /><br />

	<h6 class="note"><label for="loginId">ログインID</label></h6>
	<div class="subnote"><label for="loginId">(半角英数6文字以上20字以下)</label></div>
	<br />
	<input type="text" name="loginId" value="${editUser.login_id}" />
	<br />

	<h6 class="note"><label for="name">名前</label></h6>
	<div class="subnote"><label for="name">(10文字以下)</label></div>
	<br />
	<input type ="text" name="name" value="${editUser.name}" id="name"/>
	<br />

	<h6 class="note"><label for="password">パスワード</label></h6>
	<div class="subnote"><label for="password">(半角英数記号6文字以上20字以下)</label></div>
	<br />
	<input name="password" type="password" id="password" />
	<br />

	<h6 class="note"><label for="checkPassword">パスワード</label></h6>
	<div class="subnote"><label for="checkPassword">(確認用)</label></div>
	<br />
	<input name="checkPassword" type="password" id="checkPassword"/>
	<br />


	<h6 class="note"><label for="branchId">支店</label></h6><br />
	<select name="branchId" id="branchId">
	 <c:choose>
	 	<c:when test="${loginUser.id == editUser.id}">
			<option value="${loginUser.branch_id}">
				<c:forEach items="${branches}" var="branch">
					<c:if test="${loginUser.branch_id == branch.id}" >
					<c:out value="${branch.name}" />
					</c:if>
				</c:forEach>
			</option>
		</c:when>
		<c:otherwise>
			<c:forEach items="${branches}" var="branch">
				<option value="${branch.id}"
				<c:if test="${editUser.branch_id == branch.id}" >selected</c:if>
				>
				<c:out value="${branch.name}" />
				</option>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</select> <br />

	<h6 class="note"><label for="departmentId">部署</label></h6><br />
	<select name="departmentId" id="departmentId">
		<c:choose>
			<c:when test="${editUser.id == loginUser.id}">
				<option value="${loginUser.department_id}">
					<c:forEach items="${departments}" var="department">
						<c:if test="${loginUser.department_id == department.id}" >
						<c:out value="${department.name}" />
						</c:if>
					</c:forEach>
				</option>
			</c:when>
			<c:otherwise>
				<c:forEach items="${departments}" var="department">
					<option value="${department.id}"
					<c:if test="${editUser.department_id == department.id}" >selected</c:if>
					>
					<c:out value="${department.name}" />
					</option>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</select> <br />

	<input class="button-submit" type="submit" value="登録" /> <br />
</form>
</div>
</div>
</body>
</html>