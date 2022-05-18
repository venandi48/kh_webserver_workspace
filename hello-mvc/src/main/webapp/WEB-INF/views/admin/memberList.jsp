<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page import="member.model.dto.Member"%>
<%
	List<Member> list = (List<Member>) request.getAttribute("list");
%>
<!-- 관리자용 admin.css link -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
<style>
    div#search-container {width: 100%; margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);}
    div#search-memberId {display: inline-block;}
    div#search-memberName{display:none;}
    div#search-gender{display:none;}
</style>
<section id="memberList-container">
	<h2>회원관리</h2>
    <div id="search-container">
       <label for="searchType">검색타입 :</label> 
       <select id="searchType">
           <option value="member_id">아이디</option>        
           <option value="member_name">회원명</option>
           <option value="gender">성별</option>
       </select>
       <div id="search-memberId" class="search-type">
           <form action="<%=request.getContextPath()%>/admin/memberFinder">
               <input type="hidden" name="searchType" value="member_id"/>
               <input type="text" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요."/>
               <button type="submit">검색</button>            
           </form>    
       </div>
       <div id="search-memberName" class="search-type">
           <form action="<%=request.getContextPath()%>/admin/memberFinder">
               <input type="hidden" name="searchType" value="member_name"/>
               <input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요."/>
               <button type="submit">검색</button>            
           </form>    
       </div>
       <div id="search-gender" class="search-type">
           <form action="<%=request.getContextPath()%>/admin/memberFinder">
               <input type="hidden" name="searchType" value="gender"/>
               <input type="radio" name="searchKeyword" value="M" checked> 남
               <input type="radio" name="searchKeyword" value="F"> 여
               <button type="submit">검색</button>
           </form>
       </div>
    </div>
	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
<% 
			if(list != null && !list.isEmpty()){
				for(int i = 0; i < list.size() ; i++){
					Member member = list.get(i);
%>
			<tr>
				<td><%= member.getMemberId() %></td>
				<td><%= member.getMemberName() %></td>
				<td><select class="member-role" data-member-id="<%= member.getMemberId() %>">
						<option value="<%=MemberRole.A%>"
							<%=member.getMemberRole() == MemberRole.A ? "selected" : ""%>>관리자</option>
						<option value="<%=MemberRole.U%>"
							<%=member.getMemberRole() == MemberRole.U ? "selected" : ""%>>일반회원</option>
				</select></td>
				<td><%=member.getGender() != null ? member.getGender() : ""%></td>
				<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
				<td><%= member.getEmail() != null ?  member.getEmail() : "" %></td>
				<td><%= member.getPhone() != null ? member.getPhone() : "" %></td>
				<td><%= member.getAddress() != null ? member.getAddress() : "" %></td>
				<td><%= member.getHobby() != null ? member.getHobby() : ""%></td>
				<td><%= member.getEnrollDate() %></td>
			</tr>
<% 
				}
			} else {
%>
			<tr>
				<td colspan="10">조회된 회원이 없습니다.</td>
			</tr>
<%
			} 
%>
		
		</tbody>
	</table>
</section>
<form 
	action="<%=request.getContextPath()%>/admin/memberRoleUpdate"
	method="POST" 
	name="updateMemberRoleFrm">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>
<script>

(() => {
<%
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	if(searchType != null && searchKeyword != null){
%>

		document.querySelectorAll(".search-type").forEach((div) => {
			div.style.display = "none";
		});
		let id = "";
		switch("<%= searchType %>"){
			case "member_id": id = "search-memberId"; break;
			case "member_name": id = "search-memberName"; break;
			case "gender": id = "search-gender"; break;
		}
		const option = document.querySelector("#searchType [value=<%= searchType %>]");
		option.selected = true;
		
		const target = document.querySelector(`#\${id}`);
		target.style.display = "inline-block";
		
		if("<%= searchType %>" == "gender" && "<%= searchKeyword %>" != "M"){
			target.querySelector("[value=F]").checked = true;
		} else{
			target.querySelector("[name=searchKeyword]").value = "<%= searchKeyword %>";
		}
<%
	}
%>
})();

searchType.addEventListener('change', (e) => {
	const {value} = e.target; // 구조분해할당
	console.log(value);
	
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	});
	
	let id = "";
	switch(value){
		case "member_id": id = "search-memberId"; break;
		case "member_name": id = "search-memberName"; break;
		case "gender": id = "search-gender"; break;
	}
	document.querySelector(`#\${id}`).style.display = "inline-block";
});

document.querySelectorAll(".member-role").forEach((select) => {
	select.addEventListener('change', (e) => {
		// console.log(e.target);
		// console.log(e.target.dataset.memberId); // key값 조회시 camelcasing으로 참조
		// console.log(e.target.value); // "U", "A"
		const memberId = e.target.dataset.memberId;
		const memberRole = e.target.value;
		
		// jsp에서 js의 Template String 문법 사용시 반드시 escaping처리 할 것
		if(confirm(`[\${memberId}]의 권한을 [\${memberRole}]로 변경하시겠습니까?`)){
			const frm = document.updateMemberRoleFrm;
			frm.memberId.value = memberId;
			frm.memberRole.value = memberRole;
			frm.submit();
		} else {
			e.target.querySelector("[selected]").selected = true;
		}
	});
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
