package com.sapient.statestreetscreeningapplication.ui.bean;


public class RateBean {

	int rateId;

	int rate;
	
	private LocationNewBean location;
	
	private CategoryBean category;
	
	private PositionBean position;
	
	String rateType;
	
	String rateCategory;
	
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

	public LocationNewBean getLocation() {
		return location;
	}

	public void setLocation(LocationNewBean location) {
		this.location = location;
	}

	public CategoryBean getCategory() {
		return category;
	}

	public void setCategory(CategoryBean category) {
		this.category = category;
	}

	public PositionBean getPosition() {
		return position;
	}

	public void setPosition(PositionBean position) {
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

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "RateBean [rateId=" + rateId + ", rate=" + rate + ", location="
				+ location + ", category=" + category + ", position="
				+ position + ", rateType=" + rateType + ", rateCategory="
				+ rateCategory + ", isUsed=" + isUsed + "]";
	}
}
