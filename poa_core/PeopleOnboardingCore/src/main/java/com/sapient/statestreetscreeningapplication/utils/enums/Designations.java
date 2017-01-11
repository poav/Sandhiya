package com.sapient.statestreetscreeningapplication.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Designations {
	
	Associate("Associate"),
	
	SeniorAssociate("Senior Associate"),
	
	Manager("Manager"),
	
	SeniorManager("Senior Manager"),
	
	Director("Director"),
	
	VicePresident("Vice President");
	
	public String getDesignationName() {
		return designationName;
	}

	private final String designationName;
	
	Designations(String designationName)
	{
		this.designationName=designationName;
	}
	
	
	
	
	
	
	
	
	

}
