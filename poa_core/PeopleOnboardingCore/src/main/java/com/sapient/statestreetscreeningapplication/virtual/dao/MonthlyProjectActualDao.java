/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectActualBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Class MonthlyProjectActualDao.
 */
public class MonthlyProjectActualDao {
	
	/**
	 * Save project monthly cost.
	 *
	 * @param mpaBean the mpa bean
	 * @param mpaBeanSet the mpa bean set
	 * @return the sets the
	 */
	public static Set<MonthlyProjectActualBean> saveProjectMonthlyCost(MonthlyProjectActualBean mpaBean, Set<MonthlyProjectActualBean> mpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveProjectMonthlyCost(MonthlyProjectActualBean mpaBean, Set<MonthlyProjectActualBean> mpaBeanSet) method of MonthlyProjectActualDao class" );
		mpaBeanSet.add(mpaBean);
		return mpaBeanSet;
    }
	
	
	/**
	 * Gets the monthly project actual this quarter.
	 *
	 * @param month1 the month1
	 * @param month2 the month2
	 * @param month3 the month3
	 * @param year the year
	 * @param mpaBeanSet the mpa bean set
	 * @return the monthly project actual this quarter
	 */
	public static List<MonthlyProjectActualBean> getMonthlyProjectActualThisQuarter(Months month1, Months month2, Months month3, int year, Set<MonthlyProjectActualBean> mpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectActualThisQuarter(Months month1, Months month2, Months month3, int year, Set<MonthlyProjectActualBean> mpaBeanSet) method of MonthlyProjectActualDao class" );
		List<MonthlyProjectActualBean> mpaBeanListThisQuarter = new ArrayList<MonthlyProjectActualBean>();
		for(MonthlyProjectActualBean mpaBean : mpaBeanSet){
			if( (mpaBean.getYear()==year) && ((mpaBean.getMonth()==month1)||(mpaBean.getMonth()==month2)||(mpaBean.getMonth()==month3)) ){
				 mpaBeanListThisQuarter.add(mpaBean);
			}
		}
		return mpaBeanListThisQuarter;
	}


	/**
	 * Gets the monthly project actual this year.
	 *
	 * @param year the year
	 * @param mpaBeanSet the mpa bean set
	 * @return the monthly project actual this year
	 */
	public static List<MonthlyProjectActualBean> getMonthlyProjectActualThisYear(int year, Set<MonthlyProjectActualBean> mpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getMonthlyProjectActualThisYear(int year, Set<MonthlyProjectActualBean> mpaBeanSet) method of MonthlyProjectActualDao class" );
		List<MonthlyProjectActualBean> mpaBeanListThisYear = new ArrayList<MonthlyProjectActualBean>();
		for(MonthlyProjectActualBean mpaBean : mpaBeanSet){
			if( mpaBean.getYear()==year ){
				mpaBeanListThisYear.add(mpaBean);
			}
		}
		return mpaBeanListThisYear;
	}
	
}