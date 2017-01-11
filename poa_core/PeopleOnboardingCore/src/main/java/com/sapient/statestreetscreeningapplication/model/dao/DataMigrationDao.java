package com.sapient.statestreetscreeningapplication.model.dao;

import org.springframework.stereotype.Component;

@Component
public interface DataMigrationDao {
	
	public void setPersonStaffingCategory();
	
	public void setRate();
	
	public void setRateId();

}
