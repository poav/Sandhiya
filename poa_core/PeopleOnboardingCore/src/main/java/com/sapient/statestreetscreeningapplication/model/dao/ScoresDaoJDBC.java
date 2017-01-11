package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;

@Repository
public interface ScoresDaoJDBC {
	
	public List<ScoresNewBean> getScoreBeanList(String criteria, String categoryName,
			long personScreeningDetailsId);
	

}
