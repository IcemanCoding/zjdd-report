package com.zjdd.report.current.entity;

import java.util.Date;

public class SystemReportInviteBusinessEntity {
	
	private String accDate;
	private Integer inviteBusiness;
	private Date createdDate;
	private Date updatedDate;
	
	public String getAccDate() {
		return accDate;
	}
	public void setAccDate( String accDate ) {
		this.accDate = accDate;
	}
	public Integer getInviteBusiness() {
		return inviteBusiness;
	}
	public void setInviteBusiness( Integer inviteBusiness ) {
		this.inviteBusiness = inviteBusiness;
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
