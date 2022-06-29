package com.kh.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

/**
 * GET /mybatis/student/studentEnroll.do -> StudentEnrollController
 * POST /mybatis/student/studentEnroll.do -> StudentEnrollController
 */
public class StudentEnrollController extends AbstractController {
	private StudentService studentService;
	
	public StudentEnrollController(StudentService studentService) {
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

			//db insert관련

			return "redirect:/student/studentEnroll.do";
		}
}
