package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {
	
	@Override
	public List<Map<String, Object>> selectList(SqlSession sqlSession) {
		return sqlSession.selectList("emp.selectList");
	}
	
	@Override
	public List<Map<String, Object>> search1(SqlSession sqlSession, Map<String, Object> param) {
		return sqlSession.selectList("emp.search1", param);
	}
	
	@Override
	public List<Map<String, Object>> search2(SqlSession sqlSession, Map<String, Object> param) {
		return sqlSession.selectList("emp.search2", param);
	}
	
	@Override
	public List<Map<String, Object>> searchJobList(SqlSession sqlSession) {
		return sqlSession.selectList("emp.selectJobList");
	}
	
	@Override
	public List<Map<String, Object>> search3(SqlSession sqlSession, Map<String, Object> param) {
		return sqlSession.selectList("emp.search3", param);
	}
	
	@Override
	public List<Map<String, Object>> selectDeptList(SqlSession sqlSession) {
		return sqlSession.selectList("emp.selectDeptList");
	}
	
	@Override
	public Map<String, Object> selectOne(SqlSession sqlSession, int empId) {
		return sqlSession.selectOne("emp.selectOne", empId);
	}
	
	@Override
	public int updateEmp(SqlSession sqlSession, Map<String, Object> param) {
		return sqlSession.update("emp.updateEmp", param);
	}
}
