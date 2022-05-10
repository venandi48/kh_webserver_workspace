<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%
	// scriptlet - java영역
	// request, response 등의 선언없이 사용가능한 내장객체 존재
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	String animal = request.getParameter("animal");
	String[] foods = request.getParameterValues("food");
	
	// servlet에서 request속성으로 전달한 값 가져오기
	String src = (String) request.getAttribute("src"); // 기본타입은 Object
	
	System.out.println("------------------------- @jsp -------------------------");
	System.out.println(name + ", " + animal + ", " + color + ", "
			+ (foods !=null ? Arrays.toString(foods) : null ) + ", " + src);
%>
<!doctype html>
<html>
<head>
	<meta chrset='utf-8'>
	<title>개인취향검사결과</title>
</head>
<body>
	<h1>개인취향검사결과</h1>
	<%-- jsp주석 (해석/처리되지 않는다.) --%>
	<%-- 표현식 : 자바변수를 응답메세지에 출력할 때 사용 --%>
	<p>이름 : <%= name %></p>
	<p>선호컬러 : <%= color %></p>
	<p>선호동물 : <%= animal %></p>
	<p>선호음식 : <%= Arrays.toString(foods) %></p>
<hr/>
<p>홍길동님이 좋아하는 <img src='<%= src %>'/>입니다.</p>
</body>
</html>