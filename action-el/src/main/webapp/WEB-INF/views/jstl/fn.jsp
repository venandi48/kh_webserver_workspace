<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fn</title>
</head>
<body>
	<h1>JSTL fn</h1>
	<ul>
		<li>${fn:toUpperCase(requestScope.str1)}</li>
		<li>${fn:toLowerCase(str1)}</li>
		<li>${fn:length(str1)}</li>
		<li>${fn:indexOf(str1, "world")}</li>
		<li>${fn:indexOf(str1, "wwwwwwwwwworld")}</li>
		<li>${fn:contains(str1, 'world')}
			<c:if test="${fn:contains(str1, 'world')}">world가 있습니다.</c:if>
			<c:if test="${not fn:contains(str1, 'world')}">world가 없습니다.</c:if>
		</li>
		<li>${fn:contains(str1, "wwwwwwwwwworld")}
			<c:if test="${fn:contains(str1, 'wwwwwwwwwworld')}">world가 있습니다.</c:if>
			<c:if test="${not fn:contains(str1, 'wwwwwwwwwworld')}">world가 없습니다.</c:if>
		</li>
		<li>
			${fn:replace(str1, "Hello", "Goodbye")}
			<br />
			${str1}
		</li>
		<li>${fn:substring(str1, 6, 11)}</li>
		<li>${str1}, ${str2}</li>
		<li>${str1.concat(", ").concat(str2)}</li>
		<li>${fn:escapeXml(xss1)}</li>
		<li>${fn:escapeXml(xss2)}</li>
		<li><c:out value="${xss1}" escapeXml="true" /></li> <%-- escapeXml 기본값 true --%>
		<li><c:out value="${xss2}" escapeXml="true" /></li> <%-- escapeXml 기본값 true --%>
	</ul>
	
</body>
</html>