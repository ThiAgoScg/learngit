package com.scg.base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.scg.base.db.model.vo.ActiveUser;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.ResultUtil;
import com.scg.util.ResourcesUtil;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		//判断用户是否已经登录
		if(activeUser != null){
			return true;
		}
		
		//访问公开的路径
		List<String> urls = ResourcesUtil.gekeyList(Config.ANONYMOUS_ACTIONS);
		String requestURL = request.getRequestURI();
		for(String url:urls){
			if(requestURL.indexOf(url)>=0){
				return true;
			}
		}
		
		//否则跳转到登录页面
		//ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 106, null));
		request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request, response);
		//response.sendRedirect("/yycgProject/login.action");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
