package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="STATUS_CHANGE_LOG")
public class StatusChangeLogEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="STATUS_CHANGE_LOG_ID")
	private Long statusChangeLogId;
	
	@OneToOne
	@JoinColumn(name = "CANDIDATE_PSD")
	private PersonScreeningDetails candidatePSD;
	
	
	@OneToOne
	@JoinColumn(name="STATUS_CHANGED_FROM")
	private Status statusChangedFrom;
	
	@OneToOne
	@JoinColumn(name="STATUS_CHANGED_TO")
	private Status statusChangedTo;
	
	@Column(name = "STATUS_CHANGE_DATE")
	private Date statusChangeDate;
	
	@OneToOne
	@JoinColumn(name="OPERATOR")
	private Person operator;

	public Long getStatusChangeLogId() {
		return statusChangeLogId;
	}

	public void setStatusChangeLogId(Long statusChangeLogId) {
		this.statusChangeLogId = statusChangeLogId;
	}

	public PersonScreeningDetails getCandidatePSD() {
		return candidatePSD;
	}

	public void setCandidatePSD(PersonScreeningDetails candidatePSD) {
		this.candidatePSD = candidatePSD;
	}

	public Status getStatusChangedFrom() {
		return statusChangedFrom;
	}

	public void setStatusChangedFrom(Status statusChangedFrom) {
		this.statusChangedFrom = statusChangedFrom;
	}

	public Status getStatusChangedTo() {
		return statusChangedTo;
	}

	public void setStatusChangedTo(Status statusChangedTo) {
		this.statusChangedTo = statusChangedTo;
	}

	public Date getStatusChangeDate() {
		return statusChangeDate;
	}

	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}

	public Person getOperator() {
		return operator;
	}

	public void setOperator(Person operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "StatusChangeLogEntity [StatusChangeLogId=" + statusChangeLogId
				+ ", candidatePSD=" + candidatePSD + ", statusChangedFrom="
				+ statusChangedFrom + ", statusChangedTo=" + statusChangedTo
				+ ", statusChangeDate=" + statusChangeDate + ", operator="
				+ operator + "]";
	}
	
	

}
