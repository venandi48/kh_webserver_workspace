<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery - xml</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
table {border : 1px solid #000; border-collapse: collapse; margin: 10px 0;}
th, td {border : 1px solid #000; text-align: center; padding: 3px; }
table img {width: 100px;}
</style>
</head>
<body>
	<h1>xml</h1>
	<button id="btn1">sample</button>
	<table id="tbl-books">
		<thead>
			<tr>
				<th>분류</th>
				<th>제목</th>
				<th>저자</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
	btn1.addEventListener('click', () => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/sample.xml",
			method : "GET",
			dataType : "xml",
			success(doc){
				// 응답받은 xml을 jquery가 parsing처리 후 DOM으로 전달.
				console.log(doc);
				console.dir(doc); // document
				
				const root = doc.querySelector(":root");
				console.log(root);
				console.dir(root);
				// 속성가져오기
				console.log(root.myattr); // undefined root.attributes(NamedNodeMap타입) 속성에서 관리됨.
				console.log(root.getAttribute("myattr"));
				
				const tbody = document.querySelector("#tbl-books tbody");
				tbody.innerHTML = "";
				
				// const books = root.children; 
				const books = [...root.children]; // HTMLCollection이기때문에 배열로 변환 필요
				console.log(books); 
				books.forEach((book) => {
					const [subject, title, author] = book.children;
					// console.log(subject, title, author);
					
					tbody.innerHTML += `<tr>
						<td>\${subject.innerHTML}</td>
						<td>\${title.textContent}</td>
						<td>\${author.innerHTML}</td>
					</tr>`;
				});
			},
			error : console.log
		});
	});
	</script>
	
	<hr />
	
	<button id="btn2">celeb</button>
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
	<script>
	btn2.addEventListener('click', () => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/xml",
			// dataType : "xml", // 생략시 응답데이터를 통해 자동으로 지정
			success(doc){
				console.log(doc);
				
				const root = doc.querySelector(":root"); // celebs 태그
				const len = root.getAttribute("len");
				console.log(`총 [\${len}]개의 데이터가 조회되었습니다/`);
				
				
				const celebs = [...root.children]; // 전개연산자
				document.querySelector("#tbl-celeb tbody").innerHTML =
					celebs.reduce((html, celeb) => {
						const [name, type, profile] = celeb.children; // iterator를 상속(진짜배열, 유사배열)
						const tr = `<tr>
							<td>\${celeb.getAttribute("no")}</td>
							<td>\${name.textContent}</td>
							<td>\${type.textContent}</td>
							<td><img src="<%= request.getContextPath() %>/images/\${profile.innerHTML}"></td>
						</tr>`;
						return html + tr;
					}, "");
			},
			error : console.log
		});
	});
	</script>
	
	<hr />
	<h2>일일 박스오피스 조회</h2>
	<div><input type="date" name="targetDt" id="targetDt" /></div>
	<table id="tbl-daily-boxoffice">
		<thead>
			<tr>
				<th>순위</th>
				<th>영화제목</th>
				<th>누적관객수</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<script>
	/**
	 * @ 실습문제 - 페이지로딩이 완료되면 어제 날짜로 박스오피스 조회를 자동으로 처리
	 */
	window.onload = () => {
	<%
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		date = cal.getTime();
		String formattedDate = sdf.format(date);
	%>
		document.querySelector("#targetDt").value = "<%= formattedDate %>";

		printDailyBoxOffice();
	}
	
	printDailyBoxOffice = () => {
		const val = document.querySelector("#targetDt").value.replace(/-/g, "");
		
		$.ajax({
			url : "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml",
			data : {
				key : 'a4c7bf4a52f0b3a57befce61f14f4c13',
				targetDt : val
			},
			success(doc){
				console.log(doc);
				
				// doc -> html
				const root = doc.querySelector(":root");
				const dailyBoxOfficeList = root.lastChild;
				const dailyBoxOffice = [...dailyBoxOfficeList.children];
				console.log(dailyBoxOffice);
				
				document.querySelector("#tbl-daily-boxoffice tbody").innerHTML =
					dailyBoxOffice.reduce((html, movie) => {
						const rank = movie.getElementsByTagName("rank")[0];
						const movieNm = movie.getElementsByTagName("movieNm")[0];
						const audiAcc = movie.getElementsByTagName("audiAcc")[0];

						const tr = `<tr>
							<td>\${rank.textContent}</td>
							<td>\${movieNm.textContent}</td>
							<td>\${audiAcc.textContent}</td>
						</tr>`;

						return html + tr;
					}, "");
			},
			error : console.log
		});
	}
	
	targetDt.addEventListener ('change', printDailyBoxOffice);
	</script>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>