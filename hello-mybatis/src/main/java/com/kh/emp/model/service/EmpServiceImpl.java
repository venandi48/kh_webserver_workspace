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
	
	@Override
	public List<Map<String, Object>> selectJobList() {
		List<Map<String, Object>> jobList = null;
		try (SqlSession sqlSession = getSqlSession()){
			jobList = empDao.searchJobList(sqlSession);
		}
		return jobList;
	}
	
	@Override
	public List<Map<String, Object>> search3(Map<String, Object> param) {
		List<Map<String, Object>> list = null;
		try (SqlSession sqlSession = getSqlSession()){
			list = empDao.search3(sqlSession, param);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> selectDeptList() {
		List<Map<String, Object>> deptList = null;
		try (SqlSession sqlSession = getSqlSession()){
			deptList = empDao.selectDeptList(sqlSession);
		}
		return deptList;
	}
	
	@Override
	public Map<String, Object> selectOne(int empId) {
		Map<String, Object> emp = null;
		try (SqlSession sqlSession = getSqlSession()){
			emp = empDao.selectOne(sqlSession, empId);
		}
		return emp;
	}
	
	@Override
	public int updateEmp(Map<String, Object> param) {
		int result = 0;
		SqlSession sqlSession = getSqlSession();
		try {
			result = empDao.updateEmp(sqlSession, param);
			sqlSession.commit();
		} catch(Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return result;
	}
}
