package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;

@Component
public interface RateService {
	
	public List<RateBean> viewRate();

	public void editRate(int rateId, int rate, PersonNewBean currentUser);
	public void saveRate(RateBean rateBean,PersonNewBean currentUser);

	public void editRateAdmin(int rateId, int rate, PersonNewBean currentUser,
			String rateIsActiveOrNot);

	public List<RateBean> viewAllUsedRate();

	public RateBean findRateByRateId(int rateId);

	public Boolean getIsRateActive(int rateId);

	public boolean findIfDuplicateRate(String locationName, String positionName,
			String domainName, String rateCategory, String rateType);

	public RateBean findRate(String locationName, String positionName,
			String domainName, String rateCategory, String rateType);

	

}
