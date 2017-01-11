package com.sapient.statestreetscreeningapplication.utils.enums;

import java.util.ArrayList;
import java.util.List;

public enum RateType {
	
	MONTHLY;
	public static List<String> names() {
		RateType[] rateType = values();
	    List<String> names=new ArrayList<>();
		for(RateType rt:rateType){
			names.add(rt.name());
		}

	  

	    return names;
	}

}
