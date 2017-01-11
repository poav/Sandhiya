/*
 * 
 */
package com.sapient.statestreetscreeningapplication.utils.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.sapient.statestreetscreeningapplication.model.entity.MonthlyProjectBudgetEntity;
import com.sapient.statestreetscreeningapplication.model.entity.Person;
import com.sapient.statestreetscreeningapplication.model.entity.ProjectNew;
import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.model.service.impl.PersonLookupServiceImplementation;
import com.sapient.statestreetscreeningapplication.model.service.impl.ProjectServiceImpl;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.IntervieweeBean;
import com.sapient.statestreetscreeningapplication.ui.bean.MonthlyProjectBudgetBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PeopleInfoBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PositionBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlySummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoreBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.StatusBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;
import com.sapient.statestreetscreeningapplication.utils.converter.ProjectNewConverter;
import com.sapient.statestreetscreeningapplication.utils.enums.Months;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFileCreator.
 */
public class ExcelFileCreator {
	
	/** The person lookup service. */
	private static PersonLookupService personLookupService =new PersonLookupServiceImplementation();
	
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
		ExcelFileCreator.personLookupService = personLookupService;
	}
	
	
	
	/** The project service. */
	private static ProjectService projectService = new ProjectServiceImpl();
	
	/**
	 * Gets the project service.
	 *
	 * @return the project service
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * Sets the project service.
	 *
	 * @param projectService the new project service
	 */
	public void setProjectService(ProjectService projectService) {
		ExcelFileCreator.projectService = projectService;
	}

	
	
	
	/**
	 * Project summary export.
	 *
	 * @param projectSummaryBeanList the project summary bean list
	 * @param mpbEntityList the mpb entity list
	 * @param year the year
	 * @return the byte[]
	 */
	public static byte[] projectSummaryExport(List<ProjectSummaryBean> projectSummaryBeanList,List<MonthlyProjectBudgetEntity> mpbEntityList, int year) {
		
		if(projectSummaryBeanList!=null && projectSummaryBeanList.size() > 0){
			
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Project Summary");
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		sheet.setColumnWidth(16, 5000);
		sheet.setColumnWidth(17, 5000);
		sheet.setColumnWidth(18, 5000);
		sheet.setColumnWidth(19, 5000);
		sheet.setColumnWidth(20, 5000);
		sheet.setColumnWidth(21, 5000);
		sheet.setColumnWidth(22, 5000);
		sheet.setColumnWidth(23, 5000);
		sheet.setColumnWidth(24, 5000);
		sheet.setColumnWidth(25, 5000);
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		
		int key = 1;
		data.put(key, new Object[] { 
				"Lead",
				"Project", 
				"Domain", 
				"Location", 
				"Candidate Name", 
				"Allocation", 
				"Rate", 
				"Start Date",
				"End Date",
				"Jan",
				"Feb",
				"March",
				"Q1",
				"April",
				"May",
				"June",
				"Q2",
				"July",
				"Aug",
				"Sept",
				"Q3",
				"Oct",
				"Nov",
				"Dec",
				"Q4"
				});
		headings.add(key);
		key = key + 1;
		float janActualSum=0;
		float febActualSum=0;
		float marchActualSum=0;
		float aprilActualSum=0;
		float mayActualSum=0;
		float juneActualSum=0;
		float julyActualSum=0;
		float augActualSum=0;
		float septActualSum=0;
		float octActualSum=0;
		float novActualSum=0;
		float decActualSum=0;
		float q1ActualSum=0;
		float q2ActualSum=0;
		float q3ActualSum=0;
		float q4ActualSum=0;
		
		for(ProjectSummaryBean projectSummaryBean : projectSummaryBeanList) {

			if(projectSummaryBean != null){
				
			janActualSum = janActualSum + projectSummaryBean.getJan().getIndividualMonthlyCost();
			febActualSum = febActualSum + projectSummaryBean.getFeb().getIndividualMonthlyCost();	
			marchActualSum = marchActualSum + projectSummaryBean.getMarch().getIndividualMonthlyCost();
		    aprilActualSum = aprilActualSum + projectSummaryBean.getApril().getIndividualMonthlyCost();
			mayActualSum = mayActualSum + projectSummaryBean.getMay().getIndividualMonthlyCost();
			juneActualSum = juneActualSum + projectSummaryBean.getJune().getIndividualMonthlyCost();
			julyActualSum = julyActualSum + projectSummaryBean.getJuly().getIndividualMonthlyCost();
			augActualSum = augActualSum + projectSummaryBean.getAug().getIndividualMonthlyCost();
			septActualSum = septActualSum + projectSummaryBean.getSept().getIndividualMonthlyCost();
			octActualSum = octActualSum + projectSummaryBean.getOct().getIndividualMonthlyCost();
			novActualSum = novActualSum + projectSummaryBean.getNov().getIndividualMonthlyCost();
			decActualSum = decActualSum + projectSummaryBean.getDec().getIndividualMonthlyCost();
			q1ActualSum = q1ActualSum + projectSummaryBean.getQ1();
			q2ActualSum = q2ActualSum + projectSummaryBean.getQ2();
			q3ActualSum = q3ActualSum + projectSummaryBean.getQ3();
			q4ActualSum = q4ActualSum + projectSummaryBean.getQ4();
			
			String projectLeadName = "NA";
			if(projectSummaryBean.getPersonStaffingBean().getProject().getProjectLead()!=null){
			   projectLeadName = personLookupService.getPersonByNTId(projectSummaryBean.getPersonStaffingBean().getProject().getProjectLead().getPersonNtId()).getName();
			}
			String personStaffingName = "NA";
			if(projectSummaryBean.getPersonStaffingBean().getPerson().getIsTemp()){
			   personStaffingName = projectSummaryBean.getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName();
			}
			if(!projectSummaryBean.getPersonStaffingBean().getPerson().getIsTemp()){
				PersonBean person =personLookupService.getPersonByNTId(projectSummaryBean.getPersonStaffingBean().getPerson().getPersonNtId());
				if(person!=null){
					personStaffingName = person.getName();	
				}
				
			}
			
			data.put(key, new Object[] {
					projectLeadName,
					projectSummaryBean.getPersonStaffingBean().getProject().getProjectName(),
					projectSummaryBean.getPersonStaffingBean().getCategoryBean().getCategoryName(),
					projectSummaryBean.getPersonStaffingBean().getLocation().getCity(),
					personStaffingName,
					projectSummaryBean.getPersonStaffingBean().getAllocation(),
					projectSummaryBean.getPersonStaffingBean().getRateBean().getRate(),
					projectSummaryBean.getPersonStaffingBean().getStartDate(),
					projectSummaryBean.getPersonStaffingBean().getEndDate(),
					projectSummaryBean.getJan().getIndividualMonthlyCost(),
					projectSummaryBean.getFeb().getIndividualMonthlyCost(),
					projectSummaryBean.getMarch().getIndividualMonthlyCost(),
					projectSummaryBean.getQ1(),
					projectSummaryBean.getApril().getIndividualMonthlyCost(),
					projectSummaryBean.getMay().getIndividualMonthlyCost(),
					projectSummaryBean.getJune().getIndividualMonthlyCost(),
					projectSummaryBean.getQ2(),
					projectSummaryBean.getJuly().getIndividualMonthlyCost(),
					projectSummaryBean.getAug().getIndividualMonthlyCost(),
					projectSummaryBean.getSept().getIndividualMonthlyCost(),
					projectSummaryBean.getQ3(),
					projectSummaryBean.getOct().getIndividualMonthlyCost(),
					projectSummaryBean.getNov().getIndividualMonthlyCost(),
					projectSummaryBean.getDec().getIndividualMonthlyCost(),
					projectSummaryBean.getQ4(),
				 });
			}
			key = key + 1;
		
			}
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				});
		
		key = key + 1;
		
		String totalString = "Total Monthly";
		String mpaString = "Project Actuals";
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				totalString,
				mpaString,
				janActualSum,
				febActualSum,
				marchActualSum,
				q1ActualSum,
				aprilActualSum,
				mayActualSum,
				juneActualSum,
				q2ActualSum,
				julyActualSum,
				augActualSum,
				septActualSum,
				q3ActualSum,
				octActualSum,
				novActualSum,
				decActualSum,
				q4ActualSum,
				});
		
		key = key + 1;
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				});
		
		key = key + 1;
		
		float janBudgetSum=0;
		float febBudgetSum=0;
		float marchBudgetSum=0;
		float aprilBudgetSum=0;
		float mayBudgetSum=0;
		float juneBudgetSum=0;
		float julyBudgetSum=0;
		float augBudgetSum=0;
		float septBudgetSum=0;
		float octBudgetSum=0;
		float novBudgetSum=0;
		float decBudgetSum=0;
		
	    for(MonthlyProjectBudgetEntity mpbEntity : mpbEntityList ){
			 
				Months month = mpbEntity.getMonth();
				
				switch (month) {
				
				case JAN:   janBudgetSum=mpbEntity.getProjectMonthlyBudgetValue(); 
				            break;
				case FEB:   febBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
				case MARCH: marchBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
				case APRIL: aprilBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case MAY:   mayBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case JUNE:  juneBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case JULY:  julyBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case AUG:   augBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case SEPT:  septBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case OCT:   octBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case NOV:   novBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
			    case DEC:   decBudgetSum=mpbEntity.getProjectMonthlyBudgetValue();
			                break;
				default:    break;
				
				}
				
		}
		
	    float q1BudgetSum = janBudgetSum + febBudgetSum + marchBudgetSum;
		float q2BudgetSum = aprilBudgetSum + mayBudgetSum + juneBudgetSum;
		float q3BudgetSum = julyBudgetSum + augBudgetSum + septBudgetSum;
		float q4BudgetSum = octBudgetSum + novBudgetSum + decBudgetSum;
		
		String mpbString = "Project Budgets";
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				totalString,
				mpbString,
				janBudgetSum,
				febBudgetSum,
				marchBudgetSum,
				q1BudgetSum,
				aprilBudgetSum,
				mayBudgetSum,
				juneBudgetSum,
				q2BudgetSum,
				julyBudgetSum,
				augBudgetSum,
				septBudgetSum,
				q3BudgetSum,
				octBudgetSum,
				novBudgetSum,
				decBudgetSum,
				q4BudgetSum,
				});
		
		key = key + 1;
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				});
		
		key = key + 1;
		
		float janCredit   =  janBudgetSum   - janActualSum;
		float febCredit   =  febBudgetSum   - febActualSum;
		float marchCredit =  marchBudgetSum - marchActualSum;
		float aprilCredit =  aprilBudgetSum - aprilActualSum;
		float mayCredit   =  mayBudgetSum   - mayActualSum;
		float juneCredit  =  juneBudgetSum  - juneActualSum;
		float julyCredit  =  julyBudgetSum  - julyActualSum;
		float augCredit   =  augBudgetSum   - augActualSum;
		float septCredit  =  septBudgetSum  - septActualSum;
		float octCredit   =  octBudgetSum   - octActualSum;
		float novCredit   =  novBudgetSum   - novActualSum;
		float decCredit   =  decBudgetSum   - decActualSum;
		float q1Credit    =  q1BudgetSum    - q1ActualSum;
		float q2Credit    =  q2BudgetSum    - q2ActualSum;
		float q3Credit    =  q3BudgetSum    - q3ActualSum;
		float q4Credit    =  q4BudgetSum    - q4ActualSum;
		
		
		String creditString = "Project Credits";
		
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				totalString,
				creditString,
				janCredit,
				febCredit,
				marchCredit,
				q1Credit,
				aprilCredit,
				mayCredit,
				juneCredit,
				q2Credit,
				julyCredit,
				augCredit,
				septCredit,
				q3Credit,
				octCredit,
				novCredit,
				decCredit,
				q4Credit,
				});
		
		key = key + 1;
		
		
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}
				
					else if (obj instanceof Long) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 
					
					else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}
					
					else if (obj instanceof Float) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 

				}
			}
			
			try {
				FileOutputStream out = new FileOutputStream(new File(
						"D://PeopleOnboarding3.0\\Attachment.xls"));
				workbook.write(out);
				out.flush();

				

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				try {
					workbook.write(bos);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] bytes = bos.toByteArray();

			return bytes;
			}
			return null;
	}
	
	/**
	 * Annual summary export.
	 *
	 * @param annualSummaryBeanList the annual summary bean list
	 * @return the byte[]
	 */
	public static byte[] annualSummaryExport(List<AnnualSummaryBean> annualSummaryBeanList) {
		
		if(annualSummaryBeanList!=null && annualSummaryBeanList.size() > 0){
			
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Quarterly Summary");
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		
		int key = 1;
		data.put(key, new Object[] { 
				"Client Project Name",
				"Client Project Id", 
				"Client Time Tracking Id", 
				"Cost Center", 
				"Project Start Date", 
				"Project End Date", 
				"Project Budget For The Year", 
				"Project Actual For The Year",
				"Comments",
				"Project Credit For The Year" 
				});
		headings.add(key);
		key = key + 1;
		float sumOfTotalCreditThisYear = 0;
		String annualComments = "total annual credit";
		for(AnnualSummaryBean annualSummaryBean :annualSummaryBeanList) {

			if(annualSummaryBean != null){
			String comments = annualSummaryBean.getComments();
			sumOfTotalCreditThisYear = sumOfTotalCreditThisYear + annualSummaryBean.getTotalCreditThisYear();
			if(comments==null){
			   comments = "NA";
			}
			data.put(key, new Object[] { 
					annualSummaryBean.getProjectNewBean().getClientProjectName(),
					annualSummaryBean.getProjectNewBean().getClientProjectId(),
					annualSummaryBean.getProjectNewBean().getClientTimeTrackingId(),
					annualSummaryBean.getProjectNewBean().getCostCenter(),
					annualSummaryBean.getProjectNewBean().getProjectStartDate(),
					annualSummaryBean.getProjectNewBean().getProjectEndDate(),
					annualSummaryBean.getAnnualProjectBudgetBean().getProjectAnnualBudgetValue(),
					annualSummaryBean.getAnnualProjectActualBean().getProjectAnnualCost(),
					//annualSummaryBean.getComments(),
					comments,
					annualSummaryBean.getTotalCreditThisYear(),
				 });
			}
			key = key + 1;
		
			}
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				//annualSummaryBean.getComments(),
				annualComments,
				sumOfTotalCreditThisYear,
				});
		
		
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}
				
					else if (obj instanceof Long) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 
					
					else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}
					
					else if (obj instanceof Float) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 

				}
			}
			
			try {
				FileOutputStream out = new FileOutputStream(new File(
						"D://PeopleOnboarding3.0\\Attachment.xls"));
				workbook.write(out);
				out.flush();

				

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				try {
					workbook.write(bos);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] bytes = bos.toByteArray();

			return bytes;
			}
			return null;
	}

	/**
	 * Quarterly summary export.
	 *
	 * @param quarterlySummaryBeanList the quarterly summary bean list
	 * @return the byte[]
	 */
	public static byte[] quarterlySummaryExport(List<QuarterlySummaryBean> quarterlySummaryBeanList) {
		
		if(quarterlySummaryBeanList!=null && quarterlySummaryBeanList.size() > 0){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Quarterly Summary");
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		
		int key = 1;
		data.put(key, new Object[] { 
				"Client Project Name",
				"Client Project Id", 
				"Client Time Tracking Id", 
				"Cost Center", 
				"Project Start Date", 
				"Project End Date", 
				"Project Budget For The Quarter", 
				"Project Actual For The Quarter",
				"Comments",
				"Project Credit For The Quarter" 
				});
		headings.add(key);
		key = key + 1;
		float sumOfTotalCreditThisQuarter = 0;
		String quarterComments = "total quarter credit";
		for(QuarterlySummaryBean quarterlySummaryBean :quarterlySummaryBeanList) {

			if(quarterlySummaryBean != null){
			String comments = quarterlySummaryBean.getComments();
			sumOfTotalCreditThisQuarter = sumOfTotalCreditThisQuarter + quarterlySummaryBean.getTotalCreditThisQuarter();
			if(comments==null){
			   comments = "NA";
			}
			data.put(key, new Object[] { 
					quarterlySummaryBean.getProjectNewBean().getClientProjectName(),
					quarterlySummaryBean.getProjectNewBean().getClientProjectId(),
					quarterlySummaryBean.getProjectNewBean().getClientTimeTrackingId(),
					quarterlySummaryBean.getProjectNewBean().getCostCenter(),
					quarterlySummaryBean.getProjectNewBean().getProjectStartDate(),
					quarterlySummaryBean.getProjectNewBean().getProjectEndDate(),
					quarterlySummaryBean.getQuarterlyProjectBudgetBean().getProjectQuarterlyBudgetValue(),
					quarterlySummaryBean.getQuarterlyProjectActualBean().getProjectQuarterlyCost(),
					//quarterlySummaryBean.getComments(),
					comments,
					quarterlySummaryBean.getTotalCreditThisQuarter(),
				 });
			}
			key = key + 1;
		
			}
		data.put(key, new Object[] { 
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				//annualSummaryBean.getComments(),
				quarterComments,
				sumOfTotalCreditThisQuarter,
				});

		
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}
				
					else if (obj instanceof Long) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 
					
					else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}
					
					else if (obj instanceof Float) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} 

				}
			}
			
			try {
				FileOutputStream out = new FileOutputStream(new File(
						"D://PeopleOnboarding3.0\\Attachment.xls"));
				workbook.write(out);
				out.flush();

				

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				try {
					workbook.write(bos);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} finally {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] bytes = bos.toByteArray();

			return bytes;
			}
			return null;

			}

	/**
	 * Top screeners of the month excel.
	 *
	 * @param screenersMap the screeners map
	 * @return the byte[]
	 */
	public static byte[] topScreenersOfTheMonthExcel(TreeMap<Person,Long> screenersMap) {
		if(screenersMap!=null && screenersMap.size() > 0){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Top Screeners Of The Month");

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		

		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		int key = 1;
		data.put(key, new Object[] { "Screener Name",
				"Screener Oracle ID", "Screening Count"});
		headings.add(key);
		key = key + 1;
		for(Map.Entry<Person,Long> entry :screenersMap.entrySet()) {

			
			
			if(entry.getKey()!=null){
			data.put(key, new Object[] { personLookupService.getPersonByNTId((entry.getKey().getPersonNtId())).getName(),
					entry.getKey().getPersonOracleId(),
					entry.getValue(),
				 });
			}
			key = key + 1;
		
			}
		
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);

				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					

					if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}

				
					else if (obj instanceof Long) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

				}
			}
		

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			try {
				workbook.write(bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = bos.toByteArray();

		return bytes;
		}
		return null;
	}
	
	
	
	
	

	/**
	 * Interviewee details with scores file creator.
	 *
	 * @param list2 the list2
	 * @return the byte[]
	 */
	public static byte[] intervieweeDetailsWithScoresFileCreator(List<PersonScreeningDetailsBean> list2) {
		if(list2!=null && list2.size() > 0){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Interviewee details");

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 13000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 6500);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);

		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		int key = 1;
		for (int m = 0; m < list2.size(); m++) {

			data.put(key, new Object[] { "INTERVIEWEE NAME",
					"INTERVIEWEE ORACLE ID", "CATEGORY", "START DATE",
					"END DATE", "STATUS", "RESULT", "COMMENT", "PROJECT",
					"POSITION", "LOCATION" });
			headings.add(key);
			key = key + 1;
			String statusName = "Status Not Set", resultName = "--";

			if (list2.get(m).getStatusBean() != null) {
				statusName = list2.get(m).getStatusBean().getStatusName();
				resultName = list2.get(m).getStatusBean().getResultName();
			}
			String comments = list2.get(m).getFeedback();
			/*
			 * comments = comments.replace("<br>", "\n"); comments =
			 * comments.replace("<b>", ""); comments = comments.replace("</b>",
			 * "");
			 */
			if(list2.get(m).getProjectBean()!=null){
				if(list2.get(m).getPersonBean().getIsTemp()){
					data.put(key, new Object[] { list2.get(m).getPersonBean().getTempPersonBean().getTempPersonName(),
							list2.get(m).getPersonBean().getPersonOracleId(),
							list2.get(m).getCategory(),
							list2.get(m).getStringScreeningStartDate(), 
							list2.get(m).getStringScreeningEndDate(),
							statusName,
							resultName,
							comments,
							list2.get(m).getProjectBean().getProjectName(),
							list2.get(m).getPersonBean().getPosition().getPositionName(),
							list2.get(m).getPersonBean().getLocation().getCity(), });
				}else{
					PersonBean p=personLookupService.getPersonByOracleId(list2.get(m).getPersonBean().getPersonOracleId());
					if(p!=null){
					list2.get(m).getPersonBean().setPersonName(p.getName());
					data.put(key, new Object[] { list2.get(m).getPersonBean().getPersonName(),
					list2.get(m).getPersonBean().getPersonOracleId(),
					list2.get(m).getCategory(),
					list2.get(m).getStringScreeningStartDate(), 
					list2.get(m).getStringScreeningEndDate(),
					statusName,
					resultName,
					comments,
					list2.get(m).getProjectBean().getProjectName(),
					list2.get(m).getPersonBean().getPosition().getPositionName(),
					list2.get(m).getPersonBean().getLocation().getCity(), });
					}
				}
			}else{
				if(list2.get(m).getPersonBean().getIsTemp()){
					data.put(key, new Object[] { list2.get(m).getPersonBean().getTempPersonBean().getTempPersonName(),
							list2.get(m).getPersonBean().getPersonOracleId(),
							list2.get(m).getCategory(),
							list2.get(m).getStringScreeningStartDate(), 
							list2.get(m).getStringScreeningEndDate(),
							statusName,
							resultName,
							comments,
							null,
							list2.get(m).getPersonBean().getPosition().getPositionName(),
							list2.get(m).getPersonBean().getLocation().getCity(), });	
					}else{
						PersonBean p=personLookupService.getPersonByOracleId(list2.get(m).getPersonBean().getPersonOracleId());
						if(p!=null){
						list2.get(m).getPersonBean().setPersonName(p.getName());
						data.put(key, new Object[] { list2.get(m).getPersonBean().getPersonName(),
						list2.get(m).getPersonBean().getPersonOracleId(),
						list2.get(m).getCategory(),
						list2.get(m).getStringScreeningStartDate(), 
						list2.get(m).getStringScreeningEndDate(),
						statusName,
						resultName,
						comments,
						null,
						list2.get(m).getPersonBean().getPosition().getPositionName(),
						list2.get(m).getPersonBean().getLocation().getCity(), });
						}
					}
			}
			key = key + 1;
			data.put(key, new Object[] { null, null, null, null, null, null,
					null, null, null, null, null });
			key = key + 1;

			int scoreListSize = list2.get(m).getScoreList().size();
			if (scoreListSize != 0) {
				data.put(key, new Object[] { "CRITERIA", "TOPIC", "SCORE",
						"COMMENTS" });
				headings.add(key);
				CustomLoggerUtils.INSTANCE.log.info("interviewee id: "
						+ list2.get(m).getPersonBean().getPersonId());
				/*for (int j = 0; j < list2.get(m).getScoreList().size(); j++) {
					key = key + j + 1;
					data.put(key, new Object[] {
							list2.get(m).getScoreList().get(j).getTopicBean().getCriteriaBean().getCriteriaName(),
							list2.get(m).getScoreList().get(j).getTopicBean().getTopicName(),
							list2.get(m).getScoreList().get(j).getScore(),
							list2.get(m).getScoreList().get(j).getComments().trim() });
				}*/
				int j=0;
				for (ScoresNewBean scoreBean: list2.get(m).getScoreList()) {
					key = key + j + 1;
					j++;
					data.put(key, new Object[] {
							scoreBean.getTopicBean().getCriteriaBean().getCriteriaName(),
							scoreBean.getTopicBean().getTopicName(),
							scoreBean.getScore(),
							scoreBean.getComments().trim() });
				}
				
				key = key + 1;
				data.put(key, new Object[] { null, null, null, null, null,
						null, null, null, null, null, null });
				key = key + 1;
			}
			data.put(key, new Object[] { null, null, null, null, null, null,
					null, null, null, null, null });
			key = key + 1;
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);

				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof Date) {
						Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						String s = formatter.format(obj);
						cell.setCellValue((String) s);

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof CategoryBean) {
						cell.setCellValue(((CategoryBean) obj)
								.getCategoryName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof StatusBean) {
						cell.setCellValue(((StatusBean) obj).getStatusName()
								+ ":" + ((StatusBean) obj).getResultName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof PositionBean) {
						cell.setCellValue(((PositionBean) obj)
								.getPositionName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof Double) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

				}
			}
		}

		// Present only to test the attachment created.
		/*
		 * try { FileOutputStream out = new FileOutputStream(new File(
		 * "D:\\ScreeningApplication\\Interviewee1.xls")); workbook.write(out);
		 * out.flush();
		 * 
		 * log.info("Excel written successfully..");
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		// converting the excel file into byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			try {
				workbook.write(bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = bos.toByteArray();

		return bytes;
		}
		return null;
	}
	
	

	/**
	 * create a library of cell styles.
	 *
	 * @param wb the wb
	 * @return the map
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.TAN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		style.setWrapText(true);
		styles.put("header", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setWrapText(true);
		styles.put("content", style);

		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setWrapText(true);
		styles.put("contentComment", style);

		return styles;
	}

	/**
	 * Creates the bordered style.
	 *
	 * @param wb the wb
	 * @return the cell style
	 */
	private static CellStyle createBorderedStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
	
	
	


	/**
	 * People information.
	 *
	 * @param list1 the list2
	 * @return the byte[]
	 */
	public static byte[] peopleInformation(List<OnboardingCheckListBean> list1) {
		if(list1!=null && list1.size() > 0){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("People Information");

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 13000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 6500);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		sheet.setColumnWidth(16, 5000);
		sheet.setColumnWidth(17, 5000);
		sheet.setColumnWidth(18, 5000);
		sheet.setColumnWidth(19, 5000);
		sheet.setColumnWidth(20, 5000);
		sheet.setColumnWidth(21, 5000);
		sheet.setColumnWidth(22, 5000);
		sheet.setColumnWidth(23, 5000);
		sheet.setColumnWidth(24, 5000);
		sheet.setColumnWidth(25, 8000);
		sheet.setColumnWidth(26, 8000);
		sheet.setColumnWidth(27, 8000);
		sheet.setColumnWidth(28, 15000);
		sheet.setColumnWidth(29, 15000);
		sheet.setColumnWidth(30, 15000);
		
		
		
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		int key = 1;
		data.put(key, new Object[] { "Sapient Id",
				"Sapient Person", "Sapient Email", "Title",
				"Level", "Domain", "Location","Sapient Lead","SapientProject Name",
				"Client Project Id","Client Project Name","Client Time Tracking Id",
				"Start Date","End Date","Cost Center",
				"Active",
				"Status",
				"Date Onboarding Initiated",
				"Date Background Check Submitted","Date Background Check Done",
				"Background Check Status",
				"Rampup Initiated Date","Finger printing complete",
				"Date Person Added In Client Vendor Management System","Date Person Approved In Client Vendor Management System",
				"Machine Name","Machine Request Date","Time Off Tool RequestDate",
				"Office Access Request Date","User Access Request Date",
				"User Access Request Id"});
		headings.add(key);
		key = key + 1;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
		String endDate=dateFormat.format(currentDate);
		
		for (int m = 0; m < list1.size(); m++) {

			/*boolean isUsed=list1.get(m).getOnboardingResourceBean().isActive();//getInterviewee().getProjectBean().getIsUsed();
*/			String active="";
			String NA="NA";

			
			if((list1.get(m).getPersonStaffingBean().getEndDate()!=null)){
			endDate=list1.get(m).getPersonStaffingBean().getEndDate();

			}
			try {
				if((dateFormat.parse(endDate).compareTo(currentDate)>0) || (dateFormat.parse(endDate).equals(currentDate)) || (list1.get(m).getPersonStaffingBean().getEndDate()==null))
					active="Active";
				else
					active="Inactive";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*String statusName = "Status Not Set", resultName = "--";
			if (list2.get(m).getStatus() != null) {
				statusName = list2.get(m).getStatus().getStatusName();
				resultName = list2.get(m).getStatus().getResultName();
			}
			String comments = list2.get(m).getComments();
			
			 * comments = comments.replace("<br>", "\n"); comments =
			 * comments.replace("<b>", ""); comments = comments.replace("</b>",
			 * "");
			 */
			/*data.put(key, new Object[] { list2.get(m).getIntervieweeName(),
					list2.get(m).getIntervieweeOracleID(),
					list2.get(m).getCategoryBean().getCategoryName(),
					list2.get(m).getStartDate(), list2.get(m).getEndDate(),
					statusName, resultName, comments,
					list2.get(m).getProjectBean().getProjectName(),
					list2.get(m).getPositionBean().getPositionName(),
					list2.get(m).getLocation().getLocationName(), });*/
			if(list1.get(m).getPersonStaffingBean().getPerson().getIsTemp()){
				data.put(key, new Object[] { NA,
						list1.get(m).getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName(),
						list1.get(m).getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonEmail(),
						list1.get(m).getPersonStaffingBean().getPosition().getPositionName(), 
						list1.get(m).getPersonStaffingBean().getPosition().getLevel(),
						list1.get(m).getPersonStaffingBean().getPosition().getDomain(),
						list1.get(m).getPersonStaffingBean().getLocation().getCity(),
						/*list1.get(m).getPersonStaffingBean().getSapientLead().getPersonName()*/NA,
						list1.get(m).getPersonStaffingBean().getProject().getProjectName(),
						list1.get(m).getPersonStaffingBean().getProject().getClientProjectId(),
						list1.get(m).getPersonStaffingBean().getProject().getClientProjectName(),
						list1.get(m).getPersonStaffingBean().getProject().getClientTimeTrackingId(),
						list1.get(m).getPersonStaffingBean().getStartDate(),
						list1.get(m).getPersonStaffingBean().getEndDate(),
						list1.get(m).getPersonStaffingBean().getProject().getCostCenter(),
						active,
						list1.get(m).getOnboardingStatus(),
						list1.get(m).getDateOnboardingInitiated(),
						list1.get(m).getDateBackgroundCheckSubmitted(),
						list1.get(m).getDateBackgroundCheckDone(),
						//list2.get(m).getOnboardingCheckListOpsBean().getLeadsAction(),
						list1.get(m).getBackgroundCheckStatus(),
						list1.get(m).getRampupInitiatedDate(),
						list1.get(m).getFingerPrintDate(),
						list1.get(m).getDatePersonAddedInClientVendorManagementSystem(),
						list1.get(m).getDatePersonApprovedInClientVendorManagementSystem(),
						list1.get(m).getMachineName(),
						list1.get(m).getMachineRequestDate(),
						list1.get(m).getTimeOffToolRequestDate(),
						list1.get(m).getOfficeAccessRequestDate(),
						list1.get(m).getUserAccessRequestDate(),
						list1.get(m).getUserAccessRequestId()
						
						});
				
			}else{
				data.put(key, new Object[] { list1.get(m).getPersonStaffingBean().getPerson().getPersonOracleId(),
						list1.get(m).getPersonStaffingBean().getPerson().getPersonName(),
						list1.get(m).getPersonStaffingBean().getPerson().getPersonEmailId(),
						list1.get(m).getPersonStaffingBean().getPosition().getPositionName(), 
						list1.get(m).getPersonStaffingBean().getPosition().getLevel(),
						list1.get(m).getPersonStaffingBean().getPosition().getDomain(),
						list1.get(m).getPersonStaffingBean().getLocation().getCity(),
						/*list1.get(m).getPersonStaffingBean().getSapientLead().getPersonName()*/NA,
						list1.get(m).getPersonStaffingBean().getProject().getProjectName(),
						list1.get(m).getPersonStaffingBean().getProject().getClientProjectId(),
						list1.get(m).getPersonStaffingBean().getProject().getClientProjectName(),
						list1.get(m).getPersonStaffingBean().getProject().getClientTimeTrackingId(),
						list1.get(m).getPersonStaffingBean().getStartDate(),
						list1.get(m).getPersonStaffingBean().getEndDate(),
						list1.get(m).getPersonStaffingBean().getProject().getCostCenter(),
						active,
						list1.get(m).getOnboardingStatus(),
						list1.get(m).getDateOnboardingInitiated(),
						list1.get(m).getDateBackgroundCheckSubmitted(),
						list1.get(m).getDateBackgroundCheckDone(),
						//list2.get(m).getOnboardingCheckListOpsBean().getLeadsAction(),
						list1.get(m).getBackgroundCheckStatus(),
						list1.get(m).getRampupInitiatedDate(),
						list1.get(m).getFingerPrintDate(),
						list1.get(m).getDatePersonAddedInClientVendorManagementSystem(),
						list1.get(m).getDatePersonApprovedInClientVendorManagementSystem(),
						list1.get(m).getMachineName(),
						list1.get(m).getMachineRequestDate(),
						list1.get(m).getTimeOffToolRequestDate(),
						list1.get(m).getOfficeAccessRequestDate(),
						list1.get(m).getUserAccessRequestDate(),
						list1.get(m).getUserAccessRequestId()
						
						});
			}
			
			key = key + 1;
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);

				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof Date) {
						Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						String s = formatter.format(obj);
						cell.setCellValue((String) s);

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					/*else if (obj instanceof CategoryBean) {
						cell.setCellValue(((CategoryBean) obj)
								.getCategoryName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}*/

					else if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					/*else if (obj instanceof StatusBean) {
						cell.setCellValue(((StatusBean) obj).getStatusName()
								+ ":" + ((StatusBean) obj).getResultName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof PositionBean) {
						cell.setCellValue(((PositionBean) obj)
								.getPositionName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}*/

					else if (obj instanceof Double) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}
					else if (obj instanceof Long) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}
					else if (obj ==null) {
						/*cell.setCellValue(obj.toString());*/

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

				}
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(
					"D://PeopleOnboarding3.0\\Attachment.xls"));
			workbook.write(out);
			out.flush();

			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			try {
				workbook.write(bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = bos.toByteArray();

		return bytes;
		}
		return null;
	}
	
	/**
	 * Interviewee details without scores file creator.
	 *
	 * @param list2 the list2
	 * @return the byte[]
	 */
	public static byte[] intervieweeDetailsWithoutScoresFileCreator(List<IntervieweeBean> list2) {
		if(list2!=null && list2.size() > 0){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Interviewee details");

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 13000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 6500);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);

		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		List<Integer> headings = new ArrayList<Integer>();
		Map<String, CellStyle> styles = createStyles(workbook);
		int key = 1;
		data.put(key, new Object[] { "INTERVIEWEE NAME",
				"INTERVIEWEE ORACLE ID", "CATEGORY", "START DATE",
				"END DATE", "STATUS", "RESULT", "COMMENT", "PROJECT",
				"POSITION", "LOCATION" });
		headings.add(key);
		key = key + 1;
		for (int m = 0; m < list2.size(); m++) {

			
			String statusName = "Status Not Set", resultName = "--";

			if (list2.get(m).getStatus() != null) {
				statusName = list2.get(m).getStatus().getStatusName();
				resultName = list2.get(m).getStatus().getResultName();
			}
			String comments = list2.get(m).getComments();
			/*
			 * comments = comments.replace("<br>", "\n"); comments =
			 * comments.replace("<b>", ""); comments = comments.replace("</b>",
			 * "");
			 */
			data.put(key, new Object[] { list2.get(m).getIntervieweeName(),
					list2.get(m).getIntervieweeOracleID(),
					list2.get(m).getCategoryBean().getCategoryName(),
					list2.get(m).getStartDate(), list2.get(m).getEndDate(),
					statusName, resultName, comments,
					list2.get(m).getProjectBean().getProjectName(),
					list2.get(m).getPositionBean().getPositionName(),
					list2.get(m).getLocation().getLocationName(), });
			key = key + 1;
			
			
			Set<Integer> keyset = data.keySet();
			List<Integer> list = new ArrayList<Integer>(keyset);
			Collections.sort(list);
			int rownum = 0;
			for (Integer key1 : list) {
				Row row = sheet.createRow(rownum++);

				Object[] objArr = data.get(key1);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);

					if (obj instanceof Date) {
						Format formatter = new SimpleDateFormat("yyyy-MM-dd");
						String s = formatter.format(obj);
						cell.setCellValue((String) s);

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof CategoryBean) {
						cell.setCellValue(((CategoryBean) obj)
								.getCategoryName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (headings.contains(key1)) {
							cell.setCellStyle(styles.get("header"));

						} else if (((String) obj).length() >= 100) {
							cell.setCellStyle(styles.get("contentComment"));
						} else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof StatusBean) {
						cell.setCellValue(((StatusBean) obj).getStatusName()
								+ ":" + ((StatusBean) obj).getResultName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof PositionBean) {
						cell.setCellValue(((PositionBean) obj)
								.getPositionName());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

					else if (obj instanceof Double) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					} else if (obj instanceof Integer) {
						cell.setCellValue(obj.toString());

						if (headings.contains(key1))
							cell.setCellStyle(styles.get("header"));
						else {
							cell.setCellStyle(styles.get("content"));
						}
					}

				}
			}
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			try {
				workbook.write(bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = bos.toByteArray();

		return bytes;
		}
		return null;
	}
	
	/**
	 * No screens.
	 *
	 * @param msg the msg
	 * @return the byte[]
	 */
	public static byte[] noScreens(String msg) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Interviewee details");

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		sheet.setColumnWidth(0, 13000);
		String data= msg;
		
		Map<String, CellStyle> styles = createStyles(workbook);
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		
		cell.setCellValue(data);

	
		cell.setCellStyle(styles.get("header"));
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			try {
				workbook.write(bos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] bytes = bos.toByteArray();

		return bytes;
		
	}
}
