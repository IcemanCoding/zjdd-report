package com.zjdd.report.current.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SystemReportFlowEntity {
	
	private String accDate;
	private Integer flowIn;
	private Integer totalFlowIn;
	private BigDecimal dailyFlowIn;
	private Integer flowValid;
	private Integer flowUsed;
	private Date createdDate;
	private Date updatedDate;
	
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate( String accDate ) {
		this.accDate = accDate;
	}
	public Integer getFlowIn() {
		return flowIn;
	}
	public void setFlowIn( Integer flowIn ) {
		this.flowIn = flowIn;
	}
	public Integer getFlowValid() {
		return flowValid;
	}
	public void setFlowValid( Integer flowValid ) {
		this.flowValid = flowValid;
	}
	public Integer getFlowUsed() {
		return flowUsed;
	}
	public void setFlowUsed( Integer flowUsed ) {
		this.flowUsed = flowUsed;
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
	public Integer getTotalFlowIn() {
		return totalFlowIn;
	}
	public void setTotalFlowIn( Integer totalFlowIn ) {
		this.totalFlowIn = totalFlowIn;
	}
	public BigDecimal getDailyFlowIn() {
		return dailyFlowIn;
	}
	public void setDailyFlowIn( BigDecimal dailyFlowIn ) {
		this.dailyFlowIn = dailyFlowIn;
	}
	
}
