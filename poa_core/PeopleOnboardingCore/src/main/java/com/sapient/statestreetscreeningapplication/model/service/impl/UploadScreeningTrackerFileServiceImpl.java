package com.sapient.statestreetscreeningapplication.model.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.statestreetscreeningapplication.model.dao.CategoryDao;
import com.sapient.statestreetscreeningapplication.model.dao.IntervieweeDao;
import com.sapient.statestreetscreeningapplication.model.dao.LocationDao;
import com.sapient.statestreetscreeningapplication.model.dao.PositionDao;
import com.sapient.statestreetscreeningapplication.model.dao.ProjectDao;
import com.sapient.statestreetscreeningapplication.model.dao.ScreeningTrackerDao;
import com.sapient.statestreetscreeningapplication.model.dao.StatusDao;
import com.sapient.statestreetscreeningapplication.model.dao.TopicsDao;
import com.sapient.statestreetscreeningapplication.model.entity.Category;
import com.sapient.statestreetscreeningapplication.model.entity.LocationNew;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.PersonScreeningDetails;
import com.sapient.statestreetscreeningapplication.model.entity.Position;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.entity.ScoresNew;
import com.sapient.statestreetscreeningapplication.model.entity.Status;
import com.sapient.statestreetscreeningapplication.model.entity.Topics;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.UploadScreeningTrackerFileService;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class UploadScreeningTrackerFileServiceImpl.
 */
