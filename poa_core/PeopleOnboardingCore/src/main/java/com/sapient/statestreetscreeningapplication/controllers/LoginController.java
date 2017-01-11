package com.sapient.statestreetscreeningapplication.controllers;

import java.util.Hashtable;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.LoginService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PersonLookupService personLookupService;

	@CrossOrigin
	@RequestMapping(value="/loginValidation" , method=RequestMethod.POST)
	public PersonBean loginValidation(@RequestBody Map<String,String> credentials)
	{
		String username= credentials.get("username");
		String password= credentials.get("password");
		
		PersonNewBean personNewBean;
		
		CustomLoggerUtils.INSTANCE.log.info("validating username: "
					+ username);

			if (username == null || password == null) {
				//addActionError("Username / Password cannot be empty");
				CustomLoggerUtils.INSTANCE.log.error("username OR pwd is null");
			} else {
				if (username.trim().length() == 0 || password.trim().length() == 0) {
					//addActionError("Username / Password cannot be empty");
					CustomLoggerUtils.INSTANCE.log.error("username OR pwd null");
				} else {
					CustomLoggerUtils.INSTANCE.log.info("username and pwd are not null");

					personNewBean = loginService.validateAndGetUser(username);
					if (personNewBean == null){
						CustomLoggerUtils.INSTANCE.log.info("Username: "+username+ " do not have access to this application.");
						//addActionError("You do not have access to this application.");
						}
					else if (!personNewBean.getIsActive()) {
						CustomLoggerUtils.INSTANCE.log.info("Username: "+username+ " account has been deactivated.");
						//addActionError("Your account has been deactivated, Contact Admin for support ");
					} else {
						Hashtable<String, String> env = new Hashtable<String, String>();

						env.put(Context.INITIAL_CONTEXT_FACTORY,
								PropertyUtils
										.readProperty("activeDirectory.context.initialContextFactory"));
						env.put(Context.PROVIDER_URL,
								PropertyUtils
										.readProperty("activeDirectory.context.providerUrl"));
						env.put(Context.SECURITY_AUTHENTICATION, "simple");
						env.put(Context.SECURITY_PRINCIPAL, "Sapient\\" + username);
						env.put(Context.SECURITY_CREDENTIALS, password);

						try {
							new InitialDirContext(env);
							new InitialLdapContext(env, null);
							CustomLoggerUtils.INSTANCE.log
									.info("Connection succeeded!");
						} catch (AuthenticationException e) {
							CustomLoggerUtils.INSTANCE.log
									.info("Connection failed!");
							e.printStackTrace();
							personNewBean = null;
							//addActionError("Invalid username or password");
						} catch (NamingException e) {
							e.printStackTrace();
						}
					}

				}
			}
			
			CustomLoggerUtils.INSTANCE.log.info("validated user: "+ username);
			return personLookupService.getPersonByNTId(username);
		

	}

}
