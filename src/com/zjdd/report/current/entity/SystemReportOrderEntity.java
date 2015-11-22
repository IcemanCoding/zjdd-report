package com.zjdd.report.current.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SystemReportOrderEntity {
	
	private String accDate;
	private Integer orderCount;
	private Integer orderTotalCount;
	private BigDecimal dailyOrderCount;
	private BigDecimal orderAmount;
	private BigDecimal orderTotalAmount;
	private BigDecimal dailyOrderAmount;
	private BigDecimal orderReward;
	private BigDecimal orderRewardTotal;
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
	public Integer getOrderTotalCount() {
		return orderTotalCount;
	}
	public void setOrderTotalCount( Integer orderTotalCount ) {
		this.orderTotalCount = orderTotalCount;
	}
	public BigDecimal getDailyOrderCount() {
		return dailyOrderCount;
	}
	public void setDailyOrderCount( BigDecimal dailyOrderCount ) {
		this.dailyOrderCount = dailyOrderCount;
	}
	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount( BigDecimal orderTotalAmount ) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public BigDecimal getDailyOrderAmount() {
		return dailyOrderAmount;
	}
	public void setDailyOrderAmount( BigDecimal dailyOrderAmount ) {
		this.dailyOrderAmount = dailyOrderAmount;
	}
	public BigDecimal getOrderRewardTotal() {
		return orderRewardTotal;
	}
	public void setOrderRewardTotal( BigDecimal orderRewardTotal ) {
		this.orderRewardTotal = orderRewardTotal;
	}
	
}
