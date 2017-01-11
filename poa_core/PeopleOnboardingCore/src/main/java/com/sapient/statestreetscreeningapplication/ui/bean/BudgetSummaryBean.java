package com.sapient.statestreetscreeningapplication.ui.bean;

import com.sapient.statestreetscreeningapplication.utils.enums.Months;
import com.sapient.statestreetscreeningapplication.utils.enums.ProjectBudgetType;

public class BudgetSummaryBean {
	
	private MonthlyProjectBudgetBean jan;
	
	private MonthlyProjectBudgetBean feb;
	
	private MonthlyProjectBudgetBean march;
	
    private MonthlyProjectBudgetBean april;
	
	private MonthlyProjectBudgetBean may;
	
	private MonthlyProjectBudgetBean june;
	
    private MonthlyProjectBudgetBean july;
	
	private MonthlyProjectBudgetBean aug;
	
	private MonthlyProjectBudgetBean sept;
	
    private MonthlyProjectBudgetBean oct;
	
	private MonthlyProjectBudgetBean nov;
	
	private MonthlyProjectBudgetBean dec;
	
    public MonthlyProjectBudgetBean getJan() {
		return jan;
	}

	public void setJan(MonthlyProjectBudgetBean jan) {
		this.jan = jan;
	}

	public MonthlyProjectBudgetBean getFeb() {
		return feb;
	}

	public void setFeb(MonthlyProjectBudgetBean feb) {
		this.feb = feb;
	}

	public MonthlyProjectBudgetBean getMarch() {
		return march;
	}

	public void setMarch(MonthlyProjectBudgetBean march) {
		this.march = march;
	}

	public MonthlyProjectBudgetBean getApril() {
		return april;
	}

	public void setApril(MonthlyProjectBudgetBean april) {
		this.april = april;
	}

	public MonthlyProjectBudgetBean getMay() {
		return may;
	}

	public void setMay(MonthlyProjectBudgetBean may) {
		this.may = may;
	}

	public MonthlyProjectBudgetBean getJune() {
		return june;
	}

	public void setJune(MonthlyProjectBudgetBean june) {
		this.june = june;
	}

	public MonthlyProjectBudgetBean getJuly() {
		return july;
	}

	public void setJuly(MonthlyProjectBudgetBean july) {
		this.july = july;
	}

	public MonthlyProjectBudgetBean getAug() {
		return aug;
	}

	public void setAug(MonthlyProjectBudgetBean aug) {
		this.aug = aug;
	}

	public MonthlyProjectBudgetBean getSept() {
		return sept;
	}

	public void setSept(MonthlyProjectBudgetBean sept) {
		this.sept = sept;
	}

	public MonthlyProjectBudgetBean getOct() {
		return oct;
	}

	public void setOct(MonthlyProjectBudgetBean oct) {
		this.oct = oct;
	}

	public MonthlyProjectBudgetBean getNov() {
		return nov;
	}

	public void setNov(MonthlyProjectBudgetBean nov) {
		this.nov = nov;
	}

	public MonthlyProjectBudgetBean getDec() {
		return dec;
	}

	public void setDec(MonthlyProjectBudgetBean dec) {
		this.dec = dec;
	}

	public void setMonthlyProjectBudgetBean(MonthlyProjectBudgetBean mpbBean) {
		
		Months month = mpbBean.getMonth();
		
		switch (month) {
		
		case JAN:   setJan(mpbBean); 
		            break;
		case FEB:   setFeb(mpbBean);
	                break;
		case MARCH: setMarch(mpbBean);
	                break;
		case APRIL: setApril(mpbBean);
	                break;
	    case MAY:   setMay(mpbBean);
	                break;
	    case JUNE:  setJune(mpbBean);
	                break;
	    case JULY:  setJuly(mpbBean);
	                break;
	    case AUG:   setAug(mpbBean);
	                break;
	    case SEPT:  setSept(mpbBean);
	                break;
	    case OCT:   setOct(mpbBean);
	                break;
	    case NOV:   setNov(mpbBean);
	                break;
	    case DEC:   setDec(mpbBean);
	                break;
		default:    break;
		
		}
		
	}
    
    private float q1;
	
	private float q2;
	
	private float q3;
	
	private float q4;

	
    public float getQ1() {
		return q1;
	}

	public void setQ1(float q1) {
		this.q1 = q1;
	}

