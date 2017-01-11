package com.sapient.statestreetscreeningapplication.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "RATE")
public class Rate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RATE_ID")
	int rateId;

	@Column(name = "RATE")
	int rate;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "LOCATION_ID")
	private LocationNew location;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "POSITION_ID")
	private Position position;

	@Column(name = "RATE_TYPE")
	String rateType;

	@Column(name = "RATE_CATEGORY")
	String rateCategory;

	@Column(name = "IS_USED")
	private boolean isUsed;

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public LocationNew getLocation() {
		return location;
	}

	public void setLocation(LocationNew location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRateCategory() {
		return rateCategory;
	}

	public void setRateCategory(String rateCategory) {
		this.rateCategory = rateCategory;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
