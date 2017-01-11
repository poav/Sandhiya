package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="RATE_LOG")
public class RateLog {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	//@Column(name = "RATE_LOG_ID")
	int Id;
	
	@Column(name="DATE")
	Date date;
	
	@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="PERSON_ID")
	Person person;
	
	@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name="RATE_ID")
	Rate rate;
	
	@Column(name="NEW_RATE_VALUE")
	int newRate;
	
	@Column(name="PREVIOUS_RATE_VALUE")
	int previousRate;
	
	@Column(name="CHANGE_IN_ACTIVATION_STATUS")
	boolean changeInActivationStatus;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public int getNewRate() {
		return newRate;
	}

	public void setNewRate(int newRate) {
		this.newRate = newRate;
	}

	public int getPreviousRate() {
		return previousRate;
	}

	public void setPreviousRate(int previousRate) {
		this.previousRate = previousRate;
	}

	public boolean isChangeInActivationStatus() {
		return changeInActivationStatus;
	}

	public void setChangeInActivationStatus(boolean changeInActivationStatus) {
		this.changeInActivationStatus = changeInActivationStatus;
	}
	
	
	

}
