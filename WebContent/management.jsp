<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>

<script type="text/javascript">
	function dialog(){
		if(window.confirm("本当によろしいですか？")){
		//location.href = "./manage" ;
		return true;
		}
	else{
	}
	window.alert("キャンセルしました")
	return false;
	}
	</script>

</head>
<body>
<a href="./">ホーム</a>
<a href="logout">ログアウト</a>
<div class="main-contents">
<a href="signup">ユーザー新規登録</a>
<div class="users">
	<c:forEach items="${users}" var="user">
		<div class="user">
			<div class="user-name">
				<span class="name">名前:<c:out value="${user.name}" /></span>
			</div>
			<div class="login_id">ログインID:<c:out value="${user.login_id}" /></div>

			<div class="branch">
			<c:forEach items="${branches}" var="branch">
				<c:if test="${user.branch_id == branch.id}" >
				支店:<c:out value="${branch.name}" />
				</c:if>
			</c:forEach>
			</div>

			<div class="department">
			<c:forEach items="${departments}" var="department">
				<c:if test="${user.department_id == department.id}" >
				部署:<c:out value="${department.name}" />
				</c:if>
			</c:forEach>
			</div>


			<div class="edit">
			<form action="edit" method="get">
			<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
			<input type="submit" value="編集" />
			</form>
			</div>

			<div class="is_working">
			<form action="isWorking" method="post" onsubmit="return dialog()">
			<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
			<c:if test="${loginUser.id != user.id}">
				<c:if test="${user.is_working == 0 }" >
					<input type="submit" value="停止" />
				</c:if>
				<c:if test="${user.is_working == 1 }" >
					<input type="submit" value="復活" />
				</c:if>
			</c:if>
			</form>
			</div>

			<div class="delete">
			<form action="deleteUser" method="post" onsubmit="return dialog()" >
			<c:if test="${loginUser.id != user.id }">
				<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
				<input type="submit" id="delete" value="削除" />
			</c:if>
			</form>
			</div>
		</div>
	</c:forEach>
</div>
</div>
</body>
</html>