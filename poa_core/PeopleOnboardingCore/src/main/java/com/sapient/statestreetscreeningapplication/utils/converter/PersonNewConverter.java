package com.sapient.statestreetscreeningapplication.utils.converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.impl.PersonLookupServiceImplementation;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonNewConverter.
 */
public class PersonNewConverter {

	/**
	 * Person new entity to bean.
	 *
	 * @param personNew the person new
	 * @return the person new bean
	 */
	public static PersonNewBean personNewEntityToBean(Person personNew) {
		CustomLoggerUtils.INSTANCE.log.info("Inside personNewEntityToBean in PersonNewConverter");
		PersonNewBean personNewBean=new PersonNewBean();
		personNewBean.setPersonOracleId(personNew.getPersonOracleId());
		personNewBean.setBirthDay(personNew.getBirthDay());
		personNewBean.setBirthMonth(personNew.getBirthMonth());
		personNewBean.setHasExited(personNew.getHasExited());
		personNewBean.setIsActive(personNew.isActive());
		if(personNew.getLocation()!=null)
		personNewBean.setLocation(LocationNewConverter.locationEntityToBean(personNew.getLocation()));
		//personNewBean.setMachineName(personNew.getMachineName());
		personNewBean.setPersonClientId(personNew.getPersonClientId());
		personNewBean.setPersonId(personNew.getPersonId());
		personNewBean.setPersonNtId(personNew.getPersonNtId());
		if(personNew.getPosition()!=null)
		personNewBean.setPosition(PositionConverter.convertPositionEntityToBean(personNew.getPosition()));
		personNewBean.setRole(RoleNewConverter.roleEntityToBean(personNew.getRole()));
		personNewBean.setSubscribeDailyMail(personNew.getSubscribeDailyMail());
		personNewBean.setSubscribeImmediateMail(personNew.getSubscribeImmediateMail());
		personNewBean.setSupervisorId(personNew.getSupervisorId());
		personNewBean.setSupervisorNtId(personNew.getSupervisorNtId());
		personNewBean.setIsContractor(personNew.getIsContractor());
		personNewBean.setIsTemp(personNew.getIsTemp());
		if( personNew.getIsTemp()!=null && personNew.getIsTemp()){
			personNewBean.setTempPersonBean(TempPersonConverter.tempPersonEntityToBean(personNew.getTempPerson()));
		}else{
			personNewBean.setTempPersonBean(null);
		}
		CustomLoggerUtils.INSTANCE.log.info("Returning the following bean inside personNewEntityToBean in PersonNewConverter"+personNewBean);
		return personNewBean;
	}

	/**
	 * Person bean to entity.
	 *
	 * @param personNewBean the person new bean
	 * @return the person
	 */
	public static Person personBeanToEntity(
			PersonNewBean personNewBean) {
		CustomLoggerUtils.INSTANCE.log.info("Inside personBeanToEntity in PersonNewConverter");
	Person person =new Person();
	person.setPersonId(personNewBean.getPersonId());
	person.setActive(personNewBean.getIsActive());
	person.setBirthDay(personNewBean.getBirthDay());
	person.setBirthMonth(personNewBean.getBirthMonth());
	person.setHasExited(personNewBean.getHasExited());
	if(personNewBean.getLocation()!=null)
	person.setLocation(LocationNewConverter.locationBeanToEntity(personNewBean.getLocation()));
	//person.setMachineName(personNewBean.getMachineName());
	person.setPersonClientId(personNewBean.getPersonClientId());
	person.setPersonOracleId(personNewBean.getPersonOracleId());
	person.setPersonNtId(personNewBean.getPersonNtId());
	if(personNewBean.getPosition()!=null)
	person.setPosition(PositionConverter.convertPositionBeanToEntity(personNewBean.getPosition()));
	person.setRole(RoleNewConverter.roleBeanToEntitySet(personNewBean.getRole()));
	person.setSubscribeDailyMail(personNewBean.getSubscribeDailyMail());
	person.setSubscribeImmediateMail(personNewBean.getSubscribeImmediateMail());
	person.setSupervisorId(personNewBean.getSupervisorId());
	person.setSupervisorNtId(personNewBean.getSupervisorNtId());
	person.setIsContractor(personNewBean.getIsContractor());
	person.setIsTemp(personNewBean.getIsTemp());
	if((personNewBean.getIsTemp()!=null) && (personNewBean.getIsTemp()==true)){
		person.setTempPerson(TempPersonConverter.tempPersonBeanToEntity(personNewBean.getTempPersonBean()));
	}else{
		person.setTempPerson(null);
	}
	CustomLoggerUtils.INSTANCE.log.info("Returning the following entity inside personNewBeanToEntity in PersonNewConverter"+person);
		return person;
	}

}
