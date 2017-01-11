/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.StaffingMonthlyCostBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class StaffingMonthlyCostDao.
 */
public class StaffingMonthlyCostDao {
	
	/**
	 * Save staffing monthly cost.
	 *
	 * @param smcBean the smc bean
	 * @param smcBeanSet the smc bean set
	 * @return the sets the
	 */
	public static Set<StaffingMonthlyCostBean> saveStaffingMonthlyCost(StaffingMonthlyCostBean smcBean, Set<StaffingMonthlyCostBean> smcBeanSet){
		CustomLoggerUtils.INSTANCE.log.info("inside  saveStaffingMonthlyCost(StaffingMonthlyCostBean smcBean) method of StaffingMonthlyCostDao class" );
	//	StaffingMonthlyCostTable.getStaffingMonthlyCostTable().getSmcBeanSet().add(smcBean);
		smcBeanSet.add(smcBean);
		return smcBeanSet;
	}

}
