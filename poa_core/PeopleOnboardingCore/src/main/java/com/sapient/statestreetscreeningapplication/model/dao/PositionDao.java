package com.sapient.statestreetscreeningapplication.model.dao;

import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.Position;

// TODO: Auto-generated Javadoc
/**
 * The Interface PositionDao.
 */
public interface PositionDao {
	
	/**
	 * Gets the position.
	 *
	 * @param positionName the position name
	 * @return the position
	 */
	public Position getPosition(String positionName);

	/**
	 * Gets the all positions.
	 *
	 * @return the all positions
	 */
	public List<Position> getAllPositions();

	/**
	 * Save position details.
	 *
	 * @param position the position
	 */
	public void savePositionDetails(Position position);

	/**
	 * Save position batch.
	 *
	 * @param list the list
	 */
	public void savePositionBatch(List<Position> list);

	/**
	 * Change state.
	 *
	 * @param positionId the position id
	 * @param state the state
	 * @return true, if successful
	 */
	public boolean changeState(long positionId, int state);

	/**
	 * Gets the all used positions.
	 *
	 * @return the all used positions
	 */
	public List<Position> getAllUsedPositions();

	/**
	 * Gets the position by name domain level.
	 *
	 * @param positionName the position name
	 * @param domainName the domain name
	 * @param levelName the level name
	 * @return the position by name domain level
	 */
	public Position getPositionByNameDomainLevel(String positionName,
			String domainName, String levelName);

	/**
	 * Gets the all positions by name.
	 *
	 * @param positionName the position name
	 * @return the all positions by name
	 */
	public List<Position> getAllPositionsByName(String positionName);
	/**Gets the all used positions.
	 * 
	 * @return Position name
	 */

	public List<String> getAllUsedPositionsByName();
	/**
	 * Gets the all positions for given Domain.
	 *
	 * @param domainName the Domain name
	 * @return the all positions by name
	 */
	public List<Position> getAllUsedPositionByDomain(String domainName);
}
