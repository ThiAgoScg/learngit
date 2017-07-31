package com.scg.base.service;

import java.util.List;

import com.scg.base.db.model.po.Dictinfo;
import com.scg.base.db.model.po.Dicttype;

public interface SysConfigService {
	/**
	 * 系统配置信息接口
	 * @author asus
	 *
	 */
	
	
	//根据数据字典的typename查询数据字典dicttype的id
	
	public Dicttype findDictTypeByName(String name)throws Exception ;
	
	//根据数据字典的typecode查询所有关于数据字典的信息
	public List<Dictinfo> findDictInfoAll(String typecode)throws Exception;
	
	public List<Dictinfo> serchDictinfo(String name) throws Exception;
}
