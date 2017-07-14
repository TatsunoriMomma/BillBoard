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

	<link href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css" rel="stylesheet" type="text/css">
	<script src="https://use.fontawesome.com/1e2813cc8e.js"></script>
	<link href="./css/main.css" rel="stylesheet" type="text/css">
	<link href="./css/home.css" rel="stylesheet" type="text/css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css" >
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
	<script type="text/javascript" src='<c:url value="./js/main.js"/>'></script>

	<script>
	$(function() {

	    $('#firstCalendar').datepicker({
			maxDate : "0y"
	    });

		$('#firstCalendar').change(function() {
			$('#lastCalendar').datepicker("option","minDate",$('#firstCalendar').val())
		})

	    $('#lastCalendar').datepicker({
			maxDate : "0y"
	    });
	});
	</script>



</head>
<body>

<div class="main-contents">

<a href="contribution">新規投稿</a>
<a href="management">ユーザー管理</a>
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


<div class="profile">
	<div class="loginUserName"><h5>こんにちは！<c:out value="${loginUser.name}"/>さん！</h5></div>
</div>

<div class="side-menu shadow">
	<div class = "form1">
		<form action="narrow" method="get">
		<div class="narrowCategory">
			<label for="narrowCategory">カテゴリー</label>
			<select name="narrowCategory" id="narrowCategory">
			<option value=""><c:out value="選択してください" />
			<c:forEach items="${categories}" var="category">
				<option value="${category}"><c:out value="${category}" /></option>
			</c:forEach>
			</select>
		</div>
		<div class="narrowDate">
			<div class="start">
				<label for="narrowFirstDate">開始日</label>
				<input name="narrowFirstDate" id="firstCalendar" type="text" readonly>
			</div>
			<div class="end">
				<label for="narrowLastDate">終了日</label>
				<input name="narrowLastDate" id="lastCalendar" type="text" readonly>
			</div>
		</div>
		<div class="sort">
			<input class="button-submit" type="submit" value="絞り込む" />
		</div>
		</form>
	</div>
	<div class="form2">
	<form action="./" method="get">
		<input type="submit" value="リセット" />
	</form>
	</div>
</div>

<div class="contributions shadow">
	<c:forEach items="${contributions}" var="contribution">
		<div class="contribution shadow">
			<div class="contributionInfo">
				<div class="category">カテゴリー:<c:out value="${contribution.category}" /></div>
				<h5 class="subject">件名:<c:out value="${contribution.subject}"/></h5>
				<div class="name"><i class="fa fa-user" ></i><c:out value="${contribution.name}" /></div>
				<div class="insert_date"><i class="fa fa-clock-o" ></i><fmt:formatDate value="${contribution.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
			</div>
			<div class="text"><c:out value="${contribution.text}"/></div>

			<form action="deleteContribution" method="post">
				<input type="hidden" name="contributionId" id="contributionId" value="${contribution.id}" />
				<c:choose>
					<c:when test="${contribution.user_id == loginUser.id}" ><input class=" button-delete" type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.department_id == 2}" ><input class=" button-delete" type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.department_id == 3 && contribution.department_id == 4 && loginUser.branch_id == contribution.branch_id}" >
					<input class=" button-delete" type="submit" value="投稿削除" />
					</c:when>
				</c:choose>
			</form>

			<form action="comment" method="post">
				<label for="text"></label>
				<textarea  class="u-full-width" name="text" id="text" ></textarea>
				<input type="hidden" name="contributionId" id="contributionId" value="${contribution.id}" />
				<input class="button-submit" type="submit" value="コメントする" />
			</form>

			<div class = "commentView">
				<button>コメント非表示 </button>
			</div>

			<div class = "commentList shadow">
			<c:forEach items="${comments}" var="comment">
				<div class="comment">
					<c:if test="${contribution.id == comment.contribution_id}" >
						<div class="name"><i class="fa fa-user" ></i><c:out value="${comment.name}" /></div>
						<div class="insert_date"><i class="fa fa-clock-o" ></i><fmt:formatDate value="${comment.insert_date}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
						<div class="text"><c:out value="${comment.text}" /></div>
						<form action="commentDelete" method="post">
							<input type="hidden" name="commentId" id="commentId" value="${comment.id}" />
								<c:choose>
									<c:when test="${comment.user_id == loginUser.id}" ><input class="button-delete" type="submit" value="コメント削除" /></c:when>
									<c:when test="${loginUser.department_id == 2}" ><input class="button-delete" type="submit" value="コメント削除" /></c:when>
									<c:when test="${loginUser.department_id == 3 && comment.department_id == 4 && loginUser.branch_id == comment.branch_id}" >
									<input class="button-delete" type="submit" value="コメント削除" />
									</c:when>
								</c:choose>
						</form>
					</c:if>
				</div>
			</c:forEach>
			</div>
		</div>
		<br>
	</c:forEach>
</div>
</div>
</body>
</html>