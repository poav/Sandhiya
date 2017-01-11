package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.List;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sapient.statestreetscreeningapplication.model.dao.ScoresDaoJDBC;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.mapper.CategoryMapper;
import com.sapient.statestreetscreeningapplication.model.mapper.CriteriaMapper;
import com.sapient.statestreetscreeningapplication.model.mapper.ScoreMapper;
import com.sapient.statestreetscreeningapplication.model.mapper.TopicMapper;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;


@Repository
public class ScoresDaoJDBCImpl implements ScoresDaoJDBC {
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	 public void setDataSource(DataSource dataSource) {
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	 }

	@Override
	public List<ScoresNewBean> getScoreBeanList(String criteria,
			String categoryName, long personScreeningDetailsId) {
		
		String SQL = "select * from CATEGORY where CATEGORY_NAME=?";
		
		CategoryBean categoryBean=jdbcTemplateObject.queryForObject(SQL, 
                new Object[]{categoryName}, new CategoryMapper());
		
		String SQL1 = "select * from CRITERIA where CATEGORY_ID="+categoryBean.getCategoryId()+" and CRITERIA_NAME='"+criteria+"'";
		
		 List <CriteriaBean> criteriaObj = jdbcTemplateObject.query(SQL1, 
                 new CriteriaMapper());
		
		 String SQL2 = "select * from SCORES_NEW where PERSON_SCREENING_ID="+personScreeningDetailsId+" and CRITERIA_ID="+criteriaObj.get(0).getCriteriaId();
			
		 List <ScoresNewBean> scoreList = jdbcTemplateObject.query(SQL2, 
                 new ScoreMapper());
		 
		 for(ScoresNewBean score:scoreList){

				String SQL3 = "select * from TOPICS where TOPIC_ID=?";
				
				TopicBean topicBean=jdbcTemplateObject.queryForObject(SQL3, 
		                new Object[]{score.getTopicBean().getTopicId()}, new TopicMapper());
				score.getTopicBean().setTopicName(topicBean.getTopicName());
				
		 }
		return scoreList;
	}

}
