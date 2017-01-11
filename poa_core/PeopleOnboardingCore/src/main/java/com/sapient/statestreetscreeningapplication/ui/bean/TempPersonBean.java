package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;



public class TempPersonBean {
	
	private long tempPersonId;
	
	
	private String tempPersonName;
	
	
	
	private String tempPersonEmail;
	
	
	private String tempPersonStartDate;


	@Override
	public String toString() {
		return "TempPersonBean [tempPersonId=" + tempPersonId
				+ ", tempPersonName=" + tempPersonName + ", tempPersonEmail="
				+ tempPersonEmail + ", tempPersonStartDate="
				+ tempPersonStartDate + "]";
	}


	public long getTempPersonId() {
		return tempPersonId;
	}


	public void setTempPersonId(long tempPersonId) {
		this.tempPersonId = tempPersonId;
	}


	public String getTempPersonName() {
		return tempPersonName;
	}


	public void setTempPersonName(String tempPersonName) {
		this.tempPersonName = tempPersonName;
	}


	public String getTempPersonEmail() {
		return tempPersonEmail;
	}


	public void setTempPersonEmail(String tempPersonEmail) {
		this.tempPersonEmail = tempPersonEmail;
	}


	public String getTempPersonStartDate() {
		return tempPersonStartDate;
	}


	public void setTempPersonStartDate(String tempPersonStartDate) {
		this.tempPersonStartDate = tempPersonStartDate;
	}

}
