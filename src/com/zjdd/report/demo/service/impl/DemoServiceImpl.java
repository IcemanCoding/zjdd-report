package com.zjdd.report.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjdd.report.demo.dao.DemoCMSDAO;
import com.zjdd.report.demo.dao.DemoDataDAO;
import com.zjdd.report.demo.dao.DemoOnlineDAO;
import com.zjdd.report.demo.service.DemoService;

public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoOnlineDAO demoOnlineDao;
	
	@Autowired
	private DemoCMSDAO demoCMSDao;
	
	@Autowired
	private DemoDataDAO demoDataDao;
	
	@Override
	public Integer testDemo() {
		
		Integer res = demoOnlineDao.dkUserCount();
		
		Integer demo2 = demoCMSDao.adminUserCount();
		
		Integer demo3 = demoDataDao.authUserCount();
		
		System.out.println( " res : " + res + "/// demo2 : " + demo2 + "/// demo3 : " + demo3 );
		
		return res;
	
	}

}
