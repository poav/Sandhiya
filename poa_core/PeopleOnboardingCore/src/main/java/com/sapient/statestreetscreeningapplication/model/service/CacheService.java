package com.sapient.statestreetscreeningapplication.model.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sapient.statestreetscreeningapplication.ui.bean.CacheKey;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;

// TODO: Auto-generated Javadoc
/**
 * The Interface CacheService.
 */
public interface CacheService {
	
	/** The Constant MAX_ENTRIES. */
	final static int MAX_ENTRIES=Integer.valueOf(PropertyUtils.readProperty("activeDirectory.fetchCacheSize"));;
	
	/** The Constant CACHE_MAP. */
	final static Map<CacheKey, PersonBean> CACHE_MAP = Collections.synchronizedMap(new LinkedHashMap<CacheKey, PersonBean>(
			MAX_ENTRIES + 1, .75F, true) {
		private static final long serialVersionUID = 3852024021439951302L;

		@Override
		public boolean removeEldestEntry(Map.Entry<CacheKey, PersonBean> eldest) {
			return size() > MAX_ENTRIES;
		}
	});
}
