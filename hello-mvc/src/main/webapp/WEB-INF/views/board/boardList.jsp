<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="board.model.dto.BoardExt" %>
<%
List<BoardExt> list = (List<BoardExt>) request.getAttribute("list");
String pagebar = (String) request.getAttribute("pagebar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판 </h2>
<% 	if(loginMember != null) { %>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/board/boardEnroll';" />
<% 	} %>
	<table id="tbl-board">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		
<%
		if(list != null && !list.isEmpty()){
			for(BoardExt boardExt : list){
%>
			<tr>
				<td><%= boardExt.getNo() %></td>
				<td>
					<a href="<%= request.getContextPath() %>/board/boardView?no=<%= boardExt.getNo() %>"><%= boardExt.getTitle() %></a>
				</td>
				<td><%= boardExt.getMemberId() %></td>
				<td><%= boardExt.getRegDate() %></td>
				<td>
<%
				if(boardExt.getAttachCount() > 0) {
%>
					<img src="<%= request.getContextPath() %>/images/file.png" alt="" />
<%
				}
%>
				</td>
				<td><%= boardExt.getReadCount() %></td>
			</tr>		
<%
			}
		} else {
%>
			<tr>
				<td colspan="6">조회된 게시글이 없습니다.</td>
			</tr>
<%
		} 
%>
		</tbody>
	</table>

	<div id='pageBar'>
		<%= pagebar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
