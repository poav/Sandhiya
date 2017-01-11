package com.sapient.statestreetscreeningapplication.utils.converter;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Rate;
import com.sapient.statestreetscreeningapplication.ui.bean.RateBean;

@Component
public class RateConverter {
	
	public static RateBean rateEntityToBean(Rate rate) {
		RateBean rateBean=new RateBean();
		
		rateBean.setRateId(rate.getRateId());
		rateBean.setCategory(CategoryConverter.convertCategoryEntityToCategoryBean(rate.getCategory()));
		rateBean.setLocation(LocationNewConverter.locationEntityToBean(rate.getLocation()));
		rateBean.setPosition(PositionConverter.convertPositionEntityToBean(rate.getPosition()));
		rateBean.setRate(rate.getRate());
		rateBean.setRateCategory(rate.getRateCategory());
		rateBean.setRateType(rate.getRateType());
		rateBean.setUsed(rate.isUsed());
		return rateBean;
		
	}
	
	public static Rate rateBeanToEntity(RateBean rateBean) {
		Rate rate=new Rate();
		
		rate.setRateId(rateBean.getRateId());
		rate.setCategory(CategoryConverter.convertCategoryBeanToCategoryEntity(rateBean.getCategory()));
		rate.setLocation(LocationNewConverter.locationBeanToEntity(rateBean.getLocation()));
		rate.setPosition(PositionConverter.convertPositionBeanToEntity(rateBean.getPosition()));
		rate.setRate(rateBean.getRate());
		rate.setRateCategory(rateBean.getRateCategory());
		rate.setRateType(rateBean.getRateType());
		rate.setUsed(rateBean.getIsUsed());
		return rate;
		
	}
	
	public static List<RateBean> rateEntityListToBeanList(List<Rate> rateEntityList){
		List<RateBean> rateBeanList=new ArrayList<>();
		for(Rate r:rateEntityList){
			rateBeanList.add(rateEntityToBean(r));
		}
		return rateBeanList;
	}


}
