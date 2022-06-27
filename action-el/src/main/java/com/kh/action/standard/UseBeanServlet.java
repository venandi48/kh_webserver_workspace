package com.kh.action.standard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.person.model.dto.Person;

/**
 * Servlet implementation class UseBeanServlet
 */
@WebServlet("/standard/useBean.do")
public class UseBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// jsp에서 사용할 데이터 생성
		Person person = new Person("홍길동", '남', 30, true);

		// view단 forwarding
		request.setAttribute("person", person);
		request.getRequestDispatcher("/WEB-INF/views/standard/useBean.jsp")
			.forward(request, response);

	}

}