	public float getQ2() {
		return q2;
	}

	public void setQ2(float q2) {
		this.q2 = q2;
	}

	public float getQ3() {
		return q3;
	}

	public void setQ3(float q3) {
		this.q3 = q3;
	}

	public float getQ4() {
		return q4;
	}

	public void setQ4(float q4) {
		this.q4 = q4;
	}

	public void setQuarterlyProjectBudget() {
    	
    	float q01 = 0;
    	float q02 = 0;
    	float q03 = 0;
    	float q04 = 0;
    	
    	if(jan!=null){
    		q01 = q01 + jan.getProjectMonthlyBudgetValue(); 
    	}
    	else{
    		jan = new MonthlyProjectBudgetBean();
    		jan.setMonthlyProjectBudgetId(0L);
    		jan.setProjectMonthlyBudgetValue(0);
    		jan.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(feb!=null){
        	q01 = q01 + feb.getProjectMonthlyBudgetValue(); 
    	}
        else{
        	feb = new MonthlyProjectBudgetBean();
        	feb.setMonthlyProjectBudgetId(0L);
        	feb.setProjectMonthlyBudgetValue(0);
        	feb.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(march!=null){
        	q01 = q01 + march.getProjectMonthlyBudgetValue(); 
    	}
        else{
        	march = new MonthlyProjectBudgetBean();
        	march.setMonthlyProjectBudgetId(0L);
        	march.setProjectMonthlyBudgetValue(0);
        	march.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(april!=null){
        	q02 = q02 + april.getProjectMonthlyBudgetValue(); 
    	}
        else{
        	april = new MonthlyProjectBudgetBean();
        	april.setMonthlyProjectBudgetId(0L);
        	april.setProjectMonthlyBudgetValue(0);
        	april.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(may!=null){
        	q02 = q02 + may.getProjectMonthlyBudgetValue();
    	}
        else{
        	may = new MonthlyProjectBudgetBean();
        	may.setMonthlyProjectBudgetId(0L);
        	may.setProjectMonthlyBudgetValue(0);
        	may.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(june!=null){
        	q02 = q02 + june.getProjectMonthlyBudgetValue();
    	}
        else{
        	june = new MonthlyProjectBudgetBean();
        	june.setMonthlyProjectBudgetId(0L);
        	june.setProjectMonthlyBudgetValue(0);
        	june.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(july!=null){
        	q03 = q03 + july.getProjectMonthlyBudgetValue();
    	}
        else{
        	july = new MonthlyProjectBudgetBean();
        	july.setMonthlyProjectBudgetId(0L);
        	july.setProjectMonthlyBudgetValue(0);
        	july.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(aug!=null){
        	q03 = q03 + aug.getProjectMonthlyBudgetValue();
    	}
        else{
        	aug = new MonthlyProjectBudgetBean();
        	aug.setMonthlyProjectBudgetId(0L);
        	aug.setProjectMonthlyBudgetValue(0);
        	aug.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(sept!=null){
        	q03 = q03 + sept.getProjectMonthlyBudgetValue();
    	}
        else{
        	sept = new MonthlyProjectBudgetBean();
        	sept.setMonthlyProjectBudgetId(0L);
        	sept.setProjectMonthlyBudgetValue(0);
        	sept.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(oct!=null){
        	q04 = q04 + oct.getProjectMonthlyBudgetValue();
    	}
        else{
        	oct = new MonthlyProjectBudgetBean();
        	oct.setMonthlyProjectBudgetId(0L);
        	oct.setProjectMonthlyBudgetValue(0);
        	oct.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(nov!=null){
        	q04 = q04 + nov.getProjectMonthlyBudgetValue();
    	}
        else{
        	nov = new MonthlyProjectBudgetBean();
        	nov.setMonthlyProjectBudgetId(0L);
        	nov.setProjectMonthlyBudgetValue(0);
        	nov.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
        if(dec!=null){
        	q04 = q04 + dec.getProjectMonthlyBudgetValue();
    	}
        else{
        	dec = new MonthlyProjectBudgetBean();
        	dec.setMonthlyProjectBudgetId(0L);
        	dec.setProjectMonthlyBudgetValue(0);
        	dec.setProjectMonthlyBudgetType(ProjectBudgetType.Labour);
    	}
		
		setQ1(q01);
		setQ2(q02);
		setQ3(q03);
		setQ4(q04);
		
	}
  
}
