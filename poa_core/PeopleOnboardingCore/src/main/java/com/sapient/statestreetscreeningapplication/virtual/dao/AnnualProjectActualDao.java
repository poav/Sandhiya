/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.AnnualProjectActualBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class AnnualProjectActualDao.
 */
public class AnnualProjectActualDao {
	
	/**
	 * Save project annual cost.
	 *
	 * @param apaBean the apa bean
	 * @param apaBeanSet the apa bean set
	 * @return the sets the
	 */
	public static Set<AnnualProjectActualBean> saveProjectAnnualCost(AnnualProjectActualBean apaBean, Set<AnnualProjectActualBean> apaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveProjectAnnualCost(AnnualProjectActualBean apaBean) method of AnnualProjectActualDao class" );
	//	AnnualProjectActualTable.getAnnualProjectActualTable().getApaBeanSet().add(apaBean);
		apaBeanSet.add(apaBean);
		return apaBeanSet;
	}
	
	/**
	 * Gets the annual project actual this year.
	 *
	 * @param year the year
	 * @param apaBeanSet the apa bean set
	 * @return the annual project actual this year
	 */
	public static List<AnnualProjectActualBean> getAnnualProjectActualThisYear(int year, Set<AnnualProjectActualBean> apaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAnnualProjectActualThisYear(int year) method of AnnualProjectActualDao class" );
		List<AnnualProjectActualBean> apaBeanListThisYear = new ArrayList<AnnualProjectActualBean>();
	//	for(AnnualProjectActualBean apaBean : AnnualProjectActualTable.getAnnualProjectActualTable().getApaBeanSet()){
		for(AnnualProjectActualBean apaBean : apaBeanSet){
			if( apaBean.getYear()==year ){
			    apaBeanListThisYear.add(apaBean);
			}
		}
		return apaBeanListThisYear;
	}

}
