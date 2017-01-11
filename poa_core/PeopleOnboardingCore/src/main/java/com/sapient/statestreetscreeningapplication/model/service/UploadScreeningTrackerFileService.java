package com.sapient.statestreetscreeningapplication.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Interface UploadScreeningTrackerFileService.
 */
@Service
public interface UploadScreeningTrackerFileService {

	/**
	 * Read file and save.
	 *
	 * @param path the path
	 * @return the int
	 */
	int readFileAndSave(String path);

	/**
	 * Generate error log.
	 *
	 * @return the list
	 */
	List<String> generateErrorLog();
}
