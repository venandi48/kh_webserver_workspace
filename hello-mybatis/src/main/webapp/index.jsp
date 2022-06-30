<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8"/>
	<title>Mybatis</title>
</head>
<body>
	<h1>Mybatis</h1>
	<h2>student</h2>
	<ul>
		<li><a href="<c:url value="/student/studentEnroll.do"/>">학생등록</a></li>
		<li><a href="<c:url value="/student/student.do"/>">학생조회</a></li>
	</ul>
</body>
</html>