@Service
public class UploadScreeningTrackerFileServiceImpl implements
		UploadScreeningTrackerFileService {

	/** The interviewee dao. */
	@Autowired
	private IntervieweeDao intervieweeDao;

	/** The screening tracker. */
	@Autowired
	private ScreeningTrackerDao screeningTracker;

	/*@Autowired
	private StakeholderDao stakeholderDao;*/

	/** The position dao. */
	@Autowired
	private PositionDao positionDao;

	/** The category dao. */
	@Autowired
	private CategoryDao categoryDao;
	
	/** The location dao. */
	@Autowired
	private LocationDao locationDao;

	/** The status dao. */
	@Autowired
	private StatusDao statusDao;

	/** The project dao. */
	@Autowired
	private ProjectDao projectDao;

	/** The topic dao. */
	@Autowired
	private TopicsDao topicDao;

	/** The person lookup service. */
	@Autowired
	private PersonLookupService personLookupService;

	/** The error log. */
	private List<String> errorLog;

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.UploadScreeningTrackerFileService#readFileAndSave(java.lang.String)
	 */
	@Override
	@Transactional
	public int readFileAndSave(String path) {
		CustomLoggerUtils.INSTANCE.log.info("inside readFileAndSave(String path) method of UploadScreeningTrackerFileServiceImpl class");
		int intervieweeResult = 0;
		int scoreResult = 0;
		int saveIntervieweeBatchResult = saveIntervieweeBatch(path);
		int saveScoreBatchResult = saveScoreBatch(path);
		switch (saveIntervieweeBatchResult) {
		case 0:
			intervieweeResult = 0;
			break;
		case 1:
			intervieweeResult = 1;
			break;
		case 2:
			intervieweeResult = 2;
			break;

		}
		switch (saveScoreBatchResult) {
		case 0:
			scoreResult = 0;
			break;
		case 1:
			scoreResult = 1;
			break;
		case 2:
			scoreResult = 2;
			break;

		}
		if (intervieweeResult == 0 && scoreResult == 0)
			return 0;
		else if (intervieweeResult == 2 && scoreResult == 2)
			return 2;
		else
			return 1;
	}

	/**
	 * Save interviewee batch.
	 *
	 * @param path the path
	 * @return the int
	 */
	@Transactional
	private int saveIntervieweeBatch(String path) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveIntervieweeBatch(String path) method of UploadScreeningTrackerFileServiceImpl class");
		errorLog = new ArrayList<String>();
		List<Person> intervieweeList = new ArrayList<Person>();
		int countOfRecordsInFile = 0;
		try {
			FileInputStream file = new FileInputStream(new File(path));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet intervieweeDetailsSheet = workbook.getSheetAt(0);

			boolean flag = true;
			// boolean isUnique = true;
			Person intervieweeExisting = null;
			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = intervieweeDetailsSheet.iterator();
			int rowNumber = 1;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				boolean validRecord = false;
				Person interviewee = new Person();
				PersonScreeningDetails personScreeningDetails = new PersonScreeningDetails();
				if (!flag) {
					validRecord = true;
					++rowNumber;
					countOfRecordsInFile++;
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();

					// field 1
					if (cellIterator.hasNext()) {
						Cell cell1 = cellIterator.next();
						if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {

							intervieweeExisting = intervieweeDao
									.getIntervieweeByOracleId((int) cell1
											.getNumericCellValue());
							if (intervieweeExisting == null) {
								if (personLookupService
										.getPersonByOracleId((int) cell1
												.getNumericCellValue()) != null) {
								interviewee.setPersonId((int) cell1
											.getNumericCellValue());
								} else {
									errorLog.add("Interviewee details sheet row "
											+ rowNumber
											+ " : Person with oracle id: "
											+ (int) cell1.getNumericCellValue()
											+ " not found.");
									validRecord = false;
									continue;
								}
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber
										+ " : Interviewee with oracle id: "
										+ (int) cell1.getNumericCellValue()
										+ " already exists.");
								validRecord = false;
								continue;
							}

						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for oracle id.");
							validRecord = false;
							continue;
						}
					}

					// field 2
					if (cellIterator.hasNext()) {
						Cell cell2 = cellIterator.next();
						Position position;
						if (cell2.getCellType() == Cell.CELL_TYPE_STRING) {
							position = positionDao.getPosition(cell2
									.getStringCellValue().trim());
							if (position != null) {
								interviewee.setPosition(position);
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber + " : Invalid title.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber + " : Invalid entry for title.");
							validRecord = false;
							continue;
						}
					}

					// field 3
					if (cellIterator.hasNext()) {
						Cell cell3 = cellIterator.next();
					LocationNew location;
						if (cell3.getCellType() == Cell.CELL_TYPE_STRING) {
							location = null;/* locationDao.getLocationByName(cell3
									.getStringCellValue().trim());*/
							if (location != null) {
								interviewee.setLocation(location);
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber + " : Invalid location.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for location.");
							validRecord = false;
							continue;
						}

					}

					// field 4
					Date startdate = null;
					if (cellIterator.hasNext()) {
						Cell cell4 = cellIterator.next();

						if (cell4.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							startdate = cell4.getDateCellValue();
							/*interviewee.setStartDate(startdate);*/
							personScreeningDetails.setScreeningStartDate(startdate);
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for start date.");
							validRecord = false;
							continue;
						}
					}

					// field 5
					if (cellIterator.hasNext()) {
						Cell cell5 = cellIterator.next();

						if (cell5.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							if (cell5.getDateCellValue().after(startdate)) {
								//interviewee.setEndDate(cell5.getDateCellValue());
								personScreeningDetails.setScreeningEndDate(cell5.getDateCellValue());
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber
										+ " : End date should be after the start date.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for end date.");
							validRecord = false;
							continue;
						}
					}
					String status = null;
					// field 6
					if (cellIterator.hasNext()) {
						Cell cell6 = cellIterator.next();

						if (cell6.getCellType() == Cell.CELL_TYPE_STRING) {
							status = cell6.getStringCellValue().trim();
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for status.");
							validRecord = false;
							continue;
						}
					}
					// field 7
					if (cellIterator.hasNext()) {
						Cell cell7 = cellIterator.next();
						Status statusObj;
						if (cell7.getCellType() == Cell.CELL_TYPE_STRING) {
							statusObj = statusDao.getStatusByNames(status,
									cell7.getStringCellValue().trim());
							if (statusObj != null) {
								//interviewee.setStatus(statusObj);
								personScreeningDetails.setStatus(statusObj);
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber
										+ " : Invalid status and result.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for result.");
							validRecord = false;
							continue;
						}
					}
					// field 8
					if (cellIterator.hasNext()) {
						Cell cell8 = cellIterator.next();
						if (cell8.getCellType() == Cell.CELL_TYPE_STRING) {
							String comments = cell8.getStringCellValue().trim();
							comments = comments.replace("\n", "\\n\\n");
							//interviewee.set(comments);
							personScreeningDetails.setFeedback(comments);
						}
					}

					// field 9
					if (cellIterator.hasNext()) {
						Cell cell9 = cellIterator.next();
						Category category;
						if (cell9.getCellType() == Cell.CELL_TYPE_STRING) {
							category = categoryDao.getCategory(cell9
									.getStringCellValue().trim());
							if (category != null) {
								//interviewee.setCategory(category);
								personScreeningDetails.setCategory(category);
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber + " : Invalid domain.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for domain.");
							validRecord = false;
							continue;
						}
					}

					// field 10
					if (cellIterator.hasNext()) {
						Cell cell10 = cellIterator.next();
						ProjectNew project;
								if (cell10.getCellType() == Cell.CELL_TYPE_STRING) {
							project = projectDao.getNewProjectByName(cell10
									.getStringCellValue().trim());
							if (project != null) {
								//interviewee.setProject(project);
								personScreeningDetails.setProjectNew(project);
							} else {
								errorLog.add("Interviewee details sheet row "
										+ rowNumber + " : Invalid project.");
								validRecord = false;
								continue;
							}
						} else {
							errorLog.add("Interviewee details sheet row "
									+ rowNumber
									+ " : Invalid entry for project.");
							validRecord = false;
							continue;
						}
					}
						personScreeningDetails.setPerson(interviewee);
				}

				flag = false;
				if (validRecord) {
					intervieweeList.add(interviewee);
				}
			}
			screeningTracker.saveScreeningSummary(intervieweeList);
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	if (intervieweeList.size() == 0)
			return 0;
		else if (intervieweeList.size() < countOfRecordsInFile)
			return 1;
		else
			return 2;

	}

	/**
	 * Save score batch.
	 *
	 * @param path the path
	 * @return the int
	 */
	@Transactional
	private int saveScoreBatch(String path) {
		CustomLoggerUtils.INSTANCE.log.info("inside saveScoreBatch(String path) method of UploadScreeningTrackerFileServiceImpl class");
		List<ScoresNew> scoreList = new ArrayList<ScoresNew>();
		int countOfRecordsInFile = 0;
		try {
			FileInputStream file = new FileInputStream(new File(path));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			Person intervieweeExisting = null;
			// Iterate through each rows from first sheet
			XSSFSheet ScoreSheet = workbook.getSheetAt(1);

			boolean flag1 = true;
			// Iterate through each rows from first sheet
			Iterator<Row> scoreRowIterator = ScoreSheet.iterator();
			int rowNumber = 1;
			while (scoreRowIterator.hasNext()) {
				Row row = scoreRowIterator.next();

				ScoresNew score = new ScoresNew();
				boolean validRecord = true;
				if (!flag1) {
					++rowNumber;
					countOfRecordsInFile++;
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();

					// field 1
					if (cellIterator.hasNext()) {
						Cell cell1 = cellIterator.next();
						if (cell1.getCellType() == Cell.CELL_TYPE_NUMERIC) {

							intervieweeExisting = intervieweeDao
									.getIntervieweeByOracleId((int) cell1
											.getNumericCellValue());
							if (intervieweeExisting == null) {
								errorLog.add("Score sheet row "
										+ rowNumber
										+ " : Interviewee record with oracle id: "
										+ (int) cell1.getNumericCellValue()
										+ " does not exist.");
								continue;
							} else {
								Person interviewee = new Person();
								PersonScreeningDetails personScreeningDetails = new PersonScreeningDetails();
								interviewee.setPersonId(intervieweeExisting.getPersonId());
								personScreeningDetails.setPerson(interviewee);
								score.setPersonScreeningDetails(personScreeningDetails);
								// field 2
								String category = null;
								if (cellIterator.hasNext()) {
									Cell cell2 = cellIterator.next();

									if (cell2.getCellType() == Cell.CELL_TYPE_STRING) {
										category = cell2.getStringCellValue()
												.trim();
									} else {
										errorLog.add("Score sheet row "
												+ rowNumber
												+ " : Invalid entry for category.");
										validRecord = false;
										continue;
									}
								}

								// field 3
								String criteria = null;
								if (cellIterator.hasNext()) {
									Cell cell3 = cellIterator.next();

									if (cell3.getCellType() == Cell.CELL_TYPE_STRING) {
										criteria = cell3.getStringCellValue()
												.trim();
									} else {
										errorLog.add("Score sheet row "
												+ rowNumber
												+ " : Invalid entry for criteria.");
										validRecord = false;
										continue;
									}

								}

								// field 4
								String topic = null;
								if (cellIterator.hasNext()) {
									Cell cell4 = cellIterator.next();

									if (cell4.getCellType() == Cell.CELL_TYPE_STRING) {
										topic = cell4.getStringCellValue()
												.trim();
										Topics topicObj = topicDao.getTopic(
												topic, criteria, categoryDao
														.getCategory(category));
										if (topicObj != null) {
												score.setTopic(topicObj);
										} else {
											errorLog.add("Score sheet row "
													+ rowNumber
													+ " : Invalid category/criteria/topic.");
											validRecord = false;
											continue;
										}
									} else {
										errorLog.add("Score sheet row "
												+ rowNumber
												+ " : Invalid entry for topic.");
										validRecord = false;
										continue;
									}
								}

								// field 5
								if (cellIterator.hasNext()) {
									Cell cell5 = cellIterator.next();

									if (cell5.getCellType() == Cell.CELL_TYPE_NUMERIC) {
										score.setScore(cell5
												.getNumericCellValue());
									} else {
										errorLog.add("Score sheet row "
												+ rowNumber
												+ " : Invalid entry for score.");
										validRecord = false;
										continue;
									}
								}

								// field 6
								if (cellIterator.hasNext()) {
									Cell cell6 = cellIterator.next();

									if (cell6.getCellType() == Cell.CELL_TYPE_STRING) {
										score.setComments(cell6
												.getStringCellValue());
									}
								}

							}

						} else {
							errorLog.add("Score sheet row "
									+ rowNumber
									+ " : Invalid entry for interviewee oracle id.");
							validRecord = false;
							continue;
						}
					}
					boolean isScoreUnique = true;
					/*if (intervieweeExisting != null) {
						for (Scores existingScore : intervieweeExisting.getScoresList()) {
							if (existingScore.getTopic().getTopicId() == score
									.getTopic().getTopicId()) {
								isScoreUnique = false;
								break;
							}
						}
						if (isScoreUnique && validRecord)
							scoreList.add(score);
					}*/

				}
				flag1 = false;

			}

			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		screeningTracker.saveScoresBatch(scoreList);
		if (scoreList.size() == 0)
			return 0;
		else if (scoreList.size() < countOfRecordsInFile)
			return 1;
		else
			return 2;

	}

	/* (non-Javadoc)
	 * @see com.sapient.statestreetscreeningapplication.model.service.UploadScreeningTrackerFileService#generateErrorLog()
	 */
	@Override
	public List<String> generateErrorLog() {
		return errorLog;
	}

	/**
	 * Gets the interviewee dao.
	 *
	 * @return the interviewee dao
	 */
	public IntervieweeDao getIntervieweeDao() {
		return intervieweeDao;
	}

	/**
	 * Sets the interviewee dao.
	 *
	 * @param intervieweeDao the new interviewee dao
	 */
	public void setIntervieweeDao(IntervieweeDao intervieweeDao) {
		this.intervieweeDao = intervieweeDao;
	}

	

	/**
	 * Gets the screening tracker.
	 *
	 * @return the screening tracker
	 */
	public ScreeningTrackerDao getScreeningTracker() {
		return screeningTracker;
	}

	/**
	 * Sets the screening tracker.
	 *
	 * @param screeningTracker the new screening tracker
	 */
	public void setScreeningTracker(ScreeningTrackerDao screeningTracker) {
		this.screeningTracker = screeningTracker;
	}

	/**
	 * Gets the position dao.
	 *
	 * @return the position dao
	 */
	public PositionDao getPositionDao() {
		return positionDao;
	}

	/**
	 * Sets the position dao.
	 *
	 * @param positionDao the new position dao
	 */
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	/**
	 * Gets the category dao.
	 *
	 * @return the category dao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	/**
	 * Sets the category dao.
	 *
	 * @param categoryDao the new category dao
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * Gets the project dao.
	 *
	 * @return the project dao
	 */
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	/**
	 * Sets the project dao.
	 *
	 * @param projectDao the new project dao
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * Gets the status dao.
	 *
	 * @return the status dao
	 */
	public StatusDao getStatusDao() {
		return statusDao;
	}

	/**
	 * Sets the status dao.
	 *
	 * @param statusDao the new status dao
	 */
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	/**
	 * Gets the location dao.
	 *
	 * @return the location dao
	 */
	public LocationDao getLocationDao() {
		return locationDao;
	}

	/**
	 * Sets the location dao.
	 *
	 * @param locationDao the new location dao
	 */
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * Gets the topic dao.
	 *
	 * @return the topic dao
	 */
	public TopicsDao getTopicDao() {
		return topicDao;
	}

	/**
	 * Sets the topic dao.
	 *
	 * @param topicDao the new topic dao
	 */
	public void setTopicDao(TopicsDao topicDao) {
		this.topicDao = topicDao;
	}

	/**
	 * Gets the person lookup service.
	 *
	 * @return the person lookup service
	 */
	public PersonLookupService getPersonLookupService() {
		return personLookupService;
	}

	/**
	 * Sets the person lookup service.
	 *
	 * @param personLookupService the new person lookup service
	 */
	public void setPersonLookupService(PersonLookupService personLookupService) {
		this.personLookupService = personLookupService;
	}

	/**
	 * Gets the error log.
	 *
	 * @return the error log
	 */
	public List<String> getErrorLog() {
		return errorLog;
	}

	/**
	 * Sets the error log.
	 *
	 * @param errorLog the new error log
	 */
	public void setErrorLog(List<String> errorLog) {
		this.errorLog = errorLog;
	}

}
