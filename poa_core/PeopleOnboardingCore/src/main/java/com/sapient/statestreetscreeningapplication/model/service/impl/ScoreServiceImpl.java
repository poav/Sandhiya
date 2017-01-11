package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.ScoreDao;
import com.sapient.statestreetscreeningapplication.model.dao.ScoresDaoJDBC;
import com.sapient.statestreetscreeningapplication.model.service.ScoreService;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.ScoreNewConvertor;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoreServiceImpl.
 */
@Service
public class ScoreServiceImpl implements ScoreService {

	/** The score dao. */
	@Autowired
	private ScoreDao scoreDao;
	
	@Autowired
	ScoresDaoJDBC scoreDaoJDBC;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.ScoreService#fetchScores(long)
	 */
	@Override
	@Transactional
	public Set<ScoresNewBean> fetchScores(long id) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchScores(long id) method of ScoreServiceImpl class");
		if (id > 0) {
			Set<ScoresNewBean> scoreList = ScoreNewConvertor.convertScoreListEntityToScoreListBean
					(scoreDao.fetchScores(id));
			return scoreList;
		}
		return null;
	}

	/**
	 * Gets the score dao.
	 *
	 * @return the score dao
	 */
	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	/**
	 * Sets the score dao.
	 *
	 * @param scoreDao the new score dao
	 */
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	/*@Override
	public Set<ScoresNewBean> getScoreBean(String criteria,String categoryName, long personScreeningDetailsId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getScoreBean(String criteria,String categoryName, long personScreeningDetailsId) method of ScoreServiceImpl class");
		return ScoreNewConvertor.convertScoreListEntityToScoreListBean(scoreDao.getScoreBean(criteria, categoryName, personScreeningDetailsId));
		
	}*/
	
	@Override
	public Set<ScoresNewBean> getScoreBean(String criteria,String categoryName, long personScreeningDetailsId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getScoreBean(String criteria,String categoryName, long personScreeningDetailsId) method of ScoreServiceImpl class");
		Set<ScoresNewBean> scoresNewBeanSet=new LinkedHashSet<>();
		scoresNewBeanSet.addAll(scoreDaoJDBC.getScoreBeanList(criteria, categoryName, personScreeningDetailsId));
		
		/*return ScoreNewConvertor.convertScoreListEntityToScoreListBean(scoreDao.getScoreBean(criteria, categoryName, personScreeningDetailsId));
		*/
		return scoresNewBeanSet;
		
	}

	@Override
	public boolean deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  deleteScoresRows(String criteria, String categoryName,long personScreeningDetailsId) method of ScoreServiceImpl class");
		return scoreDao.deleteScoresRows(criteria, categoryName, personScreeningDetailsId);
		
	}

}
