package com.zjdd.report.current.dao;

public interface ReqMsgDAO {
	
	public Integer countFlowByDate( String accDate );
	
	public Integer countVaildFlowByDate( String accDate );

}
