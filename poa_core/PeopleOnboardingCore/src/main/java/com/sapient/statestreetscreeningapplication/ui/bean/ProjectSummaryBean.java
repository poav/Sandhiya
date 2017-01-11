package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Months;

public class ProjectSummaryBean {
	
	private PersonStaffingBean personStaffingBean;
	
	private StaffingMonthlyCostBean jan;
	
	private StaffingMonthlyCostBean feb;
	
	private StaffingMonthlyCostBean march;
	
    private StaffingMonthlyCostBean april;
	
	private StaffingMonthlyCostBean may;
	
	private StaffingMonthlyCostBean june;
	
    private StaffingMonthlyCostBean july;
	
	private StaffingMonthlyCostBean aug;
	
	private StaffingMonthlyCostBean sept;
	
    private StaffingMonthlyCostBean oct;
	
	private StaffingMonthlyCostBean nov;
	
	private StaffingMonthlyCostBean dec;
	
	private float q1;
	
	private float q2;
	
	private float q3;
	
	private float q4;

	public PersonStaffingBean getPersonStaffingBean() {
		return personStaffingBean;
	}

	public void setPersonStaffingBean(PersonStaffingBean personStaffingBean) {
		this.personStaffingBean = personStaffingBean;
	}

	public StaffingMonthlyCostBean getJan() {
		return jan;
	}

	private void setJan(StaffingMonthlyCostBean jan) {
		this.jan = jan;
	}

	public StaffingMonthlyCostBean getFeb() {
		return feb;
	}

	private void setFeb(StaffingMonthlyCostBean feb) {
		this.feb = feb;
	}

	public StaffingMonthlyCostBean getMarch() {
		return march;
	}

	private void setMarch(StaffingMonthlyCostBean march) {
		this.march = march;
	}

	public StaffingMonthlyCostBean getApril() {
		return april;
	}

	private void setApril(StaffingMonthlyCostBean april) {
		this.april = april;
	}

	public StaffingMonthlyCostBean getMay() {
		return may;
	}

	private void setMay(StaffingMonthlyCostBean may) {
		this.may = may;
	}

	public StaffingMonthlyCostBean getJune() {
		return june;
	}

	private void setJune(StaffingMonthlyCostBean june) {
		this.june = june;
	}

	public StaffingMonthlyCostBean getJuly() {
		return july;
	}

	private void setJuly(StaffingMonthlyCostBean july) {
		this.july = july;
	}

	public StaffingMonthlyCostBean getAug() {
		return aug;
	}

	private void setAug(StaffingMonthlyCostBean aug) {
		this.aug = aug;
	}

	public StaffingMonthlyCostBean getSept() {
		return sept;
	}

	private void setSept(StaffingMonthlyCostBean sept) {
		this.sept = sept;
	}

	public StaffingMonthlyCostBean getOct() {
		return oct;
	}

	private void setOct(StaffingMonthlyCostBean oct) {
		this.oct = oct;
	}

	public StaffingMonthlyCostBean getNov() {
		return nov;
	}

	private void setNov(StaffingMonthlyCostBean nov) {
		this.nov = nov;
	}

	public StaffingMonthlyCostBean getDec() {
		return dec;
	}

	private void setDec(StaffingMonthlyCostBean dec) {
		this.dec = dec;
	}

	public float getQ1() {
		return q1;
	}

	private void setQ1(float q1) {
		this.q1 = q1;
	}

	public float getQ2() {
		return q2;
	}

	private void setQ2(float q2) {
		this.q2 = q2;
	}

	public float getQ3() {
		return q3;
	}

	private void setQ3(float q3) {
		this.q3 = q3;
	}

	public float getQ4() {
		return q4;
	}

	private void setQ4(float q4) {
		this.q4 = q4;
	}
	
	public void setStaffingMonthlyCostBean(StaffingMonthlyCostBean smcBean){
		
		Months month = smcBean.getMonth();
		
		switch (month) {
		
		case JAN:   setJan(smcBean); 
		            break;
		case FEB:   setFeb(smcBean);
	                break;
		case MARCH: setMarch(smcBean);
	                break;
		case APRIL: setApril(smcBean);
	                break;
	    case MAY:   setMay(smcBean);
	                break;
	    case JUNE:  setJune(smcBean);
	                break;
	    case JULY:  setJuly(smcBean);
	                break;
	    case AUG:   setAug(smcBean);
	                break;
	    case SEPT:  setSept(smcBean);
	                break;
	    case OCT:   setOct(smcBean);
	                break;
	    case NOV:   setNov(smcBean);
	                break;
	    case DEC:   setDec(smcBean);
	                break;
		default:    break;
		
		}
		
	}
	
	public void setStaffingQuarterlyCost(){
		
		setQ1(jan.getIndividualMonthlyCost()+feb.getIndividualMonthlyCost()+march.getIndividualMonthlyCost());
		setQ2(april.getIndividualMonthlyCost()+may.getIndividualMonthlyCost()+june.getIndividualMonthlyCost());
		setQ3(july.getIndividualMonthlyCost()+aug.getIndividualMonthlyCost()+sept.getIndividualMonthlyCost());
		setQ4(oct.getIndividualMonthlyCost()+nov.getIndividualMonthlyCost()+dec.getIndividualMonthlyCost());
		
	}
	
	
	
	

}
