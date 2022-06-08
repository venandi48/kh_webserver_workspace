<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/chat.css" />
<section id="chat-container">	
	<h2>채팅</h2>
	<button type="button" id="btn-userList">현재사용자확인</button>
	<span id="clientCnt"></span>
	<div id="msg-container">
		<ul></ul>
	</div>
	
	<div id="msg-editor" class="editor">
		<textarea name="" id="msg" cols="30" rows="2"></textarea>
		<button type="button" id="send">Send</button>
	</div>
	
	<hr style="margin:20px 0" />

	<!-- dm관련 섹션 -->		
	<div id="dm-container" >
		<label for="dm-client">DM</label>
		<select class="custom-select" id="dm-client">
			<option value="" disabled selected>접속자 목록</option>
		</select>
	</div>
	<div id="dm-editor" class="editor">
		<textarea id="dm-msg" cols="30" rows="2"></textarea>
		<button type="button" id="dm-send">Send</button>
	</div>
</section>
<script>
const host = location.host; // 접속하고있는 서버 도메인
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/ws`);

ws.onopen = (e) => {
	console.log('open', e);
};
ws.onmessage = (e) => {
	console.log('message', e);
	const payload = JSON.parse(e.data);
	const {type, sender, receiver, msg, time} = payload;
	console.log(type, sender, receiver, msg, time);
	
	let html = "";
	switch(type) {
	case "welcome" : 
	case "goodbye" :
	case "chat" : messageHandler(payload); break;
	case "dm" :
		alert(`\${sender}로부터 DM이 도착했습니다.
---------------------------------------------
발신자 : \${sender}
수신자 : \${receiver}
내용 : \${msg}
`);
		break;
	}
};

const messageHandler = (payload) => {
	const {type, sender, msg, time, clientCnt} = payload;
	const html = `
	<li class="\${type !== 'chat' ? 'center' : ''}">
		<span class="badge">\${sender}</span>
		\${msg}
	</li>`;
	document.querySelector("#msg-container ul").insertAdjacentHTML('beforeend', html);
	
	// 스크롤 최하단노출 처리
	const container = document.querySelector("#msg-container");
	container.scrollTop = container.scrollHeight;
	
	// 채팅 인원수 관리
	clientCnt && (document.querySelector("#clientCnt").innerHTML = clientCnt);
};

ws.onerror = (e) => {
	console.log('error', e);
};
ws.onclose = (e) => {
	console.log('close', e);
};

document.querySelector("#send").addEventListener('click', (e) => {
	const textarea = document.querySelector("#msg");
	if(!/^(.|\n)+$/.test(textarea.value)) return;
	
	const msg = {
		type : "chat",
		sender : "<%= loginMember.getMemberId() %>",
		msg : textarea.value,
		time : Date.now()
	};
	const payload = JSON.stringify(msg);
	ws.send(payload);
	textarea.value = "";
	textarea.focus();
});

// DM
document.querySelector("#dm-client").addEventListener('focus', (e) => {
	$.ajax({
		url : "<%= request.getContextPath() %>/chat/clients",
		success(clients) {
			console.log(clients);
			e.target.innerHTML = "";
			clients.forEach((client) => {
				const option = `<option value="\${client}">\${client}</option>`;
				e.target.insertAdjacentHTML('beforeend', option);
			});
		},
		error : console.log
	});
});

//
document.querySelector("#dm-send").addEventListener('click', (e) => {
	const receiver = document.querySelector("#dm-client");
	const textarea = document.querySelector("#dm-msg");
	console.log(receiver.valuel, textarea.value);
	
	if(!receiver.value || !textarea.value) return;
	
	const dm = {
		type : "dm",
		msg : textarea.value,
		sender : "<%= loginMember.getMemberId() %>",
		receiver : receiver.value,
		time : Date.now()
	};
	$.ajax({
		url : "<%= request.getContextPath() %>/chat/sendDM",
		method: "POST",
		data : {
			dm : JSON.stringify(dm)
		},
		success(resp) {
			console.log(resp);
		},
		error : console.log,
		complete() {
			receiver.innerHTML = "<option value='' disabled selected>접속자목록</option>";
			textarea.value = "";
		}
	});
});
</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>  
