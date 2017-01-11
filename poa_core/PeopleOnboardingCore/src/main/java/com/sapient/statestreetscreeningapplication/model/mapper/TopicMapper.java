package com.sapient.statestreetscreeningapplication.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sapient.statestreetscreeningapplication.ui.bean.TopicBean;

public class TopicMapper implements RowMapper<TopicBean> {
	
	   public TopicBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		   TopicBean topicBean=new TopicBean();
		   topicBean.setTopicName(rs.getString("TOPIC_NAME"));
		   
		      return topicBean;
		   }
}