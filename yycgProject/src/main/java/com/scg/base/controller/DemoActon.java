package com.scg.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.Response;
import com.scg.base.service.BssAreaService;

@Controller
public class DemoActon {
	@Autowired 
	private BssAreaService bssAreaService;
	
	@RequestMapping(value = "/home")
	public String first(){
		return "/base/first";
	}
	
	@RequestMapping(value="/welcome")
	public String welcome(){
		return "/base/welcome";
	}
	
	@RequestMapping(value="/responseJson",produces="text/json;charset=UTF-8")
	@ResponseBody
	public String getJsonString(){
		
		List<Map<String, Object>> findAllArea = bssAreaService.findAllArea();
	    String jsonString = JSONObject.toJSONString(findAllArea);
		return jsonString;
	}
}
