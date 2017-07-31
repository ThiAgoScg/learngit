package com.scg.base.controller.loginRegist;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scg.base.db.model.vo.ActiveUser;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;
import com.scg.base.service.SysuserService;

@Controller
public class LoginController {
	@Autowired
	private SysuserService sysuserService;
	
	@RequestMapping(value = "/login.action")
	public String showLogin(){
		return "/base/login";
	}
	
	@RequestMapping(value="/loginsubmit.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitLogin(HttpSession session ,String userid,String pwd,String randomcode) throws Exception {
		//验证码校验
		if(randomcode == null || randomcode == ""){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 112, null));
		}
		String validateCode = (String) session.getAttribute("validateCode");
		if(validateCode!=null && !randomcode.equals(validateCode)){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 113, null));
		}
		
		ActiveUser activeUser = sysuserService.checkUserActive(userid, pwd);
		session.setAttribute(Config.ACTIVEUSER_KEY, activeUser);
		return ResultUtil.createSuccess(Config.MESSAGE, 107, new Object[]{activeUser.getUserid()});
	}
	@RequestMapping(value = "/first.action")
	public String showFirst(){
		return "/base/first";
	}
	
}
