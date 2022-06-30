<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<style>
div#enroll-container {text-align:center;}
table {margin:0 auto;border:1px solid; border-collapse:collapse;}
th,td {border:1px solid; padding:5px;}
th {text-align:right;}
td {text-align:left;}
td:last-of-type {text-align:center;}
</style>
<script>
<c:if test="${not empty sessionScope.msg}">
	alert("${msg}");
	<c:remove var="msg" scope="session" />
</c:if>
</script>
</head>
<body>
	<div class="enroll-container">
		<h2>학생등록(VO)</h2>
		<form method="POST">
			<table>
				<tr>
					<th>학생이름</th>
					<td>
						<input type="text" name="name" required/>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="tel" name="tel" maxlength="11" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<hr />
	
	<h2>학생등록(VO)</h2>
	<form 
		action="${pageContext.request.contextPath}/student/studentMapEnroll.do" 
		method="POST">
		<table>
			<tr>
				<th>학생이름</th>
				<td>
					<input type="text" name="name" required/>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<input type="tel" name="tel" maxlength="11" required/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
