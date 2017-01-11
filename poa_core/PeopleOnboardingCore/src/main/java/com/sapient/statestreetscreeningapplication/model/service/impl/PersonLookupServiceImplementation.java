package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.model.service.CacheService;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.ui.bean.CacheKey;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;

// TODO: Auto-generated Javadoc
//TODO CleanUp
/**
 * The Class PersonLookupServiceImplementation.
 */
@Service
public class PersonLookupServiceImplementation implements PersonLookupService,
		CacheService {
	
	/** The limit entries fetched. */
	private final int LIMIT_ENTRIES_FETCHED = Integer.valueOf(PropertyUtils
			.readProperty("activeDirectory.fetchRecordsLimit"));
	
	/** The ctx. */
	private static DirContext ctx;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonsByOracleIds(java.util.List, boolean)
	 */
	@Override
	public List<PersonBean> getPersonsByOracleIds(List<Integer> oracleIdList,boolean fetchFromAd) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonsByOracleIds(List<Integer> oracleIdList,boolean fetchFromAd) method of PersonLookupServiceImplementation class");
		List<PersonBean> personBeanList = new ArrayList<PersonBean>();
		String searchQuery = new String();
		CacheKey cacheKey;

		for (Integer oracleId : oracleIdList) {
			cacheKey = new CacheKey();
			CustomLoggerUtils.INSTANCE.log.info("\nFetching Oracle id:"
					+ oracleId);
			cacheKey.setOracleId(oracleId);
			cacheKey = getKey(cacheKey);
			if (cacheKey == null) {
				searchQuery = searchQuery.concat("(employeeid=" + oracleId
						+ ")");
			} else {
				personBeanList.add(CACHE_MAP.get(cacheKey));
			}
		}
		if (fetchFromAd) {
			CustomLoggerUtils.INSTANCE.log.info("Fetching from AD: (|"
					+ searchQuery + ")");
			personBeanList.addAll(fetchFromAd("(|" + searchQuery + ")",
					LIMIT_ENTRIES_FETCHED, true));
		}
		CustomLoggerUtils.INSTANCE.log
				.info("Fetched people: " + personBeanList);
		return personBeanList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonsByOracleIds(java.util.List, java.lang.String, int)
	 */
	@Override
	public List<PersonBean> getPersonsByOracleIds(List<Integer> oracleIdList,String searchKey, int amount) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonsByOracleIds(List<Integer> oracleIdList,String searchKey, int amount) method of PersonLookupServiceImplementation class");
		List<PersonBean> personBeanList = new ArrayList<PersonBean>();
		String searchQuery = new String();
		CacheKey cacheKey;

		for (Integer oracleId : oracleIdList) {
			cacheKey = new CacheKey();
			CustomLoggerUtils.INSTANCE.log.info("\nGetting Oracle id:"
					+ oracleId);
			cacheKey.setOracleId(oracleId);
			cacheKey = getKey(cacheKey);
			if (cacheKey == null) {
				searchQuery = searchQuery.concat("(employeeid=" + oracleId
						+ ")");
			} else {
				personBeanList.add(CACHE_MAP.get(cacheKey));
			}
		}
		searchQuery = "Fetch from Ad: (&(" + searchKey + "*)(|" + searchQuery
				+ "))";
		personBeanList.addAll(fetchFromAd("(|" + searchQuery + ")", amount,
				true));
		CustomLoggerUtils.INSTANCE.log.info("Persons retrieved: "
				+ personBeanList);
		return personBeanList;
	}

	// Caution: will return null if the person has exited the company
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByOracleId(long)
	 */
	@Override
	public PersonBean getPersonByOracleId(long oracleId) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getPersonByOracleId for OracleID: "+oracleId);
		List<PersonBean> personBeanList;	
		CacheKey cacheKey = new CacheKey();
		cacheKey.setOracleId(oracleId);
		cacheKey = getKey(cacheKey);
		PersonBean personBean = CACHE_MAP.get(cacheKey);
		CustomLoggerUtils.INSTANCE.log.info("Fetching single Oid: " + oracleId
				+ " contains key: " + cacheKey);
		
		if (personBean == null && cacheKey == null) {
			personBeanList = fetchFromAd("employeeid=" + oracleId,
					LIMIT_ENTRIES_FETCHED, true);
			if (personBeanList.isEmpty()) {
				CustomLoggerUtils.INSTANCE.log
						.warn("Record not found in the AD" + oracleId);
				personBean = null;
			} else {
				personBean = personBeanList.get(0);
			}

		}
		return personBean;
	}

	/**
	 * Gets the key.
	 *
	 * @param cacheKey the cache key
	 * @return the key
	 */
	public CacheKey getKey(CacheKey cacheKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getKey for CacheKey: "+cacheKey);
		CustomLoggerUtils.INSTANCE.log.info("in get cache key"
				+ CACHE_MAP.keySet().toString());
		for (CacheKey key : CACHE_MAP.keySet()) {
			if (cacheKey.equals(key)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * Gets the keys.
	 *
	 * @param cacheKey the cache key
	 * @return the keys
	 */
	public List<CacheKey> getKeys(CacheKey cacheKey) {
		CustomLoggerUtils.INSTANCE.log.info("inside  getKeys(CacheKey cacheKey)  method of PersonLookupServiceImplementation class");
		List<CacheKey> cacheKeyList = new ArrayList<CacheKey>();
		CustomLoggerUtils.INSTANCE.log.info("in get cache key"
				+ CACHE_MAP.keySet().toString());
		for (CacheKey key : CACHE_MAP.keySet()) {
			if (cacheKey.equals(key)) {
				cacheKeyList.add(key);
			}
		}
		return cacheKeyList;

	}

	/**
	 * Update cache key.
	 *
	 * @param personBean the person bean
	 * @return the cache key
	 */
	public CacheKey updateCacheKey(PersonBean personBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside  updateCacheKey for PersonBean: "+personBean );
		CacheKey cacheKey = new CacheKey();
		cacheKey.setOracleId(personBean.getOracleId());
		cacheKey.setPersonEmail(personBean.getEmail());
		cacheKey.setPersonName(personBean.getName());
		return cacheKey;
	}

	/**
	 * Gets the environment.
	 *
	 * @return the environment
	 */
	private Hashtable<String, String> getEnvironment() {
		CustomLoggerUtils.INSTANCE.log.info("inside getEnvironment()");
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.SECURITY_PRINCIPAL,
				PropertyUtils.readProperty("activeDirectory.securityPrincipal"));
		env.put(Context.SECURITY_CREDENTIALS, PropertyUtils
				.readProperty("activeDirectory.securityCredentials"));
		env.put(Context.INITIAL_CONTEXT_FACTORY, PropertyUtils
				.readProperty("activeDirectory.context.initialContextFactory"));
		env.put(Context.PROVIDER_URL, PropertyUtils
				.readProperty("activeDirectory.context.providerUrl"));
		return env;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByEmail(java.lang.String)
	 */
	@Override
	public PersonBean getPersonByEmail(String email) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByEmail: "+email);
		List<PersonBean> personBeanList;
		CacheKey cacheKey = new CacheKey();
		cacheKey.setPersonEmail(email);
		cacheKey = getKey(cacheKey);
		PersonBean personBean = CACHE_MAP.get(cacheKey);
		if (personBean == null && cacheKey == null) {
			personBeanList = fetchFromAd("mail=" + email,
					LIMIT_ENTRIES_FETCHED, true);
			if (personBeanList.isEmpty()) {
				CustomLoggerUtils.INSTANCE.log
						.info("Record not found in the AD when searching for: "
								+ email);
				personBean = null;
			} else {
				personBean = personBeanList.get(0);
			}

		}
		return personBean;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#fetchFromAd(java.lang.String, int, boolean)
	 */
	@Override
	public List<PersonBean> fetchFromAd(String searchQuery, int limit,boolean updateCache) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchFromAd searchQuery: "+searchQuery+" limit: "+limit+" updateCache: "+updateCache);
		NamingEnumeration<SearchResult> results;
		List<PersonBean> personList = new ArrayList<PersonBean>();
		PersonBean personBean;
		try {

			ctx = new InitialDirContext(getEnvironment());

			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setReturningAttributes(new String[] { "cn", "mail",
					"employeeid", "title","sAMAccountName" });
			controls.setCountLimit(limit);

			results = ctx.search("ou=Accounts", searchQuery, controls);
			while (results.hasMoreElements()) {
				personBean = new PersonBean();
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				try {personBean.setUsername(attributes.get("sAMAccountName").get().toString());
					personBean.setName(attributes.get("cn").get().toString());
					personBean
							.setEmail(attributes.get("mail").get().toString());
					personBean.setOracleId(Integer.parseInt(attributes
							.get("employeeid").get().toString()));
					personBean.setTitle(attributes.get("title").get()
							.toString());
					personList.add(personBean);
					if (updateCache) {
						CacheKey cacheKey = updateCacheKey(personBean);
						CACHE_MAP.put(cacheKey, personBean);
					}
				} catch (NullPointerException e) {
					CustomLoggerUtils.INSTANCE.log
							.error("Object missing attributes in AD. Search query:"
									+ searchQuery);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return personList;
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#fetchFromAdSearch(java.lang.String, int)
	 */
	@Override
	public List<PersonBean> fetchFromAdSearch(String searchQuery, int limit) {
		CustomLoggerUtils.INSTANCE.log.info("inside fetchFromAdSearch searchQuery: "+searchQuery+" limit: "+limit);
		NamingEnumeration<SearchResult> results;
		List<PersonBean> personList = new ArrayList<PersonBean>();
		PersonBean personBean;
		try {

			ctx = new InitialDirContext(getEnvironment());

			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setReturningAttributes(new String[] { "cn", "mail",
					"employeeid", "title","sAMAccountName" });
			controls.setCountLimit(limit);

			results = ctx.search("ou=Accounts", searchQuery, controls);
			while (results.hasMoreElements()) {
				personBean = new PersonBean();
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				try {personBean.setUsername(attributes.get("sAMAccountName").get().toString());
					personBean.setName(attributes.get("cn").get().toString());
					personBean
							.setEmail(attributes.get("mail").get().toString());
					personBean.setOracleId(Integer.parseInt(attributes
							.get("employeeid").get().toString()));
					personBean.setTitle(attributes.get("title").get()
							.toString());
					personList.add(personBean);
				} catch (NullPointerException e) {
					CustomLoggerUtils.INSTANCE.log
							.error("Object missing attributes in AD. Search query:"
									+ searchQuery);
				}
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return personList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByName(java.lang.String)
	 */
	@Override
	// Used for fetching intervieweeNames from AD directly. Cache storage is
	// disabled as people not belonging to the application will be fetched
	public List<PersonBean> getPersonByName(String name) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByName:"+name);
		List<PersonBean> personBeanList = new ArrayList<PersonBean>();
		personBeanList.addAll(fetchFromAd("cn=" + name + "*",
				LIMIT_ENTRIES_FETCHED, false));
		CustomLoggerUtils.INSTANCE.log
		.info("personBeanList FROM AD "+personBeanList);
		if (personBeanList.isEmpty()) {
			CustomLoggerUtils.INSTANCE.log
					.info("Searching non-users in the AD. Record not found: "
							+ name);
			return null;
		} else {
			return personBeanList;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByNameSearch(java.lang.String)
	 */
	@Override
	// Used for fetching intervieweeNames from AD directly. Cache storage is
	// disabled as people not belonging to the application will be fetched
	public List<PersonBean> getPersonByNameSearch(String name) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByNameSearch: "+name);
		List<PersonBean> personBeanList = new ArrayList<PersonBean>();
		personBeanList.addAll(fetchFromAdSearch("cn=" + name + "*",50));
		CustomLoggerUtils.INSTANCE.log
		.info("personBeanList FROM AD "+personBeanList);
		if (personBeanList.isEmpty()) {
			CustomLoggerUtils.INSTANCE.log
					.info("Searching non-users in the AD. Record not found: "
							+ name);
			return null;
		} else {
			return personBeanList;
		}
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByNTId(java.lang.String)
	 */
	@Override
	public PersonBean getPersonByNTId(String username) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByNTId(String username) method of PersonLookupServiceImplementation class");
		List<PersonBean> personBeanList;
		PersonBean personBean;
		CustomLoggerUtils.INSTANCE.log
				.info("NTID: not searching in cache. fetching from AD");
		personBeanList = fetchFromAd("sAMAccountName=" + username,
				LIMIT_ENTRIES_FETCHED, true);
		if (personBeanList.isEmpty()) {
			CustomLoggerUtils.INSTANCE.log
					.info("Record not found in the AD for: " + username);
			personBean = null;
		} else {
			personBean = personBeanList.get(0);
		}
		return personBean;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getInterviewersByName(java.lang.String, java.util.List)
	 */
	@Override
	public List<PersonBean> getInterviewersByName(String name,List<Integer> oracleIdList) {
		CustomLoggerUtils.INSTANCE.log.info("inside getInterviewersByName(String name,List<Integer> oracleIdList) method of PersonLookupServiceImplementation class");
		List<PersonBean> personBeanList = new ArrayList<PersonBean>();

		String searchQuery = new String();
		CacheKey cacheKey = new CacheKey();
		cacheKey.setPersonName(name);
		List<CacheKey> cacheKeyList = getKeys(cacheKey);
		for (CacheKey key : cacheKeyList) {
			personBeanList.add(CACHE_MAP.get(key));
		}
		for (Integer oracleId : oracleIdList) {
			cacheKey = new CacheKey();
			CustomLoggerUtils.INSTANCE.log
					.info("\nInterviewer Oid:" + oracleId);
			cacheKey.setOracleId(oracleId);
			cacheKey = getKey(cacheKey);
			if (cacheKey == null) {
				searchQuery = searchQuery.concat("(employeeid=" + oracleId
						+ ")");
			}
		}
		searchQuery = "(|" + searchQuery + ")";
		searchQuery = "(&(cn=" + name + "*)" + searchQuery + ")";
		CustomLoggerUtils.INSTANCE.log.info("Fetching by names from the AD: "
				+ searchQuery);
		personBeanList.addAll(fetchFromAd(searchQuery, LIMIT_ENTRIES_FETCHED,
				true));

		CustomLoggerUtils.INSTANCE.log.info("Interviewers retrieved: "
				+ personBeanList);
		return personBeanList;
	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.PersonLookupService#getPersonByUserName(com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean)
	 */
	@Override
	public PersonBean getPersonByUserName(IntervieweeBean intervieweeBean) {
		CustomLoggerUtils.INSTANCE.log.info("inside getPersonByUserName(IntervieweeBean intervieweeBean) method of PersonLookupServiceImplementation class");
		List<PersonBean> personBeanList;
		long oracleId=intervieweeBean.getIntervieweeId();
		String userName=intervieweeBean.getIntervieweeUserName();
		CacheKey cacheKey = new CacheKey();
		cacheKey.setOracleId(intervieweeBean.getIntervieweeOracleID());
		
		cacheKey = getKey(cacheKey);
		PersonBean personBean = CACHE_MAP.get(cacheKey);
		/*CustomLoggerUtils.INSTANCE.log.info("Fetching single Oid: " + oracleId
				+ " contains key: " + cacheKey);*/
		if (personBean == null && cacheKey == null  ) {
			personBeanList = fetchFromAd("sAMAccountName=" + userName,
					LIMIT_ENTRIES_FETCHED, true);
			if (personBeanList.isEmpty()) {
				CustomLoggerUtils.INSTANCE.log
						.warn("Record not found in the AD" + oracleId);
				personBean = null;
			} else {
				personBean = personBeanList.get(0);
			}

		}
		return personBean;
	}

	

	
}
