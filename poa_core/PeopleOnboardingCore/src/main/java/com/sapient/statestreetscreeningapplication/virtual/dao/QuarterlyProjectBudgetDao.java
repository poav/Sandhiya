/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;

// TODO: Auto-generated Javadoc
/**
 * The Class QuarterlyProjectBudgetDao.
 */
public class QuarterlyProjectBudgetDao {
	
	/**
	 * Save project quarterly budget.
	 *
	 * @param qpbBean the qpb bean
	 * @param qpaBeanSet the qpa bean set
	 * @return the sets the
	 */
	public static Set<QuarterlyProjectBudgetBean> saveProjectQuarterlyBudget(QuarterlyProjectBudgetBean qpbBean, Set<QuarterlyProjectBudgetBean> qpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveProjectQuarterlyBudget(QuarterlyProjectBudgetBean qpbBean) method of QuarterlyProjectBudgetDao class" );
	//	QuarterlyProjectBudgetTable.getQuarterlyProjectBudgetTable().getQpbBeanSet().add(qpbBean);
		qpaBeanSet.add(qpbBean);
		return qpaBeanSet;
	}
	
	/**
	 * Gets the quarterly project budget.
	 *
	 * @param quarter the quarter
	 * @param year the year
	 * @param projectNewBean the project new bean
	 * @param qpaBeanSet the qpa bean set
	 * @return the quarterly project budget
	 */
	public static QuarterlyProjectBudgetBean getQuarterlyProjectBudget(Quarters quarter, int year, ProjectNewBean projectNewBean, Set<QuarterlyProjectBudgetBean> qpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getQuarterlyProjectBudget(Quarters quarter, int year, ProjectNewBean projectNewBean) method of QuarterlyProjectBudgetDao class" );
		QuarterlyProjectBudgetBean quarterlyProjectBudgetBean = new QuarterlyProjectBudgetBean();
	//	for(QuarterlyProjectBudgetBean qpbBean : QuarterlyProjectBudgetTable.getQuarterlyProjectBudgetTable().getQpbBeanSet()){
		for(QuarterlyProjectBudgetBean qpbBean : qpaBeanSet){
			if( (qpbBean.getYear()==year) && (qpbBean.getQuarter()==quarter) && (qpbBean.getProjectNewBean().getProjectId()==projectNewBean.getProjectId()) ){
				quarterlyProjectBudgetBean = qpbBean;
			}
		}
		return quarterlyProjectBudgetBean;
	}

}
