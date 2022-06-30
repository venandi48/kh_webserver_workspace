package com.kh.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

/**
 * GET /mybatis/student/studentEnroll.do -> StudentEnrollController
 * POST /mybatis/student/studentEnroll.do -> StudentEnrollController
 */
public class StudentMapEnrollController extends AbstractController {
	private StudentService studentService;
	
	public StudentMapEnrollController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(studentService);
		return "student/studentEnroll";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 사용자 입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Map<String, Object> studentMap = new HashMap<>();
		studentMap.put("name", name);
		studentMap.put("tel", tel);
		System.out.println("studentMap = " + studentMap);
	
		// 2. 업무로직
		int result = studentService.insertStudent(studentMap);
		
		// 사용자 피드백
		request.getSession().setAttribute("msg", "학생Map정보 등록성공!");

		return "redirect:/student/studentEnroll.do";
	}
}
