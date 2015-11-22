package com.zjdd.report.online.dao;

import java.math.BigDecimal;

public interface DBOnlineDAO {
	
	public Integer countOrderByDate( String accDate );
	
	public Integer countTotalOrderByDate( String accDate );
	
	public Integer countShopByDate( String accDate );
	
	public Integer countTotalShopByDate( String accDate );
	
	public BigDecimal countOrderAmountByDate( String accDate );
	
	public BigDecimal countTotalOrderAmountByDate( String accDate );
	
	public BigDecimal countOrderRewardByDate( String accDate );
	
	public BigDecimal countTotalOrderRewardByDate( String accDate );

}
