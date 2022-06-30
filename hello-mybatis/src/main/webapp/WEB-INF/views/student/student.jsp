<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis 실습</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:10px auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
	line-height: 2em; /*input태그로 인해 cell이 높이가 길어져 border 두줄현상방지 */
}
table.tbl-student th{text-align:right;}
table.tbl-student td{text-align:left;}
table.tbl-student tr:last-of-type td:first-child{text-align:center;}
</style>
<script>
window.addEventListener('load', (e) => {
	// 전체 학생 수 조회 비동기요청
	$.ajax({
		url: "${pageContext.request.contextPath}/student/totalCount.do",
		data: {},
		success(data) {
			document.querySelector("#totalCount").innerHTML = data.totalCount;
		},
		error: console.log
	});
});
</script>
</head>
<body>
	<div id="student-container">
		<h2>학생정보 검색</h2>
		<p>총 학생수는 <span id="totalCount"></span>명입니다.</p>
		<form>
			<table class="tbl-student">
				<tr>
					<th>학생번호</th>
					<td>
						<input type="number" name="no" value="" required/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="검색" />
					</td>
				</tr>
			</table>
		</form>
		
		<h1>학생 정보 수정</h1>
        <form name="studentUpdateFrm" method="POST">
            <table class="tbl-student">
                <tr>
                    <th>학생번호</th>
                    <td>
                        <input type="number" name="no" value="${studentMap.no}" required/>
                    </td>
                </tr>
                <tr>
                    <th>학생이름</th>
                    <td>
                        <input type="text" name="name" value="${studentMap.name}" required/>
                    </td>
                </tr>
                <tr>
                    <th>학생전화번호</th>
                    <td>
                        <input type="tel" name="tel" value="${studentMap.tel}" required/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="수정" />
                        <input type="button" value="삭제" onclick="deleteStudent();" />
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<script>
	document.studentUpdateFrm.addEventListener('submit', (e) => {
		e.preventDefault();
		const frm = e.target;
		
		$.ajax({
			url: "${pageContext.request.contextPath}/student/studentUpdate.do",
			method: "POST",
			data: {
				no: frm.no.value,
				name: frm.name.value,
				tel: frm.tel.value
			},
			success(resp){
				alert("학생정보를 수정하였습니다.");
			},
			error: console.log
		});
	});
	
	const deleteStudent = () => {
		if(!confirm("정말 삭제하시겠습니까?")){
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/student/studentDelete.do",
			method: "POST",
			data: {
				no: studentUpdateFrm.no.value
			},
			success(resp){
				alert("학생정보를 삭제하였습니다.");
				location.href = "${pageContext.request.contextPath}/student/student.do"
			},
			error: console.log
		});
	};
	</script>
</body>
</html>
