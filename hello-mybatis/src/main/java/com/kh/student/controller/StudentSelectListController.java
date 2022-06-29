package com.kh.student.controller;

import com.kh.common.AbstractController;
import com.kh.student.model.service.StudentService;

public class StudentSelectListController extends AbstractController {
	private StudentService studentService;

	public StudentSelectListController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

}
