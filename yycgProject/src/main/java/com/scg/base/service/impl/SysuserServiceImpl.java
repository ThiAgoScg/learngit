package com.scg.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scg.base.db.dao.SysuserMapper;
import com.scg.base.db.dao.SysuserMapperCustom;
import com.scg.base.db.dao.UsergysMapper;
import com.scg.base.db.dao.UserjdMapper;
import com.scg.base.db.dao.UseryyMapper;
import com.scg.base.db.model.po.Sysuser;
import com.scg.base.db.model.po.SysuserExample;
import com.scg.base.db.model.po.Usergys;
import com.scg.base.db.model.po.UsergysExample;
import com.scg.base.db.model.po.Userjd;
import com.scg.base.db.model.po.UserjdExample;
import com.scg.base.db.model.po.Useryy;
import com.scg.base.db.model.po.UseryyExample;
import com.scg.base.db.model.vo.ActiveUser;
import com.scg.base.db.model.vo.Menu;
import com.scg.base.db.model.vo.Operation;
import com.scg.base.db.model.vo.SysuserCustom;
import com.scg.base.db.model.vo.SysuserQueryCoustomVo;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.ExceptionResultInfo;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;
import com.scg.base.service.SysuserService;
import com.scg.util.MD5;
import com.scg.util.UUIDBuild;

@Service
public class SysuserServiceImpl implements SysuserService{
	
	@Autowired
	private SysuserMapper sysuserMapper;

	@Autowired
	private SysuserMapperCustom sysuserMapperCustom;
	
	
	@Autowired
	private UserjdMapper userjdMapper;
	
	@Autowired
	private UsergysMapper usergysMapper;
	
