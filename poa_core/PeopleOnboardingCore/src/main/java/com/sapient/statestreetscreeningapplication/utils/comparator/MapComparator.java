package com.sapient.statestreetscreeningapplication.utils.comparator;

import java.util.Comparator;
import java.util.Map;

import com.sapient.statestreetscreeningapplication.model.entity.Person;

public class MapComparator implements Comparator<Person>{
	
	private Map<Person, Long> screenerMap;
   

	public MapComparator(Map<Person, Long> screenerMap) {
		super();
		this.screenerMap = screenerMap;
	}




	public MapComparator() {
		super();
	}
	
	


	@Override
	public int compare(Person screenerOne, Person screenerTwo) {
		if (screenerMap.get(screenerOne) >= screenerMap.get(screenerTwo)) {
            return -1;
        } else {
            return 1;
        } 
	}

}
