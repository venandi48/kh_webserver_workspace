package com.kh.el;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.person.model.dto.Person;

/**
 * Servlet implementation class BasicServlet
 */
@WebServlet("/el/basic.do")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp에 전송할 데이터 생성
		// request
		request.setAttribute("coffee", "예가체프");
		request.setAttribute("num", 1234567890);
		request.setAttribute("honggd", new Person("홍길동", '남', 33, false));
		request.setAttribute("book", "오브젝트");

		List<String> items = Arrays.asList("맥북", "애플워치", "제습기");
		Map<String, Object> map = new HashMap<>();
		map.put("name", "세종대왕");
		map.put("today", new Date());
		map.put("Dr.Zang", new Person("장영실", '남', 55, true));
		request.setAttribute("items", items);
		request.setAttribute("map", map);

		// session
		HttpSession session = request.getSession();
		session.setAttribute("book", "사피엔스");

		// application
		ServletContext application = getServletContext();
		application.setAttribute("movie", "듄");


		// view단 처리
		request.getRequestDispatcher("/WEB-INF/views/el/basic.jsp")
			.forward(request, response);
		
	}

}
