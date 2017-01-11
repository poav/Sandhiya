package com.sapient.statestreetscreeningapplication.utils.database;

import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionFields.
 */
final class ConnectionFields {
	
	/** The Constant dbSchemaName. */
	static final String dbSchemaName = PropertyUtils
			.readProperty("database.schemaName");
	
	/** The Constant dbUsername. */
	static final String dbUsername = PropertyUtils
			.readProperty("database.username");
	
	/** The Constant dbPassword. */
	static final String dbPassword = PropertyUtils
			.readProperty("database.password");
	
	/** The Constant jdbcURL. */
	static final String jdbcURL = PropertyUtils
			.readProperty("database.jdbcURL");
	
	/** The Constant savePath. */
	static final String savePath = PropertyUtils
			.readProperty("database.backupSavePath");
}
