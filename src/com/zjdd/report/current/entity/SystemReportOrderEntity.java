package com.zjdd.report.current.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SystemReportOrderEntity {
	
	private String accDate;
	private Integer orderCount;
	private BigDecimal orderAmount;
	private BigDecimal orderReward;
	private Date createdDate;
	private Date updatedDate;
	
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate( String accDate ) {
		this.accDate = accDate;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount( Integer orderCount ) {
		this.orderCount = orderCount;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount( BigDecimal orderAmount ) {
		this.orderAmount = orderAmount;
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
	public BigDecimal getOrderReward() {
		return orderReward;
	}
	public void setOrderReward( BigDecimal orderReward ) {
		this.orderReward = orderReward;
	}
	
}
