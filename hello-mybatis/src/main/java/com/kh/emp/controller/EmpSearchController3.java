package com.kh.emp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.AbstractController;
import com.kh.emp.model.service.EmpService;

public class EmpSearchController3 extends AbstractController {
	private EmpService empService;

	public EmpSearchController3(EmpService empService) {
		this.empService = empService;
	}

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String[] jobCodes = request.getParameterValues("jobCode");
		String[] deptCodes = request.getParameterValues("deptCode");
		List<String> deptCodeList = deptCodes != null ? Arrays.asList(deptCodes) : null;

		Map<String, Object> param = new HashMap<>();
		param.put("jobCodes", jobCodes);
		param.put("deptCodeList", deptCodeList);

		// 2. 업무로직
		List<Map<String, Object>> jobList = empService.selectJobList();
		System.out.println("jobList = " + jobList);
		
		List<Map<String, Object>> deptList = empService.selectDeptList();

		List<Map<String, Object>> list = empService.search3(param);
		System.out.println("list = " + list);

		// 3. veiw단 데이터 전달
		request.setAttribute("jobList", jobList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("deptCodeList", deptCodeList);
		request.setAttribute("empList", list);

		return "emp/search3";
	}

}
