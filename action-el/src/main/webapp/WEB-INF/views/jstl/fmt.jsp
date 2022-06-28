<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fmt</title>
</head>
<body>
	<h1>JSTL fmt</h1>

	<h2>숫자</h2>
	<c:set var="num1">123.4567</c:set>
	<c:set var="num2">1.2</c:set>
	<c:set var="won">7320315000</c:set>	
	<c:set var="pct">0.015</c:set>	
	<ul>
		<li>
			<fmt:formatNumber value="${num1}" pattern="#.##" /> <%-- 기본적으로 반올림 처리 --%>
		</li>
		<li>
			<fmt:formatNumber value="${num2}" pattern="#.##" /> <%-- # 해당자리 숫자가 없을 때 공란처리 --%>
			<fmt:formatNumber value="${num2}" pattern="0.00" /> <%-- 0 해당자리 숫자가 없을 때 0처리 --%>
		</li>
		<li>
			<fmt:formatNumber value="${won}" pattern="#,###" />
		</li>
		<li>
			<fmt:setLocale value="ko_kr"/>
			<fmt:formatNumber value="${won}" type="currency" />
		</li>
		<li>
			<%-- maxFractionDigits 소수점 이하 자리수 표기 --%>
			<fmt:formatNumber value="${pct}" type="percent" maxFractionDigits="2" />
		</li>
	</ul>
	
	<h2>날짜</h2>
	<%--<c:set var="now" value="<%= new Date() %>"></c:set> --%>
	<fmt:parseDate var="now" value="2022-01-19 09:30:33" pattern="yyyy-MM-dd HH:mm:ss" />
	<ul>
		<li>
			<fmt:formatDate value="${now}" type="date"/>
		</li>
		<li>
			<fmt:formatDate value="${now}" type="time"/>
		</li>
		<li>
			<fmt:formatDate value="${now}" type="both"/>
		</li>
		<li>
			<fmt:formatDate value="${now}" pattern="yyyy/MM/dd(E) HH:mm:ss"/>
		</li>
		<li>
			<fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 (E요일)"/>
		</li>
	</ul>
	
</body>
</html>