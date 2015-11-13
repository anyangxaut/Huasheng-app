package com.huasheng.dao;

/**
 * 接口类：查询方法（searchlatest按创建日期降序排列，即查询最近更新的信息；
 * searchexpert查询专家介绍表，因为其中字段与其他表不同，所以单独列为一个方法）
 * @author anyang
 *
 */
public interface HuaShengNewsDao {

	
	public String searchlatest(String sql);
	
	
	public String searchexpert(String sql);
	
}
