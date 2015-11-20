package com.zjdd.report.online.dao;

import java.math.BigDecimal;

public interface DBOnlineDAO {
	
	public Integer countOrderByDate( String accDate );
	
	public Integer countShopByDate( String accDate );
	
	public BigDecimal countOrderAmountByDate( String accDate );
	
	public BigDecimal countOrderRewardByDate( String accDate );

}
