<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 반복처리</title>
<style>
table {
	border: 1px solid #000;
	border-collapse: collapse;
	margin: 10px 0;
}
th, td {
	border: 1px solid #000;
	padding: 3px;
}
</style>
</head>
<body>
	<h1>JSTL 반복처리</h1>
	<h2>names</h2>
	<ul>
		<c:forEach items="${names}" var="name">
			<li>${name}</li>
		</c:forEach>
	</ul>
	
	<h2>persons</h2>
	<table>
		<thead>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>결혼여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${persons}" var="person" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${person.name}</td>
					<td>${person.gender}</td>
					<td>${person.age}</td>
					<td>
						<input type="checkbox" onclick="return false;" ${person.married ? "checked" : ""} />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>nums</h2>
	<c:forEach items="${nums}" var="num" varStatus="vs">
		${num}
		<c:if test="${not vs.last}"> | </c:if>
		<%-- ${not vs.last ? " | " : "" } --%>
	</c:forEach>
	
	<h2>map</h2>
	<c:forEach items="${map}" var="entry">
		<li>${entry}
			<ul>
				<li>${entry.key}</li>
				<li>${entry.value}</li>
			</ul>
		</li>
	</c:forEach>
	
	<h2>csv</h2>
	<c:forTokens items="${csv}" delims="," var="ch">
		<mark>${ch}</mark>
	</c:forTokens>
 	
</body>
</html>