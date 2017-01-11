package com.sapient.statestreetscreeningapplication.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;

public class CriteriaMapper implements RowMapper<CriteriaBean> {
	
	   public CriteriaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CriteriaBean criteriaBean=new CriteriaBean();
		   criteriaBean.setCriteriaId(rs.getLong("CRITERIA_ID"));
		   
		      return criteriaBean;
		   }
}