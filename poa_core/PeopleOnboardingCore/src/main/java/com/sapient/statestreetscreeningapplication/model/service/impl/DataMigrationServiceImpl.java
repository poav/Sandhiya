package com.sapient.statestreetscreeningapplication.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.DataMigrationDao;
import com.sapient.statestreetscreeningapplication.model.service.DataMigrationService;

@Service
public class DataMigrationServiceImpl implements DataMigrationService{
	
	@Autowired
	DataMigrationDao dataMigrationDao;

	@Override
	public void setPersonStaffingCategory() {
		dataMigrationDao.setPersonStaffingCategory();
	}

	@Override
	public void setRate() {
		dataMigrationDao.setRate();
		
	}

	@Override
	public void setRateId() {
		dataMigrationDao.setRateId();
		
	}

}
