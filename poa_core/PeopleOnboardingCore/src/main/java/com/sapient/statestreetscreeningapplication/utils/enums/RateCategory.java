package com.sapient.statestreetscreeningapplication.utils.enums;

import java.util.ArrayList;
import java.util.List;


public enum RateCategory {
	Offshore,
	
	Onshore;
	
	public static List<String> names() {
		RateCategory[] rateCategory = values();
	    List<String> names=new ArrayList<>();
		for(RateCategory rc:rateCategory){
			names.add(rc.name());
		}

	  

	    return names;
	}

}
