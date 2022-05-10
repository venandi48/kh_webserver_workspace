package com.kh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPersonServlet2 extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST /testPerson2.do 요청!");
		
		// 1. 인코딩 처리 (Post only)
		req.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값 처리
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("foods = " + (foods != null ? Arrays.toString(foods) : "{}"));
		
		
		// 3. 업무로직처리 - 좋아하는 동물 사진 추천
		String src = "";
		switch(animal) {
		case "강아지":
			src = "/web/images/dog.png"; break;
		case "고양이":
			src = "/web/images/cat.png"; break;
		case "물고기":
			src = "/web/images/fish.png"; break;
		}
		
		
		// 4. 응답메세지 처리
		// 응답메세지 MIME타입
		resp.setContentType("text/html; charset=utf-8");
		// 응답출력객체(character기반)
		PrintWriter out = resp.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta chrset='utf-8'>");
		out.println("	<title>개인취향검사결과 - POST</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h1>개인취향검사결과 - POST</h1>");
		out.println("	<p>이름 : " + name + "</p>");
		out.println("	<p>선호컬러 : " + color + "</p>");
		out.println("	<p>선호동물 : " + animal + "</p>");
		out.println("	<p>선호음식 : ");
		for (String food : foods)
			out.print("<span>" + food + "</span>");
		out.println("</p>");
		out.println("<hr/>");
		out.println("<p>" + name + "님이 좋아하는 <img src='"+ src+"'/>입니다.</p>");		
		out.println("</body>");
		out.println("</html>");
	}
}
