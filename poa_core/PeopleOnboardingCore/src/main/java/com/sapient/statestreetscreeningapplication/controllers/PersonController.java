package com.sapient.statestreetscreeningapplication.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sapient.statestreetscreeningapplication.model.entity.Role;
import com.sapient.statestreetscreeningapplication.model.service.PersonService;
import com.sapient.statestreetscreeningapplication.model.service.RoleService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.RoleNewConverter;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private RoleService roleService;

	@CrossOrigin
	@RequestMapping("/displayAllUsers")
	public List<PersonNewBean> displayAllUsers()
	{
		return personService.getAllPersons();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public void deactivate(@RequestBody PersonNewBean personNewBean) {
		CustomLoggerUtils.INSTANCE.log
				.info("inside deactivate() method and in DeactivatePersonAction");
		personService.changePersonStatus(personNewBean.getPersonOracleId(), false);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestBody PersonNewBean personNewBean) {
		CustomLoggerUtils.INSTANCE.log
				.info("inside deleteUser() method and in DeletePersonAction");
		if (personNewBean != null) 
		personService.deletePerson(personNewBean.getPersonOracleId());
				
	}
	
	@CrossOrigin
	@RequestMapping(value = "/modifyUserRole", method = RequestMethod.POST)
	public void modifyRole(@RequestBody String str ) {
		CustomLoggerUtils.INSTANCE.log
				.info("inside modifyRole() method and in ModifyPersonRoleAction");
		Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String,Object> map=	gson.fromJson(str, Map.class);
			@SuppressWarnings("unchecked")
			List<String> userRolesList = (List<String>) map.get("userRolesList");
			Double currentUserOid=  (Double) map.get("currentUserOID");
			int currentUserOID = currentUserOid.intValue();
		if (userRolesList != null) 		
		{
			List<Role> userRoles = new ArrayList<Role>();
			for (String role : userRolesList) {
				/*
				 * stakeholder.setUserRole(UserRoles.addRole(
				 * stakeholder.getUserRole(), role));
				 */
				userRoles.add(RoleNewConverter.convertBeanToEntity(roleService
						.getRoleByName(role)));
			}
			
			personService.modifyPersonRole(currentUserOID,userRoles);
		}
		
			
		
	}

}
