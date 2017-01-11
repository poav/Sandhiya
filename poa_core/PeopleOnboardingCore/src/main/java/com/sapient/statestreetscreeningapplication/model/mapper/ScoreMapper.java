package com.sapient.statestreetscreeningapplication.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

public class ScoreMapper implements RowMapper<ScoresNewBean> {
	
	   public ScoresNewBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		   TopicBean topicBean=new TopicBean();
		   ScoresNewBean scoresNewBean = new ScoresNewBean();
		   scoresNewBean.setScoreId(rs.getLong("SCORE_ID"));
		   scoresNewBean.setComments(rs.getString("COMMENT"));
		   scoresNewBean.setIsChecked(rs.getInt("IS_CHECKED"));
		   scoresNewBean.setScore(rs.getDouble("SCORE"));
		   topicBean.setTopicId(rs.getLong("TOPIC_ID"));
		   scoresNewBean.setTopicBean(topicBean);
		      return scoresNewBean;
		   }

	

}
