package com.kh.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;

public class EmpSearchController1 extends AbstractController {
	private EmpService empService;

	public EmpSearchController1(EmpService empService) {
		this.empService = empService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		
		// 2. 업무로직
		List<Map<String, Object>> empList = null;
		if(searchType == null || searchKeyword == null) {
			empList = empService.selectList();
		}
		else {
			Map<String, Object> param = new HashMap<>();
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			System.out.println("param = " + param);
			
			empList = empService.search1(param);
		}
		// System.out.println("empList = " + empList);
		
		request.setAttribute("empList", empList);
		
		return "emp/search1";
	}

}
