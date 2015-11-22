package com.zjdd.report.quartz.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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
import com.zjdd.report.utils.MathUtils;
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
				.getParamsValues( ConstantsForSystemParams.LAST_DAILY_REPORT_DATE );

		String accDate = "";
		try {
			accDate = DateUtils.addDay( lastDate, 1 );
		} catch ( ParseException e1 ) {
		}
		
		/*
		 * 2.1 get current date,  compare to lastDate
		 */
		String currentDate = DateUtils.getSysDate( "yyyyMMdd" );
		if ( currentDate.equals( lastDate ) ) {
			return;
		}
		
		/*
		 * 3、生成日报表
		 */
		if ( !buildReport( accDate ) ) {
			return;
		}
		
		/*
		 * 4、update db accDate from system_params
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "paramKey", ConstantsForSystemParams.LAST_DAILY_REPORT_DATE );
		params.put( "paramValue", accDate );
		systemParamsDao.updateParamsValues( params );

	}

	private Boolean buildReport( String accDate ) {

		try {

			buildFlowReport( accDate );
			buildShopReport( accDate );
			buildOrderReport( accDate );

		} catch ( Exception e ) {
			e.printStackTrace();
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
		Integer totalFlowIn = reqMsgDao.countTotalFlowByDate( accDate );
		String operateBeginDate = systemParamsDao.getParamsValues( ConstantsForSystemParams.FLOW_BEGIN_DATE );
		Integer operateDate = DateUtils.daysBetween( operateBeginDate, accDate );
//		String dailyFlowInStr = MathUtils.getPercent( operateDate, totalFlowIn );
		double dailyFlowInStr = totalFlowIn / operateDate;
		DecimalFormat df1 = new DecimalFormat( "0.00" );
		String temp = df1.format( dailyFlowInStr );
		BigDecimal dailyFlowIn = new BigDecimal( temp );
		
		FLOW_VALID = flowVaild;
		Integer flowUsed = 0;

		SystemReportFlowEntity rptFlowEntity = new SystemReportFlowEntity();
		rptFlowEntity.setAccDate( accDate );
		rptFlowEntity.setFlowIn( flowIn );
		rptFlowEntity.setDailyFlowIn( dailyFlowIn );
		rptFlowEntity.setTotalFlowIn( totalFlowIn );
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
			String shopRateTemp = MathUtils.getPercent( shopCount, FLOW_VALID );
			shopRate = new BigDecimal( shopRateTemp );
		}
		
		Integer totalShopCount = dbOnlineDao.countTotalShopByDate( accDate );

		SystemReportShopEntity rptShopEntity = new SystemReportShopEntity();
		rptShopEntity.setAccDate( accDate );
		rptShopEntity.setShopCount( shopCount );
		rptShopEntity.setShopRate( shopRate );
		rptShopEntity.setShopTotalCount( totalShopCount );

		systemRptShopDao.insertRptShopInfo( rptShopEntity );

		return true;

	}

	/*
	 * 生成订单报表
	 */
	private Boolean buildOrderReport( String accDate ) {

		// 订单统计开始日
		String orderBeginDate = systemParamsDao.getParamsValues( ConstantsForSystemParams.ORDER_BEGIN_DATE );
		
		// 当日订单总数
		Integer orderCount = dbOnlineDao.countOrderByDate( accDate );
		
		// 累积订单总数
		Integer orderTotalCount = dbOnlineDao.countTotalOrderByDate( accDate );
		
		// 日均订单数
		Integer operateDate = DateUtils.daysBetween( orderBeginDate, accDate );
		double dailyOrderStr = orderTotalCount / operateDate;
		DecimalFormat df1 = new DecimalFormat( "0.00" );
		String temp = df1.format( dailyOrderStr );
		BigDecimal dailyOrder = new BigDecimal( temp );
		
		// 当日订单总额
		BigDecimal orderAmount = dbOnlineDao.countOrderAmountByDate( accDate );
		
		// 累积订单总额
		BigDecimal orderTotalAmount = dbOnlineDao.countTotalOrderAmountByDate( accDate );
		
		// 日均订单额
		double dailyOrderAmountStr = orderTotalAmount.intValue() / operateDate;
		temp = df1.format( dailyOrderAmountStr );
		BigDecimal dailyOrderAmount = new BigDecimal( temp );
		
		// 当日订单补贴金额
		BigDecimal orderReward = dbOnlineDao.countOrderRewardByDate( accDate );
		
		// 累积订单补贴金额
		BigDecimal orderRewardTotal = dbOnlineDao.countTotalOrderRewardByDate( accDate );
		
		
		if ( orderReward == null ) {
			orderReward = BigDecimal.ZERO;
		}

		SystemReportOrderEntity rptOrderEntity = new SystemReportOrderEntity();
		rptOrderEntity.setAccDate( accDate );
		rptOrderEntity.setOrderCount( orderCount );
		rptOrderEntity.setOrderTotalCount( orderTotalCount );
		rptOrderEntity.setDailyOrderCount( dailyOrder );
		
		rptOrderEntity.setOrderAmount( orderAmount );
		rptOrderEntity.setOrderTotalAmount( orderTotalAmount );
		rptOrderEntity.setDailyOrderAmount( dailyOrderAmount );
		
		rptOrderEntity.setOrderReward( orderReward );
		rptOrderEntity.setOrderRewardTotal( orderRewardTotal );
		
		systemRptOrderDao.insertRptOrderInfo( rptOrderEntity );

		return true;

	}

}
