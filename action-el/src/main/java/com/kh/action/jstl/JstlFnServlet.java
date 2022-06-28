package com.kh.action.jstl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JstlFnServlet
 */
@WebServlet("/jstl/fn.do")
public class JstlFnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("str1", "Hello world, Hello jstl");
		request.setAttribute("str2", "Hello EL");
		
		request.setAttribute("xss1", "<style>*{color: red}</style>");
		request.setAttribute("xss2", "<script>alert('메롱');</script>");
		
		request.getRequestDispatcher("/WEB-INF/views/jstl/fn.jsp").forward(request, response);
	}

}
