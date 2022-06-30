package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {

	/**
	 * FactoryBuilder
	 * Factory
	 * SqlSession
	 * 
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null; // 커넥션 대신 sqlSession 사용
		String resource = "/mybatis-config.xml";
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		try (InputStream is = Resources.getResourceAsStream(resource)) {
			SqlSessionFactory factory = builder.build(is);
			sqlSession = factory.openSession(false); // auto-commit 여부
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
	}
}
