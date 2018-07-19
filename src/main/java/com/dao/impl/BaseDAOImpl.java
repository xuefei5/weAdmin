package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dao.interfaces.IBaseDAO;


/**
 * @desc   构建dao基类实现类
 * @author yangsheng
 * @date   2018-07-18
 */
public class BaseDAOImpl implements IBaseDAO{
	
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//数据对象添加
	public int insert(Object object) {
		return getSqlSession().insert("insert", object);
	}
	
	//根据主键id查询数据对象
	public Object queryObjectByID(int id) {
		return getSqlSession().selectOne("queryObjectById", id);
	}

	//根据主键添加对象
	public int update(Object object) {
		return getSqlSession().update("update", object);
	}

	
}
