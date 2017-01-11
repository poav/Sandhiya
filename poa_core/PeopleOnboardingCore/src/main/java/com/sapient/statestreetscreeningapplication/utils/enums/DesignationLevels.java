package com.sapient.statestreetscreeningapplication.utils.enums;

public enum DesignationLevels {
	
	L1("L1"),
	
	L2("L2");
	
	private final String designationLevel;
	
	DesignationLevels(String designationLevel)
	{
		this.designationLevel=designationLevel;
	}

	public String getDesignationLevel() {
		return designationLevel;
	}
	
	
	
	

}
