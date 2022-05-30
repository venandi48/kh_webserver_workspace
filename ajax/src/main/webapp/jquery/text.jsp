<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery - text</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>text</h1>
	<button id="btn1">text</button>
	<script>
	btn1.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/text",
			method : "GET", // 전송방식 GET(기본값, 생략가능) | POST | PUT | PATH ...
			data : {
				q : "abcde",
				mode : 123,
				isFinal : true
			}, // 사용자입력값 직렬화처리 후 GET방식 url에 추가, POST방식이면 body부분에 작성
			dataType : "text", // text | html | script | json | xml 응답데이터 타입지정
			
			beforeSend : function() {
				// 요청 전 호출메소드
				console.log("beforeSend");
			},
			// 메소드 단축문법으로 작성 가능하다
			success(responseText) {
				// xhr.responseText 후처리 후 success 메소드에 전달
				// readyState = 4 && status = 200
				console.log("success : ", responseText);
			},
			error(jqxhr, textStatus, err) {
				// readyState = 4 && status != 200
				console.log("error : ", jqxhr, textStatus, err);
			},
			complete() {
				// 응답후(성공, 실패) 반드시 실행하는 메소드
				console.log("complete");
			}
		});
	});
	</script>
	
	
	<button id="btn2">csv</button>
	<table id="tbl-celeb">
		<thead>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>타입</th><!-- select태그 하위에 해당타입 selected 처리 -->
				<th>프로필</th><!-- img태그 처리 -->
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div id=result></div>
	<script>
	/*
	 * csv (comma separated value)
	*/
	btn2.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/csv",
			//method : "GET", // 생략가능
			dataType : "text",
			success(response) {
				console.log(response);
			},
			error(xhr, textStatus, err) {
				console.log("error : ", xhr, textStatus, err);
			}
		});
	});
	</script>
</body>
</html>