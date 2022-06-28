package com.kh.action.jstl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.person.model.dto.Person;

/**
 * Servlet implementation class JstlLoopServlet
 */
@WebServlet("/jstl/loop.do")
public class JstlLoopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터
		String[] names = {"홍길동", "신사임당", "유관순", "정약용"};
		List<Person> persons = Arrays.asList(
				new Person("홍길동", '남', 33, false),
				new Person("신사임당", '여', 48, true),
				new Person("세종대왕", '남', 62, true));

		Set<Double> nums = new HashSet<>();
		nums.add(123312.124512);
		nums.add(124.21355);
		nums.add(7695.1233);
		nums.add(8.15615644);

		Map<String, Object> map = new HashMap<>();
		map.put("name", "kh");
		map.put("today", new Date());
		map.put("item", Arrays.asList("기계식키보드", "에어팟", "핸드크림"));
		map.put("세 종", new Person("세종대왕", '남', 62, true));
		
		String csv = "a,b,c,d,e,f,g";

		request.setAttribute("names", names);
		request.setAttribute("persons", persons);
		request.setAttribute("nums", nums);
		request.setAttribute("map", map);
		request.setAttribute("csv", csv);

		// view단처리
		request.getRequestDispatcher("/WEB-INF/views/jstl/loop.jsp").forward(request, response);
	}

}
