package com.kh.emp.model.service;

import static com.kh.common.SqlSessionTemplate.getSqlSession;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.emp.model.dao.EmpDao;

public class EmpServiceImpl implements EmpService {
	private EmpDao empDao;

	public EmpServiceImpl(EmpDao empDao) {
		this.empDao = empDao;
	}
	
	@Override
	public List<Map<String, Object>> selectList() {
		List<Map<String, Object>> empList = null;
		try (SqlSession sqlSession = getSqlSession()){
			empList = empDao.selectList(sqlSession);
		}
		return empList;
	}

	@Override
	public List<Map<String, Object>> search1(Map<String, Object> param) {
		List<Map<String, Object>> empList = null;
		try (SqlSession sqlSession = getSqlSession()){
			empList = empDao.search1(sqlSession, param);
		}
		return empList;
	}
	
	@Override
	public List<Map<String, Object>> search2(Map<String, Object> param) {
		List<Map<String, Object>> empList = null;
		try (SqlSession sqlSession = getSqlSession()){
			empList = empDao.search2(sqlSession, param);
		}
		return empList;
	}
}
