package com.scg.base.service;

import java.util.List;

import com.scg.base.db.model.po.Sysuser;
import com.scg.base.db.model.po.Usergys;
import com.scg.base.db.model.po.Userjd;
import com.scg.base.db.model.po.Useryy;
import com.scg.base.db.model.vo.ActiveUser;
import com.scg.base.db.model.vo.SysuserCustom;
import com.scg.base.db.model.vo.SysuserQueryCoustomVo;
import com.scg.base.process.result.ResultInfo;

public interface SysuserService {
	
	//校验用户信息的登录
	public ActiveUser checkUserActive(String userid,String pwd)throws Exception;
	
	//查找一个分页中的数据
	public List<SysuserCustom> findUserCustomList(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
	
	//搜索条件的条数
	public int findUserCustomCount(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
	
	//添加用户
	public void saveSysUser(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception;
	
	//校验用户账号是否存在
	public Sysuser checkSysUserId(String userId) throws Exception;
	
	//校验供应商是否存在
	public Usergys checkUsergysMc(String mc) throws Exception;
	
	//校验监督局是否存在
	public Userjd checkUserjdMc(String mc) throws Exception;
	
	//校验医院是否存在
	public Useryy checkUseryyMc(String mc) throws Exception;
	
	//删除用户
	public ResultInfo  removeUserById(String userId) throws Exception ;
	
	//获取用户信息
	public SysuserCustom findSysUserById(String userId) throws Exception;
	
	//更新用户信息
	public void renewSysuser(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception; 
	
}
