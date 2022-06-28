<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Basic</title>
</head>
<body>
	<h1>JSTL Basic 결과</h1>
	<%-- JSP Standard Tag Library --%>
	<%
		/*
		pageContext.setAttribute("no1", request.getParameter("num1"));
		pageContext.setAttribute("no2", request.getParameter("num2"));
		pageContext.setAttribute("sum", 
				Integer.parseInt((String)pageContext.getAttribute("no1")) + Integer.parseInt((String)pageContext.getAttribute("no2")));
		*/
	%>
	
	<%-- 변수 선언/출력 --%>
	<%-- 
	
	<c:set var="no1" scope="page">${param.num1}</c:set>
	<c:set var="no2" value="${param.num2}"></c:set>
	<c:set var="sum" value="${no1 + no2}" />
	<h2>두 수의 합</h2>
	<p>
		<c:out value="${no1}" />과 <c:out value="${no2}" />의 합은 <c:out value="${sum}" />입니다. 
	</p>
	<p>
		${no1}과 ${no2}의 합은 ${sum}입니다.
	</p>
	
	
	<h2>크기 비교</h2>
	<c:if test="${Integer.parseInt(no1) > Integer.parseInt(no2)}">
		<p>${no1}이 ${no2}보다 큽니다.</p>
	</c:if>
	<c:if test="${Integer.parseInt(no2) > Integer.parseInt(no1)}">
		<p>${no2}이 ${no1}보다 큽니다.</p>
	</c:if>
	<c:if test="${Integer.parseInt(no1) eq Integer.parseInt(no2)}">
		<p>${no1}과 ${no2}는 같습니다.</p>
	</c:if>
	
	--%>
	
	<h2>경품뽑기</h2>
	${param.rnum}
	<c:choose>
		<c:when test="${param.rnum % 5 == 0}">
			<p>축하드립니다. 맥북을 드립니다~</p>
		</c:when>
		<c:when test="${param.rnum % 5 == 1}">
			<p>축하드립니다. 스타벅스 커피상품권을 드립니다~</p>
		</c:when>
		<c:otherwise>
			<p>꽝입니다! 다음에 다시 도전해주세요~</p>
		</c:otherwise>
	</c:choose>
	
	<h2>반복처리</h2>
	<div class="wrapper" style="border:1px solid #000; padding: 5px;">
		<c:forEach var="i" begin="1" end="6">
			<h${i}>Hello World</h${i}>
		</c:forEach>
		<hr />
		<c:forEach var="i" begin="1" end="100" step="2">
			<p>${101 - i}</p>
		</c:forEach>
	</div>

	<h2>url</h2>
	<div>
		<img src="${pageContext.request.contextPath}/images/서강준 (1).jpg" alt="" style="width:200px;"/>
		<img src='<c:url value="/images/서강준 (1).jpg"/>' alt="" style="width:200px;"/>
	</div>
	
	<h2>redirect</h2>
	<c:if test="${empty param.login || param.login == false}">
		<c:redirect url="/"/>
	</c:if>
	
</body>
</html>