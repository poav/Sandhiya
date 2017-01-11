package com.sapient.statestreetscreeningapplication.utils.converter;

import java.util.ArrayList;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;

// TODO: Auto-generated Javadoc
/**
 * The Class PositionConverter.
 */
public class PositionConverter {

	/**
	 * Convert position entity to bean.
	 *
	 * @param entity the entity
	 * @return the position bean
	 */
	public static PositionBean convertPositionEntityToBean(Position entity) {
		PositionBean bean = new PositionBean();
		bean.setPositionId(entity.getPositionId());
		bean.setPositionName(entity.getPositionName());
		bean.setIsUsed(entity.getIsUsed());
		bean.setDomain(entity.getDomain());
		bean.setLevel(entity.getLevel());
		return bean;
	}

	/**
	 * Convert position bean to entity.
	 *
	 * @param bean the bean
	 * @return the position
	 */
	public static Position convertPositionBeanToEntity(PositionBean bean) {
		Position entity = new Position();
		entity.setPositionId(bean.getPositionId());
		entity.setPositionName(bean.getPositionName());
		entity.setIsUsed(bean.getIsUsed());
		entity.setLevel(bean.getLevel());
		entity.setDomain(bean.getDomain());
		return entity;
	}

	/**
	 * Convert position entity list to position bean list.
	 *
	 * @param allPosition the all position
	 * @return the list
	 */
	public static List<PositionBean> convertPositionEntityListToPositionBeanList(
			List<Position> allPosition) {
		List<PositionBean> beanList = new ArrayList<PositionBean>();
		for (Position positionEntity : allPosition) {
			beanList.add(PositionConverter
					.convertPositionEntityToBean(positionEntity));
		}
		return beanList;
	}
}
