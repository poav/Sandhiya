package com.sapient.statestreetscreeningapplication.ui.bean;


public class StatusChangeLogBean {
	
	
	private Long statusChangeLogId;
	
	
	private PersonScreeningDetailsBean candidatePSD;
	
	
	private StatusBean statusChangedFrom;
	
	
	private StatusBean statusChangedTo;
	
	
	private String statusChangeDate;
	
	
	private PersonNewBean operator;


	public Long getStatusChangeLogId() {
		return statusChangeLogId;
	}


	public void setStatusChangeLogId(Long statusChangeLogId) {
		this.statusChangeLogId = statusChangeLogId;
	}


	public PersonScreeningDetailsBean getCandidatePSD() {
		return candidatePSD;
	}


	public void setCandidatePSD(PersonScreeningDetailsBean candidatePSD) {
		this.candidatePSD = candidatePSD;
	}


	public StatusBean getStatusChangedFrom() {
		return statusChangedFrom;
	}


	public void setStatusChangedFrom(StatusBean statusChangedFrom) {
		this.statusChangedFrom = statusChangedFrom;
	}


	public StatusBean getStatusChangedTo() {
		return statusChangedTo;
	}


	public void setStatusChangedTo(StatusBean statusChangedTo) {
		this.statusChangedTo = statusChangedTo;
	}


	public String getStatusChangeDate() {
		return statusChangeDate;
	}


	public void setStatusChangeDate(String statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}


	public PersonNewBean getOperator() {
		return operator;
	}


	public void setOperator(PersonNewBean operator) {
		this.operator = operator;
	}


	@Override
	public String toString() {
		return "StatusChangeLogBean [statusChangeLogId=" + statusChangeLogId
				+ ", candidatePSD=" + candidatePSD + ", statusChangedFrom="
				+ statusChangedFrom + ", statusChangedTo=" + statusChangedTo
				+ ", statusChangeDate=" + statusChangeDate + ", operator="
				+ operator + "]";
	}
	
	


	
	
	

}
