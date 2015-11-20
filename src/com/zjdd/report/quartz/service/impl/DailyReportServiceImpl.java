package com.zjdd.report.quartz.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjdd.report.current.dao.ReqMsgDAO;
import com.zjdd.report.current.dao.SystemParamsDAO;
import com.zjdd.report.current.dao.SystemRptFlowDAO;
import com.zjdd.report.current.dao.SystemRptOrderDAO;
import com.zjdd.report.current.dao.SystemRptShopDAO;
import com.zjdd.report.current.entity.SystemReportFlowEntity;
import com.zjdd.report.current.entity.SystemReportOrderEntity;
import com.zjdd.report.current.entity.SystemReportShopEntity;
import com.zjdd.report.online.dao.DBOnlineDAO;
import com.zjdd.report.quartz.service.DailyReportService;
import com.zjdd.report.utils.DateUtils;
import com.zjdd.report.utils.constants.ConstantsForSystemParams;

public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	private SystemParamsDAO systemParamsDao;

	@Autowired
	private DBOnlineDAO dbOnlineDao;

	@Autowired
	private ReqMsgDAO reqMsgDao;

	@Autowired
	private SystemRptFlowDAO systemRptFlowDao;

	@Autowired
	private SystemRptShopDAO systemRptShopDao;

	@Autowired
	private SystemRptOrderDAO systemRptOrderDao;

	private static Integer FLOW_VALID = 0;

	@Override
	public void buildDailyReport() {

		/*
		 * 1、生成日报开关控制
		 */
		String flag = systemParamsDao
				.getParamsValues( ConstantsForSystemParams.DAILY_REPORT_SWITCH );
		if ( flag == null || flag.equals( "" ) || flag.equals( "0" ) ) {
			return;
		}

		/*
		 * 2、取出上次日报生成时间
		 */
		String lastDate = systemParamsDao
				.getParamsValues( ConstantsForSystemParams.DAILY_REPORT_SWITCH );

		String accDate = "";
		try {
			accDate = DateUtils.addDay( lastDate, 1 );
		} catch ( ParseException e1 ) {
		}
		
		/*
		 * 3、生成日报表
		 */
		if ( !buildReport( accDate ) ) {
			return;
		}
		
		try {
			String nextDate = DateUtils.addDay( accDate, 1 );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

	}

	private Boolean buildReport( String accDate ) {

		try {

			buildFlowReport( accDate );
			buildShopReport( accDate );
			buildOrderReport( accDate );

		} catch ( Exception e ) {
			return false;
		}

		return true;

	}

	/*
	 * 生成流量报表
	 */
	private Boolean buildFlowReport( String accDate ) {

		Integer flowIn = reqMsgDao.countFlowByDate( accDate );
		Integer flowVaild = reqMsgDao.countVaildFlowByDate( accDate );
		FLOW_VALID = flowVaild;
		Integer flowUsed = 0;

		SystemReportFlowEntity rptFlowEntity = new SystemReportFlowEntity();
		rptFlowEntity.setAccDate( accDate );
		rptFlowEntity.setFlowIn( flowIn );
		rptFlowEntity.setFlowUsed( flowUsed );
		rptFlowEntity.setFlowValid( flowVaild );

		systemRptFlowDao.insertRptFlowInfo( rptFlowEntity );

		return true;

	}

	/*
	 * 生成开店信息报表
	 */
	private Boolean buildShopReport( String accDate ) {

		/*
		 * 开店数
		 */
		Integer shopCount = dbOnlineDao.countShopByDate( accDate );

		BigDecimal shopRate = BigDecimal.ZERO;
		if ( FLOW_VALID != 0 ) {
			DecimalFormat df = new DecimalFormat( "######0.00" );
			double shopRateTemp = FLOW_VALID / shopCount;
			String temp = df.format( shopRateTemp );
			shopRate = new BigDecimal( temp );
		}

		SystemReportShopEntity rptShopEntity = new SystemReportShopEntity();
		rptShopEntity.setAccDate( accDate );
		rptShopEntity.setShopCount( shopCount );
		rptShopEntity.setShopRate( shopRate );

		systemRptShopDao.insertRptShopInfo( rptShopEntity );

		return true;

	}

	/*
	 * 生成订单报表
	 */
	private Boolean buildOrderReport( String accDate ) {

		/*
		 * 订单数
		 */
		Integer orderCount = dbOnlineDao.countOrderByDate( accDate );
		BigDecimal orderAmount = dbOnlineDao.countOrderAmountByDate( accDate );
		BigDecimal orderReward = dbOnlineDao.countOrderRewardByDate( accDate );
		if ( orderReward == null ) {
			orderReward = BigDecimal.ZERO;
		}

		SystemReportOrderEntity rptOrderEntity = new SystemReportOrderEntity();
		rptOrderEntity.setAccDate( accDate );
		rptOrderEntity.setOrderAmount( orderAmount );
		rptOrderEntity.setOrderCount( orderCount );
		rptOrderEntity.setOrderReward( orderReward );

		systemRptOrderDao.insertRptOrderInfo( rptOrderEntity );

		return true;

	}

}
