package common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
//@WebListener
public class SessionCounterListener implements HttpSessionListener {
	
	// 접속중인 사용자 수
	private static int activeSessions;

    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	activeSessions++;
    	System.out.println("> 세션생성! 접속사용자수 : " + activeSessions);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(activeSessions > 0) // 개발단계에서 테스트 시 서버 재구동했을때 데이터가 다르지 않도록 추가
    		activeSessions--;
    	System.out.println("> 세션폐기! 접속사용자수 : " + activeSessions);
    }
	
}
