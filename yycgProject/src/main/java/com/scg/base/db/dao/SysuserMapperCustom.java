package com.scg.base.db.dao;

import java.util.List;

import com.scg.base.db.model.vo.SysuserCustom;
import com.scg.base.db.model.vo.SysuserQueryCoustomVo;

public interface SysuserMapperCustom {
	//查询用户列表
	public List<SysuserCustom> selectUserCustomList(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
	//查询用户总数
	public int selectUserCount(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
	//根据用户名查询用户
	public SysuserCustom selectuserByuserid(String userid) throws Exception;
	//更新用户内容
	public int updateSysUser(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
}
