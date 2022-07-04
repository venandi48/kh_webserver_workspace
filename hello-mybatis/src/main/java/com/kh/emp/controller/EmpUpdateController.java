package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;

public class EmpUpdateController extends AbstractController {
	private EmpService empService;

	public EmpUpdateController(EmpService empService) {
		this.empService = empService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empId = Integer.parseInt(request.getParameter("empId"));
		
		Map<String, Object> emp = empService.selectOne(empId);
		
		// select-option태그용 
		request.setAttribute("jobList", empService.selectJobList());
		request.setAttribute("deptList", empService.selectDeptList());

		request.setAttribute("emp", emp);
		
		return "emp/empUpdate";
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자입력값 처리
		String empId = request.getParameter("empId");
		String jobCode = request.getParameter("jobCode");
		String deptCode = request.getParameter("deptCode");
		Map<String, Object> param = new HashMap<>();
		param.put("empId", empId);
		param.put("jobCode", jobCode);
		param.put("deptCode", deptCode);
		System.out.println("param = " + param);

		// 2. 업무로직
		int result = empService.updateEmp(param);

		// 3. 리다이렉트
		return "redirect:/emp/empUpdate.do?empId=" + empId;
	}
}
