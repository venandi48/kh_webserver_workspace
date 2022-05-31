package com.kh.ajax.jquery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ajax.name.manager.NameManager;

/**
 * Servlet implementation class autoCompleteServlet
 */
@WebServlet("/jquery/autoComplete")
public class autoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String search = request.getParameter("search");
		System.out.println("search = " + search);

		// 2. 업무로직
		// Arrays.asList(String...)
		List<String> names = NameManager.getInstance().getNames();
		List<String> results = new ArrayList<>();

		for (String name : names) {
			if (name.contains(search)) {
				results.add(name);
			}
		}

		// 3. 응답처리
		response.setContentType("text/plane; charset=utf-8");
		PrintWriter out = response.getWriter();
		for (int i = 0; i < results.size(); i++) {
			out.append(results.get(i));
			if (i != results.size() - 1)
				out.append(",");
		}
	}

}
