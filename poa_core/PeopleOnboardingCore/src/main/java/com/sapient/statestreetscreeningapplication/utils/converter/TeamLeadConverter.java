package com.sapient.statestreetscreeningapplication.utils.converter;

import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;
import com.sapient.statestreetscreeningapplication.ui.bean.TeamLeadBean;

public class TeamLeadConverter {
	
	public static TeamLeadBean TeamLeadEntityToBean(TeamLeadEntity entity){
		TeamLeadBean bean = new TeamLeadBean();
		bean.setDefaultTeamLeadId(entity.getDefaultTeamLeadId());
		bean.setLead(PersonNewConverter.personNewEntityToBean(entity.getLead()));
		bean.setTeam(TeamConverter.convertTeamEntityToBean(entity.getTeam()));
		return bean;
	}
	
	public static TeamLeadEntity TeamLeadBeanToEntity(TeamLeadBean bean){
		TeamLeadEntity entity = new TeamLeadEntity();
		entity.setDefaultTeamLeadId(bean.getDefaultTeamLeadId());
		entity.setLead(PersonNewConverter.personBeanToEntity(bean.getLead()));
		entity.setTeam(TeamConverter.convertTeamBeanToEntity(bean.getTeam()));
		return entity;
	}

}
