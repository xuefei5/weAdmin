package com.dao.interfaces;

import org.apache.ibatis.annotations.Param;


/**
 * @desc   构建dao基类接口
 * @author yangsheng
 * @param <T>
 * @date   2018-07-18
 */

public interface IBaseDAO<T> {

	/**
	 * 根据主键查找对象
	 * @param id
	 * @return
	 */
	public Object queryObjectByID(@Param("id")int id);
	
	/**
	 * @desc  数据对象添加
	 * @param object
	 * @return 
	 */
	public int insert(Object object);
	
	/**
	 * 根据对象更新
	 * @param object
	 * @return
	 */
	public int update(Object object);


}
