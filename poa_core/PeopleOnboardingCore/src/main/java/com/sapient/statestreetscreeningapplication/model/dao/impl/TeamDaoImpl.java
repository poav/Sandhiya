package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.TeamDao;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.Team;
import com.sapient.statestreetscreeningapplication.model.entity.TeamLeadEntity;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TeamDaoImpl.
 */
@Component
public class TeamDaoImpl implements TeamDao {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TeamDao#saveTeam(com.sapient.statestreetscreeningapplication.model.entity.Team)
	 */
	@Override
	@Transactional
	public boolean saveTeam(Team team) {
		CustomLoggerUtils.INSTANCE.log.info("inside  saveTeam(Team team) method of TeamDaoImpl ");
		team.setIsActive(true);

		boolean isDuplicate;
		String query = "from Team";
		TypedQuery<Team> p = entityManager.createQuery(query, Team.class);
		isDuplicate = false;
		for (Team entity : p.getResultList()) {
			if (entity.getTeamName().equalsIgnoreCase(team.getTeamName())) {
				isDuplicate = true;
			}
		}
		if (isDuplicate) {
			CustomLoggerUtils.INSTANCE.log
					.info("The team name already exists in the database");
			return false;
		} else {
			CustomLoggerUtils.INSTANCE.log.info("saving the team"
					+ team.getTeamName());
			entityManager.merge(team);
			return true;
		}

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TeamDao#getAllTeamList()
	 */
	@Override
	@Transactional
	public List<Team> getAllTeamList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTeamList()  method of TeamDaoImpl ");
		List<Team> allTeamList = null;
		String query = "from Team";
		TypedQuery<Team> p = entityManager.createQuery(query, Team.class);
		try {

			allTeamList = p.getResultList();

			CustomLoggerUtils.INSTANCE.log
					.info("The size of the list obtained is"
							+ allTeamList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.info("allTeamList List is null");
			return null;
		}

		return allTeamList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TeamDao#editTeam(com.sapient.statestreetscreeningapplication.model.entity.Team)
	 */
	@Override
	@Transactional
	public void editTeam(Team team) {
		CustomLoggerUtils.INSTANCE.log.info("inside editTeam(Team team)  method of TeamDaoImpl ");
		Team teamRetreived;
		String query = "from Team where teamId =:teamId";
		TypedQuery<Team> p = entityManager.createQuery(query, Team.class);
		p.setParameter("teamId", team.getTeamId());
		try {
			teamRetreived = p.getSingleResult();
			teamRetreived.setTeamName(team.getTeamName());
			teamRetreived.setIsActive(team.getIsActive());
			entityManager.merge(teamRetreived);
		} catch (Exception e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.error("Single result not found");
		}

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TeamDao#getAllTeamNameList()
	 */
	@Override
	@Transactional
	public List<String> getAllTeamNameList() {
		CustomLoggerUtils.INSTANCE.log.info("inside getAllTeamNameList()  method of TeamDaoImpl ");
		List<String> allTeamNameList = new ArrayList<String>();

		String query = "select teamName from Team where isActive =:isActive";
		TypedQuery<String> p = entityManager.createQuery(query, String.class);
		p.setParameter("isActive", true);
		try {

			allTeamNameList = p.getResultList();

			CustomLoggerUtils.INSTANCE.log
					.info("The size of the list obtained is"
							+ allTeamNameList.size());
		} catch (NoResultException e) {
			e.printStackTrace();
			CustomLoggerUtils.INSTANCE.log.info("allTeamList List is null");
			return null;
		}

		return allTeamNameList;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.dao.TeamDao#getTeamByName(java.lang.String)
	 */
	@Override
	public Team getTeamByName(String teamName) {
		CustomLoggerUtils.INSTANCE.log.info("inside getTeamByName(String teamName)  method of TeamDaoImpl ");
		String query = "from Team where teamName =:teamName";
		TypedQuery<Team> p = entityManager.createQuery(query, Team.class);
		p.setParameter("teamName", teamName);
		Team team;
		try{
		team = p.getSingleResult();
		}catch(NoResultException e){
			CustomLoggerUtils.INSTANCE.log.info("Team "+teamName+" does not exist");
			team = null;
		}
		return team;
	}

	@Override
	@Transactional
	public TeamLeadEntity mergeTeamLead(TeamLeadEntity teamleadEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside mergeTeamLead(TeamLeadEntity teamleadEntity)  method of TeamDaoImpl ");
		String q1 = "from Person where personOracleId=:personOracleId";
		TypedQuery<Person> personTQ = entityManager.createQuery(q1,Person.class);
		personTQ.setParameter("personOracleId",teamleadEntity.getLead().getPersonOracleId());
		List<Person> personList = personTQ.getResultList();
		Person lead = new Person();
		if(personList.size()>0){
			lead = personList.get(0);
		}
		if(personList.size()==0){
			lead = entityManager.merge(teamleadEntity.getLead());
		}
		teamleadEntity.setLead(lead);
		
		String q2 = "from TeamLeadEntity where lead=:lead and team=:team";
		TypedQuery<TeamLeadEntity> TeamLeadTQ = entityManager.createQuery(q2,TeamLeadEntity.class);
		TeamLeadTQ.setParameter("lead",teamleadEntity.getLead());
		TeamLeadTQ.setParameter("team",teamleadEntity.getTeam());
		List<TeamLeadEntity> TeamLeadList = TeamLeadTQ.getResultList();
		if(TeamLeadList.size()==0){
			teamleadEntity = entityManager.merge(teamleadEntity);
		}
		if(TeamLeadList.size()==1){
			teamleadEntity = TeamLeadList.get(0);
		}
		if(TeamLeadList.size()>1){
			for(TeamLeadEntity entity : TeamLeadList ){
				entityManager.remove(entity);
			}
			teamleadEntity = entityManager.merge(teamleadEntity);
		}
		return teamleadEntity;
	}

	@Override
	public List<TeamLeadEntity> getTeamLeadByTeam(Team team) {
		CustomLoggerUtils.INSTANCE.log.info("inside getTeamLeadByTeam(Team team)  method of TeamDaoImpl ");
		Team taem = entityManager.find(Team.class, team.getTeamId());
		String q2 = "from TeamLeadEntity where team=:team";
		TypedQuery<TeamLeadEntity> TeamLeadTQ = entityManager.createQuery(q2,TeamLeadEntity.class);
		TeamLeadTQ.setParameter("team",taem);
		List<TeamLeadEntity> TeamLeadList = TeamLeadTQ.getResultList();
		return TeamLeadList;
	}

	@Override
	@Transactional
	public Boolean removeTeamLead(TeamLeadEntity teamleadEntity) {
		CustomLoggerUtils.INSTANCE.log.info("inside removeTeamLead(TeamLeadEntity teamleadEntity)  method of TeamDaoImpl ");
		boolean flag = false;
		TeamLeadEntity teamlead = entityManager.find(TeamLeadEntity.class, teamleadEntity.getDefaultTeamLeadId());
		if(teamlead!=null){
		   entityManager.remove(teamlead);
		   flag = true;
		}
		return flag;
	}

	@Override
	public int getCountOfteamName(String teamName) {
		// TODO Auto-generated method stub
		CustomLoggerUtils.INSTANCE.log.info("inside getTeamLeadByTeam(Team team)  method of TeamDaoImpl ");
		
		String q2 = "from Team where teamName=:teamName";
		TypedQuery<Team> TeamList = entityManager.createQuery(q2,Team.class);
		TeamList.setParameter("teamName",teamName);
		List<Team> TeamEntityList = TeamList.getResultList();
		return TeamEntityList.size();
	
	}

	@Override
	public Team getTeamForTeamName(String teamName) {
		// TODO Auto-generated method stub
		String q2 = "from Team where teamName=:teamName";
		TypedQuery<Team> TeamList = entityManager.createQuery(q2,Team.class);
		TeamList.setParameter("teamName",teamName);
		Team team = TeamList.getSingleResult();
		return team;
	}
}
