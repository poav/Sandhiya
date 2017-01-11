package com.sapient.statestreetscreeningapplication.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;

public class CategoryMapper implements RowMapper<CategoryBean> {
	
	   public CategoryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CategoryBean categoryBean=new CategoryBean();
		   categoryBean.setCategoryId(rs.getLong("CATEGORY_ID"));
		   
		      return categoryBean;
		   }

	

}