<%@page import="board.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="board.model.dto.BoardExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
BoardExt board = (BoardExt) request.getAttribute("board");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%= board.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= board.getMemberId() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= board.getReadCount() %></td>
		</tr>
<%
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) { 
				for(Attachment attach : attachments) { 
%>
		<tr>
			<th>첨부파일</th>
			<td>
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath() %>/board/fileDownload?no=<%= attach.getNo()%>"><%= attach.getOriginalFileName() %></a>
			</td>
		</tr>
<%
				}
			}
%>
		<tr>
			<th>내 용</th>
			<td><%= board.getContent() %></td>
		</tr>
<%
		if(loginMember != null 
			&& (loginMember.getMemberId().equals(board.getMemberId()) 
					|| loginMember.getMemberRole() == MemberRole.A)) {
%>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
		<form 
			name="boardDelFrm"
			action="<%= request.getContextPath() %>/board/boardDelete"
			method="POST">
			<input type="hidden" name="no" value="<%= board.getNo() %>" />
		</form>
		<script>
		/**
		 * POST /board/boardDelete
		 *  - no전송
		 *  - 알아서 attachment 레코드 삭제되지만(cascade) 서버에는 파일이 남아있음
		 *  - 서버에 저장된 파일 삭제 : java.io.File 활용
		 */
		const deleteBoard = () => {
			if(confirm("게시글을 삭제하시겠습니까?")){
				document.boardDelFrm.submit();
			}
		};
		</script>
<%
		}	
%>
	</table>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
