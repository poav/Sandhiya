package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.ScoreBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface ScoreService.
 */
@Service
public interface ScoreService {
	
	/**
	 * Fetch scores.
	 *
	 * @param id the id
	 * @return the list
	 */
	public Set<ScoresNewBean> fetchScores(long id);
	
	public Set<ScoresNewBean> getScoreBean(String criteria, String categoryName,long personScreeningDetailsId);
	
	public boolean deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId);

}
