/*
 * 
 */
package com.sapient.statestreetscreeningapplication.virtual.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlyProjectActualBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;

// TODO: Auto-generated Javadoc
/**
 * The Class QuarterlyProjectActualDao.
 */
public class QuarterlyProjectActualDao {
	
	/**
	 * Save project quarterly cost.
	 *
	 * @param qpaBean the qpa bean
	 * @param qpaBeanSet the qpa bean set
	 * @return the sets the
	 */
	public static Set<QuarterlyProjectActualBean> saveProjectQuarterlyCost(QuarterlyProjectActualBean qpaBean, Set<QuarterlyProjectActualBean> qpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveProjectQuarterlyCost(QuarterlyProjectActualBean qpaBean) method of QuarterlyProjectActualDao class" );
	//	QuarterlyProjectActualTable.getQuarterlyProjectActualTable().getQpaBeanSet().add(qpaBean);
		qpaBeanSet.add(qpaBean);
		return qpaBeanSet;
	}
	
	
	/**
	 * Gets the quarterly project actual this year.
	 *
	 * @param year the year
	 * @param qpaBeanSet the qpa bean set
	 * @return the quarterly project actual this year
	 */
	public static List<QuarterlyProjectActualBean> getQuarterlyProjectActualThisYear(int year, Set<QuarterlyProjectActualBean> qpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getQuarterlyProjectActualThisYear(int year) method of QuarterlyProjectActualDao class" );
		List<QuarterlyProjectActualBean> qpaBeanListThisYear = new ArrayList<QuarterlyProjectActualBean>();
	//	for(QuarterlyProjectActualBean qpaBean : QuarterlyProjectActualTable.getQuarterlyProjectActualTable().getQpaBeanSet()){
		for(QuarterlyProjectActualBean qpaBean : qpaBeanSet){
			if( qpaBean.getYear()==year ){
			    qpaBeanListThisYear.add(qpaBean);
			}
		}
		return qpaBeanListThisYear;
	}
	
	
	/**
	 * Gets the quarterly project actual this quarter.
	 *
	 * @param quarter the quarter
	 * @param year the year
	 * @param qpaBeanSet the qpa bean set
	 * @return the quarterly project actual this quarter
	 */
	public static List<QuarterlyProjectActualBean> getQuarterlyProjectActualThisQuarter(Quarters quarter, int year, Set<QuarterlyProjectActualBean> qpaBeanSet) {
		CustomLoggerUtils.INSTANCE.log.info("inside getQuarterlyProjectActualThisQuarter(Quarters quarter, int year) method of QuarterlyProjectActualDao class" );
		List<QuarterlyProjectActualBean> qpaBeanListThisQuarter = new ArrayList<QuarterlyProjectActualBean>();
	//	for(QuarterlyProjectActualBean qpaBean : QuarterlyProjectActualTable.getQuarterlyProjectActualTable().getQpaBeanSet()){
		for(QuarterlyProjectActualBean qpaBean : qpaBeanSet){
			if( (qpaBean.getYear()==year) && (qpaBean.getQuarter()==quarter) ){
			     qpaBeanListThisQuarter.add(qpaBean);
			}
		}
		return qpaBeanListThisQuarter;
	}


}
