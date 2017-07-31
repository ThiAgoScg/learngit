package com.scg.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.scg.base.db.model.po.Dictinfo;
import com.scg.base.db.model.po.Dicttype;
import com.scg.base.db.model.po.PageQuery;
import com.scg.base.db.model.vo.SysuserCustom;
import com.scg.base.db.model.vo.SysuserQueryCoustomVo;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.DataGridResultInfo;
import com.scg.base.process.result.ExceptionResultInfo;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;
import com.scg.base.service.SysConfigService;
import com.scg.base.service.SysuserService;

@Controller
@RequestMapping(value="/user")
public class SysuserController {
	
	@Autowired
	private SysuserService sysuserService;
	@Autowired
	private SysConfigService sysConfigService;
	/**
	 * 查询页面
	 */
	@RequestMapping(value = "/showUser.action")
	public String queryUser(Model model)throws Exception{
		//将页面所需要的数据取出，传到页面
		Dicttype dicttype = sysConfigService.findDictTypeByName("用户类型");
		List<Dictinfo> dictinfos = sysConfigService.findDictInfoAll(dicttype.getTypecode());
		model.addAttribute("dictinfos", dictinfos);
		Dicttype usertate = sysConfigService.findDictTypeByName("供货状态");
		List<Dictinfo> state = sysConfigService.findDictInfoAll(usertate.getTypecode());
		model.addAttribute("state", state);
		return "/base/user/queryUser";
	}
	
	/***
	 * 获取页面信息
	 * @param sysuserQueryCoustomVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUser_result.action",method = RequestMethod.POST)
	@ResponseBody
	public DataGridResultInfo resposeUserListToJson(SysuserQueryCoustomVo sysuserQueryCoustomVo,int page,int rows)throws Exception{
		//开始分页
		//查询总数
	//	rows = page!=1?(rows-1):rows;
		int total = sysuserService.findUserCustomCount(sysuserQueryCoustomVo);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		sysuserQueryCoustomVo.setPageQuery(pageQuery);
		
		List<SysuserCustom> list = sysuserService.findUserCustomList(sysuserQueryCoustomVo);
		
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		//填充total条数
		dataGridResultInfo.setTotal(total);
		//填充rows 
		dataGridResultInfo.setRows(list);
		return dataGridResultInfo;
	}
	
	@RequestMapping(value="/addUser.action")
	public String showAddUser(Model model)throws Exception {
		//将页面所需要的数据取出，传到页面
		Dicttype dicttype = sysConfigService.findDictTypeByName("用户类型");
		List<Dictinfo> dictinfos = sysConfigService.findDictInfoAll(dicttype.getTypecode());
		model.addAttribute("dictinfos", dictinfos);
		return "/base/user/addUser";
	}
	
	
	/**
	 * 添加用户
	 * @param sysuserQueryCoustomVo
	 * @return
	 */
	@RequestMapping(value="/submitUser.action",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String addUserSubmit(SysuserQueryCoustomVo sysuserQueryCoustomVo){
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			sysuserService.saveSysUser(sysuserQueryCoustomVo);
			map.put("id", "sysuser_msg");
			resultInfo.setSysdata(map);
			resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
			resultInfo.setMessage("添加成功");
			return JSON.toJSONString(resultInfo);
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo er = (ExceptionResultInfo) e;
				resultInfo = er.getResultInfo();
			    return JSON.toJSONString(resultInfo);
			}else {
				map.put("id", "sysuser_msg");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("未知异常请稍后重试");
				return JSON.toJSONString(resultInfo);
			}
			
		}
	}	
	
	
	/**
	 * 删除用户根据uid
	 * @param userId
	 * @return
	 */
	
	@RequestMapping(value="/removeUser.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo removeUserById(String userId){
		try {
			ResultInfo resultInfo = sysuserService.removeUserById(userId);
			return resultInfo;
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo ex = (ExceptionResultInfo) e;
				return ex.getResultInfo();
			}else {
				return ResultUtil.createFail(Config.MESSAGE, 900, null);
			}
		}
	}
	
	/**
	 * 显示修改用户的页面
	 * @param userId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showupuser.action")
	public  String showupdateuser(String userid,Model model) throws Exception{
		SysuserCustom sysuserCustom = sysuserService.findSysUserById(userid);
		model.addAttribute("upuser", sysuserCustom);
		//将页面所需要的数据取出，传到页面
		Dicttype dicttype = sysConfigService.findDictTypeByName("用户类型");
		List<Dictinfo> dictinfos = sysConfigService.findDictInfoAll(dicttype.getTypecode());
		model.addAttribute("dictinfos", dictinfos);
		return "/base/user/updateuser";
	}
	
	
	@RequestMapping(value="/updateuser.action",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateUser(SysuserQueryCoustomVo sysuserQueryCoustomVo){
		Map<String, Object> map = new HashMap<String ,Object>();
		ResultInfo resultInfo = null;
		try {
			sysuserService.renewSysuser(sysuserQueryCoustomVo);
			resultInfo = ResultUtil.createSuccess(Config.MESSAGE, 906, null);
			map.put("id", "msg");
			resultInfo.setSysdata(map);
			return resultInfo;
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo ex = (ExceptionResultInfo) e;
				resultInfo = ex.getResultInfo();
				return resultInfo;
			}else{
				resultInfo =  ResultUtil.createFail(Config.MESSAGE, 900, null);
				map.put("id", "msg");
				resultInfo.setSysdata(map);
				return resultInfo;
			}
		}
	}
}
