package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;


import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ScoreDao.
 */
@Component
public interface ScoreDao {
	
	/**
	 * Fetch scores.
	 *
	 * @param id the id
	 * @return the list
	 */
	public Set<ScoresNew> fetchScores(long id);
	
	public Set<ScoresNew> getScoreBean(String criteria,
			String categoryName, long personScreeningDetailsId);
	
	public boolean deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId);

}
