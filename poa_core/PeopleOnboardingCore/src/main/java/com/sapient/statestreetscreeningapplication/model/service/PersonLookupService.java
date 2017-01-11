package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
// TODO: Auto-generated Javadoc
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;

/**
 * The Interface PersonLookupService.
 */
@Service
public interface PersonLookupService {
	//Used by the autocompleter for interviewee in the Personlookup action
	/**
	 * Gets the person by name.
	 *
	 * @param name the name
	 * @return the person by name
	 */
	List<PersonBean> getPersonByName(String name);
	
	/**
	 * Gets the person by email.
	 *
	 * @param email the email
	 * @return the person by email
	 */
	PersonBean getPersonByEmail(String email);
	
	/**
	 * Gets the person by oracle id.
	 *
	 * @param oracleId the oracle id
	 * @return the person by oracle id
	 */
	PersonBean getPersonByOracleId(long oracleId);
	
	/**
	 * Gets the person by user name.
	 *
	 * @param intervieweeBean the interviewee bean
	 * @return the person by user name
	 */
	PersonBean getPersonByUserName(IntervieweeBean intervieweeBean);
	
	/**
	 * Gets the person by nt id.
	 *
	 * @param username the username
	 * @return the person by nt id
	 */
	PersonBean getPersonByNTId(String username);
	
	/**
	 * Gets the persons by oracle ids.
	 *
	 * @param oracleIdList the oracle id list
	 * @param fetchFromAd the fetch from ad
	 * @return the persons by oracle ids
	 */
	List<PersonBean> getPersonsByOracleIds(List<Integer> oracleIdList,
			boolean fetchFromAd);
	//List<PersonBean> fetchFromAd(String searchQuery, boolean updateCache);
	/**
	 * Gets the interviewers by name.
	 *
	 * @param name the name
	 * @param oracleIdList the oracle id list
	 * @return the interviewers by name
	 */
	List<PersonBean> getInterviewersByName(String name, List<Integer> oracleIdList);
	
	/**
	 * Fetch from ad.
	 *
	 * @param searchQuery the search query
	 * @param limit the limit
	 * @param updateCache the update cache
	 * @return the list
	 */
	List<PersonBean> fetchFromAd(String searchQuery, int limit,
			boolean updateCache);
	
	/**
	 * Gets the persons by oracle ids.
	 *
	 * @param oracleIdList the oracle id list
	 * @param searchKey the search key
	 * @param amount the amount
	 * @return the persons by oracle ids
	 */
	List<PersonBean> getPersonsByOracleIds(List<Integer> oracleIdList,
			String searchKey, int amount); //used for datatable name search
	
	/**
	 * Gets the person by name search.
	 *
	 * @param name the name
	 * @return the person by name search
	 */
	List<PersonBean> getPersonByNameSearch(String name);
	
	/**
	 * Fetch from ad search.
	 *
	 * @param searchQuery the search query
	 * @param limit the limit
	 * @return the list
	 */
	List<PersonBean> fetchFromAdSearch(String searchQuery, int limit);


	
}
