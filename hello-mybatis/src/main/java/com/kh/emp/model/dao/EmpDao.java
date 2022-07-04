package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectList(SqlSession sqlSession);

	List<Map<String, Object>> search1(SqlSession sqlSession, Map<String, Object> param);

	List<Map<String, Object>> search2(SqlSession sqlSession, Map<String, Object> param);

	List<Map<String, Object>> searchJobList(SqlSession sqlSession);

	List<Map<String, Object>> search3(SqlSession sqlSession, Map<String, Object> param);

	List<Map<String, Object>> selectDeptList(SqlSession sqlSession);

	Map<String, Object> selectOne(SqlSession sqlSession, int empId);

	int updateEmp(SqlSession sqlSession, Map<String, Object> param);

}
