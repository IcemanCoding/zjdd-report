package com.zjdd.report.current.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SystemReportShopEntity {
	
	private String accDate;
	private Integer shopCount;
	private BigDecimal shopRate;
	private Date createdDate;
	private Date updatedDate;
	
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate( String accDate ) {
		this.accDate = accDate;
	}
	public Integer getShopCount() {
		return shopCount;
	}
	public void setShopCount( Integer shopCount ) {
		this.shopCount = shopCount;
	}
	public BigDecimal getShopRate() {
		return shopRate;
	}
	public void setShopRate( BigDecimal shopRate ) {
		this.shopRate = shopRate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate( Date createdDate ) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate( Date updatedDate ) {
		this.updatedDate = updatedDate;
	}

}
