package com.kh.web.menu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu.do")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자 입력값 처리
		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu");
		
		// 3. 업무로직
		int totalPrice = 0;
		switch(mainMenu) {
		case "한우버거": totalPrice += 5000; break;
		case "밥버거": totalPrice += 4500; break;
		case "치즈버거": totalPrice += 4000; break;
		}
		
		switch(sideMenu) {
		case "감자튀김": totalPrice += 1500; break;
		case "어니언링": totalPrice += 1700; break;
		}
		
		switch(drinkMenu) {
		case "콜라": totalPrice += 1000; break;
		case "사이다": totalPrice += 1000; break;
		case "커피": totalPrice += 1500; break;
		case "밀크쉐이크": totalPrice += 2500; break;
		}
		
		// 4. 응답 메세지 JSP 위임
		request.setAttribute("totalPrice", totalPrice);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/menu/menuEnd.jsp");
		reqDispatcher.forward(request, response);
	}

}
