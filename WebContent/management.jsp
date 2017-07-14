<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css" rel="stylesheet" type="text/css">
<link href="./css/main.css" rel="stylesheet" type="text/css">
<link href="./css/management.css" rel="stylesheet" type="text/css">

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

<div class="main-contents">

<a href="./">ホーム</a>
<a href="signup">ユーザー新規登録</a>
<a href="logout">ログアウト</a>


<table class="u-full-width shadow">
	<thead>
    <tr>
    	<th>名前</th>
    	<th>ログインID</th>
    	<th>支店</th>
    	<th>部署</th>
    	<th>編集</th>
    	<th>復活・停止</th>
    	<th>削除</th>
    </tr>
 	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
		<tr>
			<td>
				<div class="user-name">
					<c:out value="${user.name}" />
				</div>
			</td>

			<td>
				<div class="login_id"><c:out value="${user.login_id}" /></div>
			</td>

			<td>
				<div class="branch">
				<c:forEach items="${branches}" var="branch">
					<c:if test="${user.branch_id == branch.id}" >
					<c:out value="${branch.name}" />
					</c:if>
				</c:forEach>
				</div>
			</td>

			<td>
				<div class="department">
				<c:forEach items="${departments}" var="department">
					<c:if test="${user.department_id == department.id}" >
					<c:out value="${department.name}" />
					</c:if>
				</c:forEach>
				</div>
			</td>

			<td>
				<div class="edit">
				<form action="edit" method="get">
				<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
				<input class="button-submit" type="submit" value="編集" />
				</form>
				</div>
			</td>

			<td>
				<div class="is_working">
				<form action="isWorking" method="post" onsubmit="return dialog()">
				<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
				<c:if test="${loginUser.id != user.id}">
					<c:if test="${user.is_working == 0 }" >
						<input class="button-stop" type="submit" value="停止" />
					</c:if>
					<c:if test="${user.is_working == 1 }" >
						<input class="button-revive" type="submit" value="復活" />
					</c:if>
				</c:if>
				</form>
				</div>
			</td>

			<td>
				<div class="delete">
				<form action="deleteUser" method="post" onsubmit="return dialog()" >
				<c:if test="${loginUser.id != user.id }">
					<input type="hidden" name="editUserId" id="editUserId" value="${user.id}" />
					<input class="button-delete" type="submit" id="delete" value="削除" />
				</c:if>
				</form>
				</div>
			</td>
		</tr>
		</c:forEach>

	</tbody>
</table>
</div>
</body>
</html>