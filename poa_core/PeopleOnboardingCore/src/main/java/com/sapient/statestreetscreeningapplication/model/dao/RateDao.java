package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.Rate;
import com.sapient.statestreetscreeningapplication.model.entity.RateLog;

@Component
public interface RateDao {
	public List<Rate> viewRate();

	public Rate editRate(int rateId, int rate);

	public  Rate findRateByRateId(int rateId);

	public void saveRate(Rate rate, Person person,RateLog rateLog);

	public void editRateAdmin(int rateId, int rate, Person currentUser,String rateIsActiveOrNot);

	public List<Rate> viewAllUsedRate();

	public void enterChangesInRateLog(int newRate , Person currentUser, int previousRateId, boolean changepInActiveStatus,RateLog rateLog);


	public Rate findRate(LocationNew location, Position position, Category category,
			String rateCategory, String rateType);

	

	
	
	

}
