package com.zjdd.report.current.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjdd.report.quartz.service.DailyReportService;

@RequestMapping ( "/report" )
@Controller
public class ReportInfoController {
	
	@Autowired
	private DailyReportService dailyReportService;
	
	@RequestMapping ( value = "/operateReport", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> operateReport( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		
		
		
		
		return resData;

	}
	
	@RequestMapping ( value = "/buildOperateReport", method = RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> buildOperateReport( HttpServletRequest request ) {

		Map<String, Object> resData = new HashMap<String, Object>();
		
		try {
			dailyReportService.buildDailyReport();
		} catch ( Exception e ) {
			
		}
		
		
		return resData;

	}

}
