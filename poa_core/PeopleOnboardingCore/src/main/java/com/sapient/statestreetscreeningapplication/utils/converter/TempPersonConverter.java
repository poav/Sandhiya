package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.sapient.statestreetscreeningapplication.model.entity.TempPerson;
import com.sapient.statestreetscreeningapplication.ui.bean.TempPersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

public class TempPersonConverter {
	
	public static TempPersonBean tempPersonEntityToBean(TempPerson tempPerson) {
		CustomLoggerUtils.INSTANCE.log.info("Inside tempPersonEntityToBean in TempPersonConverter");
		TempPersonBean tempPersonBean=new TempPersonBean();
		
		tempPersonBean.setTempPersonEmail(tempPerson.getTempPersonEmail());
		tempPersonBean.setTempPersonId(tempPerson.getTempPersonId());
		tempPersonBean.setTempPersonName(tempPerson.getTempPersonName());
		SimpleDateFormat formatter ;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		if(tempPersonBean.getTempPersonStartDate()!=null){
		tempPersonBean.setTempPersonStartDate(formatter.format(tempPerson.getTempPersonStartDate()));
		}
		return tempPersonBean;
	}
	
	public static TempPerson tempPersonBeanToEntity(TempPersonBean tempPersonBean) {
		CustomLoggerUtils.INSTANCE.log.info("Inside tempPersonBeanToEntity in TempPersonConverter");
		TempPerson tempPerson=new TempPerson();
		
		tempPerson.setTempPersonEmail(tempPersonBean.getTempPersonEmail());
		tempPerson.setTempPersonId(tempPersonBean.getTempPersonId());
		tempPerson.setTempPersonName(tempPersonBean.getTempPersonName());
		SimpleDateFormat formatter ;  
	    formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if(tempPersonBean.getTempPersonStartDate()!=null){
			tempPerson.setTempPersonStartDate(formatter.parse(tempPersonBean.getTempPersonStartDate()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempPerson;
	}

}
