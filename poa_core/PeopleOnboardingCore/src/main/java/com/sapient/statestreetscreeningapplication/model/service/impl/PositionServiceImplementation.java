package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.dao.PositionDao;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.service.PositionService;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PositionConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.DesignationLevels;
import com.sapient.statestreetscreeningapplication.utils.enums.Designations;

// TODO: Auto-generated Javadoc
/**
 * The Class PositionServiceImplementation.
 */
@Service
public class PositionServiceImplementation implements PositionService {

	/** The position dao. */
	@Autowired
	PositionDao positionDao;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#getPositionByName(java.lang.String)
	 */
	@Override
	public PositionBean getPositionByName(String positionName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPositionByName(String positionName) method of PositionServiceImplementation class");
		Position position = positionDao.getPosition(positionName);
		if (position != null)
			return PositionConverter.convertPositionEntityToBean(position);
		else
			// return new PositionBean();
			return null;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#getAllPositions()
	 */
	@Override
	public List<PositionBean> getAllPositions() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllPositions() method of PositionServiceImplementation class");
		List<Position> positionList = positionDao.getAllPositions();
		if (positionList != null) {
			return PositionConverter
					.convertPositionEntityListToPositionBeanList(positionList);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#savePositionDetails(com.sapient.statestreetscreeningapplication.ui.bean.PositionBean)
	 */
	@Override
	public boolean savePositionDetails(PositionBean position) {
		CustomLoggerUtils.INSTANCE.log.info("inside savePositionDetails(PositionBean position) method of PositionServiceImplementation class");
		if (position != null) {
			positionDao.savePositionDetails(PositionConverter
					.convertPositionBeanToEntity(position));
			return true;
		}
		return false;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#savePositionBatchDetails(java.io.File)
	 */
	@Override
	public void savePositionBatchDetails(File positionBatch) {
		CustomLoggerUtils.INSTANCE.log.info("inside savePositionBatchDetails(File positionBatch) method of PositionServiceImplementation class");
		// for csv file
		String csvFile = positionBatch.getPath();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<Position> positionList = new ArrayList<Position>();
		Position positionExisting;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] position = line.split(cvsSplitBy);
				positionExisting = positionDao.getPositionByNameDomainLevel(position[0],position[1],position[2]);
				if (positionExisting == null) {
					Position newPosition = new Position();
					newPosition.setPositionName(position[0]);
					newPosition.setDomain(position[1]);
					newPosition.setLevel(position[2]);
					positionList.add(newPosition);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		positionDao.savePositionBatch(positionList);
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#changeState(long, int)
	 */
	@Override
	public boolean changeState(long positionId, int state) {
		CustomLoggerUtils.INSTANCE.log.info("inside changeState(long positionId, int state) method of PositionServiceImplementation class");
		if (positionId > 0) {
			return positionDao.changeState(positionId, state);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PositionService#getAllUsedPositions()
	 */
	@Override
	public List<PositionBean> getAllUsedPositions() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositions() method of PositionServiceImplementation class");
		List<Position> positionList = positionDao.getAllUsedPositions();
		if (positionList != null) {
			return PositionConverter.convertPositionEntityListToPositionBeanList(positionList);
		} else {
			return null;
		}
	}
	@Override
	public List<String> getAllUsedPositionsByName() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositionsByName() method of PositionServiceImplementation class");
		List<String> positionList = positionDao.getAllUsedPositionsByName();
		if (positionList != null) {
			return positionList;
		} else {
			return null;
		}
	}

	/**
	 * Gets the position dao.
	 *
	 * @return the position dao
	 */
	public PositionDao getPositionDao() {
		return positionDao;
	}

	/**
	 * Sets the position dao.
	 *
	 * @param positionDao the new position dao
	 */
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Override
	public List<PositionBean> getAllUsedPositionByDomain(String domainName) {
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("inside getAllUsedPositionByDomain() method of PositionServiceImplementation class");
		List<Position> positionList = positionDao.getAllUsedPositionByDomain(domainName);
		if (positionList != null) {
			return PositionConverter
					.convertPositionEntityListToPositionBeanList(positionList);
		} else {
			return null;
		}
	}

	@Override
	public List<String> getDesignationNamesList()
	{
		Designations[] designationsList= Designations.values();
		List<String> designationNamesList= new ArrayList<>();

		for(Designations desig: Arrays.asList(designationsList))
		{
			designationNamesList.add(desig.getDesignationName());
		}

		return designationNamesList;

	}

	@Override
	public List<String> getDesignationLevelNamesList()
	{
		DesignationLevels[] designationsLevelList= DesignationLevels.values();
		List<String> designationLevelNamesList= new ArrayList<>();

		for(DesignationLevels desig: Arrays.asList(designationsLevelList))
		{
			designationLevelNamesList.add(desig.getDesignationLevel());
		}

		return designationLevelNamesList;

	}




}
