package com.zjdd.report.current.dao;

public interface ReqMsgDAO {
	
	/*
	 * 统计当日入流量
	 */
	public Integer countFlowByDate( String accDate );
	
	/*
	 * 统计累积入流量
	 */
	public Integer countTotalFlowByDate( String accDate );
	
	/*
	 * 统计当日有效流量
	 */
	public Integer countVaildFlowByDate( String accDate );
	
}
