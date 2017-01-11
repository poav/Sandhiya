package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.statestreetscreeningapplication.ui.bean.EmailConfigBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface EmailConfigService.
 */
@Service
public interface EmailConfigService {
	
	/**
	 * Gets the all email config.
	 *
	 * @return the all email config
	 */
	List<EmailConfigBean> getAllEmailConfig();
	
	/**
	 * Gets the email content by id.
	 *
	 * @param id the id
	 * @return the email content by id
	 */
	String getEmailContentByID(long id);
	
	/**
	 * Update email config.
	 *
	 * @param id the id
	 * @param content the content
	 * @param comment the comment
	 */
	void updateEmailConfig(long id,String content,String comment);
	
}
