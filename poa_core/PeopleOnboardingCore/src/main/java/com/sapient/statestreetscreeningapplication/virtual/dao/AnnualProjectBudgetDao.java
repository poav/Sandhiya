/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.AnnualProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class AnnualProjectBudgetDao.
 */
public class AnnualProjectBudgetDao {
	
	/**
	 * Save project annual budget.
	 *
	 * @param apbBean the apb bean
	 * @param apbBeanSet the apb bean set
	 * @return the sets the
	 */
	public static Set<AnnualProjectBudgetBean> saveProjectAnnualBudget(AnnualProjectBudgetBean apbBean, Set<AnnualProjectBudgetBean> apbBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveProjectAnnualBudget(AnnualProjectBudgetBean apbBean) method of AnnualProjectActualDao class" );
	//	AnnualProjectBudgetTable.getAnnualProjectBudgetTable().getApbBeanSet().add(apbBean);
		apbBeanSet.add(apbBean);
		return apbBeanSet;
	}
	
	
	/**
	 * Gets the annual project budget.
	 *
	 * @param year the year
	 * @param projectNewBean the project new bean
	 * @param apbBeanSet the apb bean set
	 * @return the annual project budget
	 */
	public static AnnualProjectBudgetBean getAnnualProjectBudget(int year, ProjectNewBean projectNewBean, Set<AnnualProjectBudgetBean> apbBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getAnnualProjectBudget(int year, ProjectNewBean projectNewBean) method of QuarterlyProjectBudgetDao class" );
		AnnualProjectBudgetBean annualProjectBudgetBean = new AnnualProjectBudgetBean();
	//	for(AnnualProjectBudgetBean apbBean : AnnualProjectBudgetTable.getAnnualProjectBudgetTable().getApbBeanSet()){
		for(AnnualProjectBudgetBean apbBean : apbBeanSet){
			if( (apbBean.getYear()==year) && (apbBean.getProjectNewBean().getProjectId()==projectNewBean.getProjectId()) ){
				annualProjectBudgetBean = apbBean;
			}
		}
		return annualProjectBudgetBean;
	}


}
