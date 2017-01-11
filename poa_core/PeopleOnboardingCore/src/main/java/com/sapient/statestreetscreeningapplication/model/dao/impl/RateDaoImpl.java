package com.sapient.statestreetscreeningapplication.model.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.RateDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.Rate;
import com.sapient.statestreetscreeningapplication.model.entity.RateLog;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.PersonNewConverter;

@Component
public class RateDaoImpl implements RateDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	PersonService personService;


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public List<Rate> viewAllUsedRate() {
		CustomLoggerUtils.INSTANCE.log.info("inside viewAllUsedRate()  method of RateDaoImpl class " );
		String query = "from Rate where isUsed=1";
		TypedQuery<Rate> p = entityManager.createQuery(query, Rate.class);
		CustomLoggerUtils.INSTANCE.log.info("retrieving the list with size "+p.getResultList().size());
		return p.getResultList();
	}


	@Override
	@Transactional
	public List<Rate> viewRate() {
		CustomLoggerUtils.INSTANCE.log.info("inside viewRate()  method of RateDaoImpl class " );
		String query = "from Rate";
		TypedQuery<Rate> p = entityManager.createQuery(query, Rate.class);
		CustomLoggerUtils.INSTANCE.log.info("retrieving the list with size "+p.getResultList().size());
		return p.getResultList();
		
	}
	
	@Override
	public Rate findRateByRateId(int rateId){
		
		Rate rate = entityManager.find(Rate.class,rateId);
		return rate;
	}


	@Override
	@Transactional
	public Rate editRate(int rateId, int rate) {
		CustomLoggerUtils.INSTANCE.log.info("inside editRate(int rateId, int rate, PersonNewBean currentUser)  method of RateDaoImpl class " );
		
		Rate rateObj=findRateByRateId(rateId);
		rateObj.setRate(rate);
		return entityManager.merge(rateObj);
	}


	@Override
	@Transactional
	public void saveRate(Rate rate,Person currentUser,RateLog rateLog) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveRate(Rate rate)  method of RateDaoImpl class " );
		
		
		
		Rate previousRate=entityManager.merge(rate);
		
		
		rateLog.setDate(new Date());
		
		rateLog.setPreviousRate(0);
		rateLog.setNewRate(previousRate.getRate());
		rateLog.setPerson(personService.getPersonByOracleId(currentUser.getPersonOracleId()));
		rateLog.setRate(previousRate);
		rateLog.setChangeInActivationStatus(false);
		entityManager.merge(rateLog);
		
	}


	@Override
	@Transactional
	public void editRateAdmin(int rateId, int newRate, Person currentUser,
			String rateIsActiveOrNot) {
		CustomLoggerUtils.INSTANCE.log.info("inside editRateAdmin(int rateId, int rate, PersonNewBean currentUser,String rateIsActiveOrNot)  method of RateDaoImpl class " );
		Rate previousRateObj=findRateByRateId(rateId);
		RateLog rateLog=new RateLog();
		
		rateLog.setDate(new Date());
		
		rateLog.setPreviousRate(previousRateObj.getRate());
		rateLog.setNewRate(newRate);
		rateLog.setPerson(personService.getPersonByOracleId(currentUser.getPersonOracleId()));
		rateLog.setRate(previousRateObj);
		boolean changeInActivationStatus;
		
		if(rateIsActiveOrNot.equalsIgnoreCase("true")){
			
			if(previousRateObj.isUsed()){
				changeInActivationStatus=false;
			}else{
				changeInActivationStatus=true;
			}
			previousRateObj.setUsed(true);

		}
		else
		{
			if(previousRateObj.isUsed()){
				changeInActivationStatus=true;
			}else{
				changeInActivationStatus=false;
			}
			previousRateObj.setUsed(false);

		}
		
			
			
		rateLog.setChangeInActivationStatus(changeInActivationStatus);
		
		if(changeInActivationStatus || previousRateObj.getRate()!=newRate){
			entityManager.merge(rateLog);
		}
		
		
		previousRateObj.setRate(newRate);
		
		
		entityManager.merge(previousRateObj);
		
	}


	@Override
	@Transactional
	public void enterChangesInRateLog(int newRate ,Person currentUser, int previousRateId,boolean changeInActivationStatus,RateLog rateLog){
		CustomLoggerUtils.INSTANCE.log.info("inside enterChangesInRateLog(Rate rateEntity, PersonNewBean currentUser, int previousRateId)  method of RateDaoImpl class " );
		
		Rate previousRate=findRateByRateId(previousRateId);
		
		
		rateLog.setDate(new Date());
		
		rateLog.setPreviousRate(previousRate.getRate());
		rateLog.setNewRate(newRate);
		rateLog.setPerson(personService.getPersonByOracleId(currentUser.getPersonOracleId()));
		rateLog.setRate(previousRate);
		rateLog.setChangeInActivationStatus(changeInActivationStatus);
		if(changeInActivationStatus || previousRate.getRate()!=newRate){
			entityManager.merge(rateLog);
		}
		
	}


	@Override
	@Transactional
	public Rate findRate(LocationNew location, Position position,Category category,String rateCategory,String rateType) {
		CustomLoggerUtils.INSTANCE.log.info("inside findRate(LocationNew location, Position position,Category category,String rateCategory,String rateType)  method of RateDaoImpl class " );
		String query = "from Rate where location=? and category=? and position=? and rateType=? and rateCategory=?";
		TypedQuery<Rate> p = entityManager.createQuery(query, Rate.class);
		p.setParameter(1, location);
		p.setParameter(2, category);
		p.setParameter(3, position);
		p.setParameter(4, rateType);
		p.setParameter(5, rateCategory);
		int size=p.getResultList().size();
		CustomLoggerUtils.INSTANCE.log.info("retrieving the list with size "+size);
		if(size==0){
			return null;
		}else{
			return p.getResultList().get(0);
		}
		
	}


	


	

}
