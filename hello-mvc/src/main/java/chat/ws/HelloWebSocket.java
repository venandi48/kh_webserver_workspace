package chat.ws;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 톰캣이 웹소켓 관련 패키지 제공
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(
	value = "/chat/ws",
	configurator = HelloWebSocketConfigurator.class
)
public class HelloWebSocket {
	
	// client map객체 동기화처리 (멀티스레딩 환경에서 안전하게 사용할 수 있도록 함)
	public static Map<String, Session> clients = Collections.synchronizedMap(new HashMap<>()); 

	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("[Open]");
		
		// 1. 사용자정보 복사 ( EndpointConfig -> Session )
		Map<String, Object> configUserProps = config.getUserProperties();
		String memberId = (String) configUserProps.get("memberId");
		Map<String, Object> sessionUserProps = session.getUserProperties();
		sessionUserProps.put("memberId", memberId);
		
		// 2. clints에 추가
		clients.put(memberId, session);
		
		// 3. 사용자 입장 메세지 출력
		String payload = convertJsonPayload("welcome", memberId, "님이 입장했습니다.");
		onMessage(payload, session);
	}

	/**
	 * Map -> Json 변환처리
	 * @param type
	 * @param sender
	 * @param msg
	 * @return
	 */
	private String convertJsonPayload(String type, String sender, String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("sender", sender);
		map.put("msg", msg);
		map.put("time", System.currentTimeMillis());
		map.put("clientCnt", clients.size());
		return new Gson().toJson(map);
	}

	@OnMessage
	public void onMessage(String payload, Session sess) {
		System.out.println("[Message]");
		
		Collection<Session> sessions = clients.values();
		for(Session session : sessions) {
			Basic basic = session.getBasicRemote(); // 메세지를 보내는 주체
			try {
				basic.sendText(payload);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("[Close]");
		
		// 1. 사용자정보 가져오기
		Map<String, Object> sessionUserProps = session.getUserProperties();
		String memberId = (String) sessionUserProps.get("memberId");
		
		// 2. clients에서 제거
		clients.remove(memberId);

		// 3. 사용자 퇴장 메세지 출력
		String payload = convertJsonPayload("goodbye", memberId, "님이 퇴장했습니다.");
		onMessage(payload, session);
	}
}
