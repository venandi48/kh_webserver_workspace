package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface EmpDao {

	List<Map<String, Object>> selectList(SqlSession sqlSession);

	List<Map<String, Object>> search1(SqlSession sqlSession, Map<String, Object> param);

	List<Map<String, Object>> search2(SqlSession sqlSession, Map<String, Object> param);

}
