package com.sapient.statestreetscreeningapplication.ui.bean;

public class ActualSummaryBean {

	private float jan;

	private float feb;

	private float march;

	private float april;

	private float may;

	private float june;

	private float july;

	private float aug;

	private float sept;

	private float oct;

	private float nov;

	private float dec;
	
	private float q1;
	
	private float q2;
	
	private float q3;
	
	private float q4;

	public float getJan() {
		return jan;
	}

	public void setJan(float jan) {
		this.jan = jan;
	}

	public float getFeb() {
		return feb;
	}

	public void setFeb(float feb) {
		this.feb = feb;
	}

	public float getMarch() {
		return march;
	}

	public void setMarch(float march) {
		this.march = march;
	}

	public float getApril() {
		return april;
	}

	public void setApril(float april) {
		this.april = april;
	}

	public float getMay() {
		return may;
	}

	public void setMay(float may) {
		this.may = may;
	}

	public float getJune() {
		return june;
	}

	public void setJune(float june) {
		this.june = june;
	}

	public float getJuly() {
		return july;
	}

	public void setJuly(float july) {
		this.july = july;
	}

	public float getAug() {
		return aug;
	}

	public void setAug(float aug) {
		this.aug = aug;
	}

	public float getSept() {
		return sept;
	}

	public void setSept(float sept) {
		this.sept = sept;
	}

	public float getOct() {
		return oct;
	}

	public void setOct(float oct) {
		this.oct = oct;
	}

	public float getNov() {
		return nov;
	}

	public void setNov(float nov) {
		this.nov = nov;
	}

	public float getDec() {
		return dec;
	}

	public void setDec(float dec) {
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
	
	public void setQuarterlyProjectActual() {
		setQ1(jan + feb + march);
		setQ2(april + may + june);
		setQ3(july + aug + sept);
		setQ4(oct + nov + dec);
	}

}
