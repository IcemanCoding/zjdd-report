package com.zjdd.report.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjdd.report.demo.service.DemoService;

@RequestMapping ( "/demo" )
@Controller
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping ( value = "/demo", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> authenUser( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();

		/*
		 * 1、上送数据验证
		 */
		Integer res = demoService.testDemo();
		
		resData.put( "flag", res );
		resData.put( "msg", "success" );

		return resData;

	}

}
