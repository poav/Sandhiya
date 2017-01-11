package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;


// TODO: Auto-generated Javadoc

/**
 * The Interface ScreeningTrackerDao.
 */
@Component

public interface ScreeningTrackerDao {

	/**
	 * Save screening summary.
	 *
	 * @param screeningSummaryList the screening summary list
	 */
	void saveScreeningSummary(List<Person> screeningSummaryList);

	/**
	 * Save scores batch.
	 *
	 * @param scoreList the score list
	 */
	
	void saveScoresBatch(List<ScoresNew> scoreList);

}
