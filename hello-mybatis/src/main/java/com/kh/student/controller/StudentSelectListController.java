package com.kh.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.dto.Student;
import com.kh.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	private StudentService studentService;

	public StudentSelectListController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업무로직 : 전체 학생 조회
		// DTO로 처리
		List<Student> studentList = studentService.selectStudentList();
		System.out.println("studentList = " + studentList);
		
		// Map으로 처리
		List<Map<String, Object>> studentMapList = studentService.studentMapList();
		System.out.println("studentMapList = " + studentMapList);
		
		
		// view단 전달
		request.setAttribute("studentList", studentList);
		request.setAttribute("studentMapList", studentMapList);
		
		return "student/selectList";
	}

}
