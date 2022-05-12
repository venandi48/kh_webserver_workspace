<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp" %>

		<h1>main1</h1>
		<p><%= name %>님, 반갑습니다.</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Temporibus tempore magnam voluptates quos saepe aspernatur deserunt repellendus obcaecati possimus aut accusamus quae sequi numquam impedit omnis eligendi eius amet pariatur?</p>
		
		<script>
		const title = document.querySelector("header h1").innerHTML;
		alert(title);
		</script>

<%@ include file="/jsp/footer.jsp" %>