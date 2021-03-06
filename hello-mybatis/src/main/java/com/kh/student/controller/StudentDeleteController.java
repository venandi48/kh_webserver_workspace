package com.kh.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

public class StudentDeleteController extends AbstractController {
	private StudentService studentService;

	public StudentDeleteController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = studentService.deleteStudent(no);
		
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "정상적으로 삭제되었습니다.");
		new Gson().toJson(map, response.getWriter());
		
		return null;
	}

}
