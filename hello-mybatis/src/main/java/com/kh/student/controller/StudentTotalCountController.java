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

public class StudentTotalCountController extends AbstractController {
	private StudentService studentService;
	
	public StudentTotalCountController(StudentService studentService) {
		this.studentService = studentService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalCount = studentService.getTotalCount();
		System.out.println("totalCount = " + totalCount);

		// 응답작성 - 비동기 json응답 직접 작성
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		new Gson().toJson(map, response.getWriter());

		return null; // 비동기는 viewName 필요X
	}
}