	@Autowired
	private UseryyMapper useryyMapper;

	
	/**
	 * 用户登录校验
	 */
	@Override
	public ActiveUser checkUserActive(String userid, String pwd) throws Exception{
		
		if(userid == null || userid == ""){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 110, null));
		}
		if(pwd == null || pwd == ""){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 111, null));
		}
			
		//校验用户是否存在
		Sysuser sysuser = this.checkSysUserId(userid);
		if(sysuser == null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 101, null));
		}
		//校验用户输入的密码是否正确
		String pwd_db = sysuser.getPwd();
		String pwd_md5 = new MD5().getMD5ofStr(pwd);
		if(!pwd_db.equalsIgnoreCase(pwd_md5)){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 114, null));
		}
		
		//将用户信息添加到ActiveUser类中方便于controller层存入session中
		ActiveUser activeUser = new ActiveUser();
		String groupid = sysuser.getGroupid();
		String sysid = sysuser.getSysid();
		String sysmc = null;
		activeUser.setUserid(userid);
		activeUser.setUsername(sysuser.getUsername());
		activeUser.setGroupid(groupid);
		activeUser.setSysid(sysuser.getSysid());
		if("1".equals(groupid) || "2".equals(groupid)){
			//1或者2是卫生局，卫生院都是监督表
			Userjd userjd = userjdMapper.selectByPrimaryKey(sysid);
			sysmc = userjd.getMc();
		}else if("3".equals(groupid)){
			//卫生室
			Useryy useryy = useryyMapper.selectByPrimaryKey(sysid);
			sysmc = useryy.getMc();
		}else if("4".equals(groupid)){
			Usergys usergys = usergysMapper.selectByPrimaryKey(sysid);
			sysmc = usergys.getMc();
		}
		activeUser.setSysmc(sysmc);
		return activeUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	//查询用户的列表
	@Override
	public List<SysuserCustom> findUserCustomList(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception {
		List<SysuserCustom> userlist = sysuserMapperCustom.selectUserCustomList(sysuserQueryCoustomVo);
		return userlist;
	}
	
	//查询用户的总条数
	@Override
	public int findUserCustomCount(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception {
		return sysuserMapperCustom.selectUserCount(sysuserQueryCoustomVo);
	}
	
	
	//校验账号公共接口
	@Override
	public Sysuser checkSysUserId(String userId)throws Exception{
	  //创建一个example
		SysuserExample example = new SysuserExample();
		//创建sql的appen的方法类
		SysuserExample.Criteria criteria = example.createCriteria();
		//设置append的条件
		criteria.andUseridEqualTo(userId);
		List<Sysuser> sysusers = sysuserMapper.selectByExample(example);
		if(sysusers!=null && sysusers.size()>0){
			return sysusers.get(0);
		}
		return null;
	}
	
	/**
	 * 校验手机号是否正确
	 * @param phone
	 * @return
	 */
	public boolean checkIsPhone(String phone){
		String regex ="^1[3|4|5|7|8][0-9]{9}$";
		boolean matches = Pattern.matches(regex, phone);
		
		return matches;
	}
	
	/**
	 * email邮箱校验
	 * @param email
	 * @return
	 */
	public boolean checkIsEmail(String email){
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	}
	
	//校验提供商的名称校验
	@Override
	public Usergys checkUsergysMc(String mc) throws Exception {
		UsergysExample example = new UsergysExample();
		UsergysExample.Criteria criteria = example.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Usergys> usergys = usergysMapper.selectByExample(example);
		if(usergys!=null && usergys.size() > 0){
			return usergys.get(0);
		}
		return null;
	}

	@Override
	public Userjd checkUserjdMc(String mc) throws Exception {
		UserjdExample example = new UserjdExample();
		UserjdExample.Criteria criteria = example.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Userjd> userjds = userjdMapper.selectByExample(example);
		if(userjds != null && userjds.size() > 0){
			return userjds.get(0);
		}
		return null;
	}

	@Override
	public Useryy checkUseryyMc(String mc) throws Exception {
		UseryyExample example = new UseryyExample();
		UseryyExample.Criteria criteria = example.createCriteria();
		criteria.andMcEqualTo(mc);
		List<Useryy> useryies = useryyMapper.selectByExample(example);
		if(useryies != null && useryies.size() > 0){
			return useryies.get(0);
		}
		return null;
	}
	
	//添加用户
	@Override
	public void saveSysUser(SysuserQueryCoustomVo sysuserQueryCoustomVo)throws Exception{
		SysuserCustom sysuserCustom = sysuserQueryCoustomVo.getSysuserCustom();
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> map = new HashMap<String,Object>();
		if(sysuserCustom == null){
			map.put("id", "sysuser_msg");
			resultInfo.setSysdata(map);
			resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
			resultInfo.setMessage("传入的参数有问题");
			throw new ExceptionResultInfo(resultInfo);
		}else{
			//首先判断需要判断的值是否为空
			String userid = sysuserCustom.getUserid();
			String username = sysuserCustom.getUsername();
			String userPwd = sysuserCustom.getPwd();
			String groupid = sysuserCustom.getGroupid();
			String sysmc = sysuserCustom.getSysmc();
			String status = sysuserCustom.getUserstate();
			String addr = sysuserCustom.getAddr();
			String phone = sysuserCustom.getPhone();
			String contact = sysuserCustom.getContact();
			String email = sysuserCustom.getEmail();
			String sysid = "";
			if(userid == null||userid == ""){
				map.put("id", "sysuser_userid");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("输入的账号不能为空");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			if(username == null || username == ""){
				map.put("id", "sysuser_username");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("输入的用户名不能为空");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			if(userPwd == null || userPwd == ""){
				map.put("id", "sysuser_password");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("输入的密码不能为空");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			
			if(groupid == null || groupid == ""){
				map.put("id", "sysuser_groupid");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("请选择用户类型");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			if(sysmc == null || sysmc == ""){
				map.put("id", "sysuser_sysmc");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("请输入用户单位名称");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			if(status == null || status ==""){
				map.put("id", "sysuser_userstate");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("请选择用户状态");
				throw new ExceptionResultInfo(resultInfo);
			}
			//地址 电话  联系方式 邮箱
			if(addr == null || addr == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1114, null);
				map.put("id", "sysuser_addr");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(phone == null || phone == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1115, null);
				map.put("id", "sysuser_phone");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(contact == null || contact == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1116, null);
				map.put("id", "sysuser_contact");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(email == null || email == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1117, null);
				map.put("id", "sysuser_email");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			//判断用户的数据是否正确
			Sysuser sysuser = this.checkSysUserId(userid);
			if(sysuser !=null){
				map.put("id", "sysuser_userid");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("该账号已存在");
				throw new ExceptionResultInfo(resultInfo);
			}
			
			if("1".equals(groupid) || "2".equals(groupid)){
				//1或者2是卫生局，卫生院都是监督表
				Userjd userjd = this.checkUserjdMc(sysmc);
				if(userjd==null){
					map.put("id", "sysuser_sysmc");
					resultInfo.setSysdata(map);
					resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
					resultInfo.setMessage("该监督单位不存在");
					throw new ExceptionResultInfo(resultInfo);
				}
				sysid = userjd.getId();
			}else if("3".equals(groupid)){
				//卫生室
				Useryy useryy = this.checkUseryyMc(sysmc);
				if(useryy == null){
					map.put("id", "sysuser_sysmc");
					resultInfo.setSysdata(map);
					resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
					resultInfo.setMessage("该医院单位不存在");
					throw new ExceptionResultInfo(resultInfo);
				}
				sysid = useryy.getId();
			}else if("4".equals(groupid)){
				Usergys usergys = this.checkUsergysMc(sysmc);
				if(usergys == null){
					map.put("id", "sysuser_sysmc");
					resultInfo.setSysdata(map);
					resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
					resultInfo.setMessage("该供货商不存在");
					throw new ExceptionResultInfo(resultInfo);
				}
				sysid = usergys.getId();
			}
			//验证手机号是否正确
			boolean phoneFlag = this.checkIsPhone(phone);
			if(!phoneFlag){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1118, null);
				map.put("id", "sysuser_phone");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			//验证邮箱是否正确
			boolean emailFlag = this.checkIsEmail(email);
			if(!emailFlag){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1119, null);
				map.put("id", "sysuser_email");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			sysuserCustom.setId(UUIDBuild.getUUID());
			sysuserCustom.setSysid(sysid);
			String spwd = sysuserCustom.getPwd();
			String md5pwd = new MD5().getMD5ofStr(spwd);
			sysuserCustom.setPwd(md5pwd);
			//插入用户
			int number = sysuserMapper.insert(sysuserCustom);
			if(number>0){
				map.put("id", "sysuser_msg");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
				resultInfo.setMessage("添加用户成功");
				throw new ExceptionResultInfo(resultInfo);
			}else {
				map.put("id", "sysuser_msg");
				resultInfo.setSysdata(map);
				resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
				resultInfo.setMessage("添加用户失败");
				throw new ExceptionResultInfo(resultInfo);
			}
		}
	}

	@Override
	public ResultInfo  removeUserById(String userId) throws Exception {
		Sysuser sysuser = sysuserMapper.selectByPrimaryKey(userId);
		if(sysuser==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 902, null));
		}
		int count = sysuserMapper.deleteByPrimaryKey(userId);
		if(count>0){
			return ResultUtil.createSuccess(Config.MESSAGE, 906, null);
		}else {
			return ResultUtil.createFail(Config.MESSAGE, 907, new Object[]{0,1});
		}
	}

	@Override
	public SysuserCustom findSysUserById(String userId) throws Exception {
		SysuserCustom sysuser = sysuserMapperCustom.selectuserByuserid(userId);
		if(sysuser==null){
		  ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 902, null));
		}
		return sysuser;
	}

	/**
	 * 更新用户
	 */
	@Override
	public void renewSysuser(SysuserQueryCoustomVo sysuserQueryCoustomVo) throws Exception {
		SysuserCustom sysuserCustom = sysuserQueryCoustomVo.getSysuserCustom();
		ResultInfo resultInfo = null;
		Map<String, Object> map = new HashMap<String,Object>();
		if(sysuserCustom==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 911, null));
		}else{
			String id = sysuserCustom.getId();
			String userid = sysuserCustom.getUserid();
			String username = sysuserCustom.getUsername();
			String groupid = sysuserCustom.getGroupid();
			String state = sysuserCustom.getUserstate();
			String pwd = sysuserCustom.getPwd();
			String sysmc = sysuserCustom.getSysmc();
			String addr = sysuserCustom.getAddr();
			String phone = sysuserCustom.getPhone();
			String contact = sysuserCustom.getContact();
			String email = sysuserCustom.getEmail();
			if(id == null || id ==""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1101, null);
				map.put("id", "msg");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(userid == null || userid == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1102, null);
				map.put("id", "userid");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(username == null || username == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1104, null);
				map.put("id", "username");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(pwd==null || pwd == ""){
				resultInfo =ResultUtil.createFail(Config.MESSAGE, 1105, null);
				map.put("id", "password");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(groupid == null || groupid == ""){
				resultInfo =ResultUtil.createFail(Config.MESSAGE, 1107, null);
				map.put("id", "groupid");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(state == null || state == ""){
				resultInfo =ResultUtil.createFail(Config.MESSAGE, 1110, null);
				map.put("id", "userstate");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			//地址 电话  联系方式 邮箱
			if(addr == null || addr == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1114, null);
				map.put("id", "addr");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(phone == null || phone == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1115, null);
				map.put("id", "phone");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(contact == null || contact == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1116, null);
				map.put("id", "contact");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(email == null || email == ""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1117, null);
				map.put("id", "email");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if(!"0".equals(groupid)&&(sysmc == null || sysmc == "")){
				resultInfo =ResultUtil.createFail(Config.MESSAGE, 1108, null);
				map.put("id", "sysmc");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			if("0".equals(groupid)&& sysmc!=null&&sysmc!=""){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1113, null);
				map.put("id", "sysmc");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			
			Sysuser sysuser = this.checkSysUserId(userid);
			if(sysuser!=null && !sysuser.getId().equals(id)){
				resultInfo =ResultUtil.createFail(Config.MESSAGE, 1103, null);
				map.put("id", "userid");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			String sysid = "";
			if("1".equals(groupid)||"2".equals(groupid)){
				Userjd userjd = this.checkUserjdMc(sysmc);
				if(userjd==null){
					resultInfo =ResultUtil.createFail(Config.MESSAGE, 1109, null);
					map.put("id", "sysmc");
					resultInfo.setSysdata(map);
					ResultUtil.throwExcepion(resultInfo);
				}
				sysid = userjd.getId();
			}else if("3".equals(groupid)){
				Useryy useryy = this.checkUseryyMc(sysmc);
				if(useryy==null){
					resultInfo =ResultUtil.createSuccess(Config.MESSAGE, 1111, null);
					map.put("id", "sysmc");
					resultInfo.setSysdata(map);
					ResultUtil.throwExcepion(resultInfo);
					
				}
				sysid = useryy.getId();
			}else if("4".equals(groupid)){
				Usergys usergys = this.checkUsergysMc(sysmc);
				if(usergys==null){
					resultInfo =ResultUtil.createFail(Config.MESSAGE, 1112, null);
					map.put("id", "sysmc");
					resultInfo.setSysdata(map);
					ResultUtil.throwExcepion(resultInfo);
				}
				sysid = usergys.getId();
			}
			//验证手机号是否正确
			boolean phoneFlag = this.checkIsPhone(phone);
			if(!phoneFlag){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1118, null);
				map.put("id", "phone");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			//验证邮箱是否正确
			boolean emailFlag = this.checkIsEmail(email);
			if(!emailFlag){
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1119, null);
				map.put("id", "email");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
			sysuserCustom.setSysid(sysid);
			int count = sysuserMapperCustom.updateSysUser(sysuserQueryCoustomVo);
			if(count<0){
				
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 900, new Object[]{1,1});
				map.put("id", "msg");
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
			}
		}
		
	}
	
}
