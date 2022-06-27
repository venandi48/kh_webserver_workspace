<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Action Tag & EL</title>
</head>
<body>
	<h1>JSP Action Tag & EL</h1>
	
	<h2>JSP Action Tag</h2>
	<h3>표준액션</h3>
	<ul>
		<li><a href="${pageContext.request.contextPath}/standard/useBean.do">jsp:useBean</a></li>
		<li><a href="${pageContext.request.contextPath}/standard/main.do">jsp:include</a></li>
	</ul>
	<h3>커스텀액션 JSTL</h3>
	
	<h2>EL</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/el/basic.do">EL Basics</a></li>
		<li><a href="${pageContext.request.contextPath}/el/object.do?pname=아이패드&pcount=5&option=red&option=128gb">EL Objects</a></li>
		<li><a href="${pageContext.request.contextPath}/el/operator.do">EL 연산자</a></li>
	</ul>
</body>
</html>