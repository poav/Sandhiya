package com.sapient.statestreetscreeningapplication.utils.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibatis.common.jdbc.ScriptRunner;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.PropertyUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Database.
 */
public class Database {

	/**
	 * Backup db.
	 *
	 * @return the string
	 */
	public static String BackupDb() {
		try {

			File f1 = new File(ConnectionFields.savePath);
			f1.mkdir();

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String fileName = "backup" + cal.get(Calendar.YEAR) + ""
					+ (cal.get(Calendar.MONTH) + 1) + ""
					+ cal.get(Calendar.DAY_OF_MONTH) + ".sql";

			/*String savePath = "\"" + ConnectionFields.savePath + "/" + fileName
					+ "\"";*/
			String savePath =  ConnectionFields.savePath + "/"+fileName ;
			CustomLoggerUtils.INSTANCE.log.info("Path were backup is saved: "
					+ savePath);
			String executeCmd = "mysqldump -u " + ConnectionFields.dbUsername
					+ " --password=" + ConnectionFields.dbPassword
					+ " --databases " + ConnectionFields.dbSchemaName + " -r "
					+ savePath;
			/*String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u " + ConnectionFields.dbUsername
					+ " --password=" + ConnectionFields.dbPassword
					+ " --database " + ConnectionFields.dbSchemaName + " -r "
					+ savePath;*/

			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				CustomLoggerUtils.INSTANCE.log.info("Backup Complete");
				return savePath;
			} else {
				CustomLoggerUtils.INSTANCE.log.info("Backup Failure");
			}

		} catch (IOException | InterruptedException ex) {
			CustomLoggerUtils.INSTANCE.log.error("Error at Backuprestore"
					+ ex.getMessage());
		}
		return null;
	}

	/**
	 * Restore db.
	 *
	 * @param dateOfFile the date of file
	 * @return the string
	 */
	public static String RestoreDb(Date dateOfFile) {

		Connection conn;
		String restorePath;
		String fileName = "";
		Calendar calendar ;

		try {
			calendar = Calendar.getInstance();
			calendar.setTime(dateOfFile);
			fileName = "backup" + calendar.get(Calendar.YEAR) + ""
					+ (calendar.get(Calendar.MONTH) + 1) + ""
					+ calendar.get(Calendar.DAY_OF_MONTH) + ".sql";

			restorePath = ConnectionFields.savePath + "\\" + fileName;
			if (Database.createDBStructure().equals("success")) {
				conn = DriverManager.getConnection(ConnectionFields.jdbcURL,
						ConnectionFields.dbUsername,
						ConnectionFields.dbPassword);
				ScriptRunner runner = new ScriptRunner(conn, false, false);

				CustomLoggerUtils.INSTANCE.log.info("Path\\filename: "
						+ restorePath);
				String result = Database.getDMLStatements(restorePath);
				if (!result.equals("error")) {
					InputStreamReader reader = new InputStreamReader(
							new FileInputStream(result));
					runner.runScript(reader);
					reader.close();
					conn.close();
					CustomLoggerUtils.INSTANCE.log.info("restore successful");
					return "success";
				}
			}
		} catch (FileNotFoundException e) {
			CustomLoggerUtils.INSTANCE.log
					.error("The file was not found. Filename: " + fileName);
			return "File not found";
		} catch (MySQLSyntaxErrorException e) {
			CustomLoggerUtils.INSTANCE.log
					.error("The given sql file has errors.");
			return "Error in SQL File";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CustomLoggerUtils.INSTANCE.log
				.info("Restoring Database threw an exception");
		return "error";
	}

	/**
	 * Creates the db structure.
	 *
	 * @return the string
	 */
	private static String createDBStructure() {
		Connection conn;
		try {

			conn = DriverManager.getConnection(ConnectionFields.jdbcURL,
					ConnectionFields.dbUsername, ConnectionFields.dbPassword);
			ScriptRunner runner = new ScriptRunner(conn, false, false);
			InputStreamReader reader = new InputStreamReader(Database.class
					.getClassLoader().getResourceAsStream("dbStructure.sql"));
			runner.runScript(reader);
			reader.close();
			conn.close();
			CustomLoggerUtils.INSTANCE.log.info("db structure created");
			return "success";
		} catch (FileNotFoundException e) {
			CustomLoggerUtils.INSTANCE.log.error("The file was not found.");
			return "File not found";
		} catch (MySQLSyntaxErrorException e) {
			CustomLoggerUtils.INSTANCE.log
					.error("The given sql file has errors.");
			return "Error in SQL File";
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CustomLoggerUtils.INSTANCE.log
				.info("Creating table structure threw an exception");
		return "error";
	}

	/**
	 * Execute script.
	 *
	 * @param path the path
	 * @return the string
	 */
	public static String executeScript(String path) {
		String result = Database.getDMLStatements(path);
		if (!result.equals("error")) {

			Connection conn;
			try {
				conn = DriverManager.getConnection(ConnectionFields.jdbcURL,
						ConnectionFields.dbUsername,
						ConnectionFields.dbPassword);
				ScriptRunner runner = new ScriptRunner(conn, false, false);
				InputStreamReader reader = new InputStreamReader(
						new FileInputStream(result));
				runner.runScript(reader);
				reader.close();
				conn.close();
				return "success";
			} catch (MySQLSyntaxErrorException e) {
				CustomLoggerUtils.INSTANCE.log
						.error("The given sql file has errors.");
				return "Error in SQL File";
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "Error";

	}

	/**
	 * Gets the DML statements.
	 *
	 * @param path the path
	 * @return the DML statements
	 */
	public static String getDMLStatements(String path) {
		BufferedReader br = null;
		List<String> queryList = new ArrayList<String>();
		String queryString = new String();
		try {
			br = new BufferedReader(new FileReader(path));

			StringBuilder sb = new StringBuilder();

			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					if (!line.matches("(--).*")) {
						sb.append(line);
					}
				}
				if (line != null)
					queryString = sb.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String[] stakeholderOracleIds = PropertyUtils.readProperty(
				"restore.defaultStakeholders").split(",");
		String[] statementArray = queryString.split(";");
		for (String statement : statementArray) {
			if (!statement.matches("(CREATE|DROP|USE).*")) {
				if (statement.matches("(INSERT INTO `stakeholder` VALUES).*")) {
					String valueSubString = statement.split("VALUES ")[1];
					String[] values = valueSubString.split(",(?![^()]*+\\))");
					for (String record : values) {
						for (String oracleId : stakeholderOracleIds) {
							if (record.contains(oracleId)) {
								statement = statement.replaceAll(record, "");
								statement = statement.replace("()", "");
								statement = statement.replace(",,", ",");
								statement = statement.replace("VALUES ,",
										"VALUES ");
							}
						}
					}
				}

				queryList.add(statement);
			}
		}

		try {

			StringBuilder sb = new StringBuilder();

			File file = new File("D://PeopleOnboarding3.0\\DMLdump\\DMLDump.sql");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (String s : queryList) {
				sb.append(s + ";").append(System.getProperty("line.separator"));

			}
			CustomLoggerUtils.INSTANCE.log.info(sb.toString());
			bw.write(sb.toString());
			bw.close();

			return file.getPath();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error";

	}

}
