package com.sapient.statestreetscreeningapplication.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.DashboardService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualProjectActualBean;
import com.sapient.statestreetscreeningapplication.ui.bean.AnnualSummaryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlyProjectActualBean;
import com.sapient.statestreetscreeningapplication.ui.bean.QuarterlySummaryBean;
import com.sapient.statestreetscreeningapplication.utils.email.ExcelFileCreator;
import com.sapient.statestreetscreeningapplication.utils.enums.Quarters;
import com.sapient.statestreetscreeningapplication.virtual.dao.AnnualProjectActualDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.AnnualProjectBudgetDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.QuarterlyProjectActualDao;
import com.sapient.statestreetscreeningapplication.virtual.dao.QuarterlyProjectBudgetDao;


@RestController
public class DashBoardController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private DashboardService dashboardService;
	
//	@Autowired 
//	private HttpServletResponse response;
	
	/**
	 * Quarterly summary result.
	 *
	 * @param q the q
	 * @param y the y
	 * @return 
	 */
	private Map<String, Object> quarterlySummaryResult(Quarters q, int y) {

		QuarterDetailsBean quarterDetailsBean = dashboardService.quarterlySummaryCalculation(q, y);
		Map<String,Object> quarterlySummaryResultMap= new HashMap<>();
		List<QuarterlySummaryBean> quarterlySummaryBeanList= new ArrayList<>();

		List<QuarterlyProjectActualBean> allQPAThisQuarter = QuarterlyProjectActualDao.getQuarterlyProjectActualThisQuarter(q, y, quarterDetailsBean.getQuarterlyProjectActualBeanSet());

		float totalCreditThisQuarter = 0;

		for (QuarterlyProjectActualBean qpa : allQPAThisQuarter) {
			 QuarterlySummaryBean quarterlySummaryBean = new QuarterlySummaryBean();
			 quarterlySummaryBean.setQuarterlyProjectActualBean(qpa);
			 quarterlySummaryBean.setQuarterlyProjectBudgetBean(QuarterlyProjectBudgetDao.getQuarterlyProjectBudget(q, y, qpa.getProjectNewBean(), quarterDetailsBean.getQuarterlyProjectBudgetBeanSet()));
			 quarterlySummaryBean.setProjectNewBean(quarterlySummaryBean.getQuarterlyProjectActualBean().getProjectNewBean());
			 float projectCreditThisQuarter = quarterlySummaryBean.getQuarterlyProjectBudgetBean().getProjectQuarterlyBudgetValue() - quarterlySummaryBean.getQuarterlyProjectActualBean().getProjectQuarterlyCost();
			 quarterlySummaryBean.setTotalCreditThisQuarter(projectCreditThisQuarter);

			quarterlySummaryBeanList.add(quarterlySummaryBean);

			 totalCreditThisQuarter = totalCreditThisQuarter + projectCreditThisQuarter;
		}
		
		quarterlySummaryResultMap.put("quarterlyProjectActualBudgetList", allQPAThisQuarter);
		quarterlySummaryResultMap.put("totalCreditThisQuarter", totalCreditThisQuarter);
		return quarterlySummaryResultMap;

	}
	
		@CrossOrigin
		@RequestMapping("/displayQuarterlyProjectDashBoard")
		public Map<String, Object> SelectQuartersForSummaryAction(@RequestParam("quarter") String quarter, @RequestParam("year") Integer year) {

		List<Quarters> quarterList=Arrays.asList(Quarters.values());

		Quarters q = null;

		Calendar calender = Calendar.getInstance();

		if ((calender.get(Calendar.MONTH) == 0) || (calender.get(Calendar.MONTH) == 1)  || (calender.get(Calendar.MONTH) == 2 )) {
			q = Quarters.Q1;
		}
		if ((calender.get(Calendar.MONTH) == 3) || (calender.get(Calendar.MONTH) == 4)  || (calender.get(Calendar.MONTH) == 5 )) {
			q = Quarters.Q2;
		}
		if ((calender.get(Calendar.MONTH) == 6) || (calender.get(Calendar.MONTH) == 7)  || (calender.get(Calendar.MONTH) == 8 )) {
			q = Quarters.Q3;
		}
		if ((calender.get(Calendar.MONTH) == 9) || (calender.get(Calendar.MONTH) == 10) || (calender.get(Calendar.MONTH) == 11)) {
			q = Quarters.Q4;
		}

		if (quarter != null) {
			q = Quarters.valueOf(quarter);
		}
		
//		if (quarter == null) {
//			quarter = q;
//		}

		int y = calender.get(Calendar.YEAR);

		List<Integer> yearsList=(projectService.getYearList());

		if (year != null) {
			y = year;
		}
		if (year == null) {
			year = y;
		}

		Map<String, Object> quarterProjectDashBoardMap=quarterlySummaryResult(q, y);
		quarterProjectDashBoardMap.put("quarterList",quarterList);
		quarterProjectDashBoardMap.put("yearsList",yearsList);
		
		return quarterProjectDashBoardMap;

		}
		
				
		
		@CrossOrigin
		@RequestMapping("/displayAnnualProjectDashBoard")
		public Map<String, Object> selectAnnualForSummaryAction(@RequestParam("year") Integer year) {

			Calendar calender = Calendar.getInstance();

			int y = calender.get(Calendar.YEAR);

			List<Integer> yearsList=(projectService.getYearList());

			if (year != null) {
				y = year;
			}
			if (year == null) {
				year = y;
			}

			annualSummaryResult(y);

			Map<String, Object> annualProjectDashBoardMap=annualSummaryResult(y);
			annualProjectDashBoardMap.put("yearsList",yearsList);
			
			return annualProjectDashBoardMap;

			
		}

		private Map<String, Object> annualSummaryResult(int y) {

			AnnualDetailsBean annualDetailsBean = dashboardService.annualSummaryCalculation(y);
			
			Map<String,Object> annualSummaryResultMap= new HashMap<>();
			List<AnnualSummaryBean> annualSummaryBeanList= new ArrayList<>();
			
			List<AnnualProjectActualBean> allAPAThisYear = AnnualProjectActualDao.getAnnualProjectActualThisYear(y, annualDetailsBean.getAnnualProjectActualBeanSet());

			float totalCreditThisYear = 0;

			for (AnnualProjectActualBean apa : allAPAThisYear) {
				 AnnualSummaryBean annualSummaryBean = new AnnualSummaryBean();
				 annualSummaryBean.setAnnualProjectActualBean(apa);
				 annualSummaryBean.setAnnualProjectBudgetBean(AnnualProjectBudgetDao.getAnnualProjectBudget(y, apa.getProjectNewBean(), annualDetailsBean.getAnnualProjectBudgetBeanSet()));
				 annualSummaryBean.setProjectNewBean(annualSummaryBean.getAnnualProjectActualBean().getProjectNewBean());
				 float projectCreditThisYear = annualSummaryBean.getAnnualProjectBudgetBean().getProjectAnnualBudgetValue() - annualSummaryBean.getAnnualProjectActualBean().getProjectAnnualCost();
				 annualSummaryBean.setTotalCreditThisYear(projectCreditThisYear);

				 annualSummaryBeanList.add(annualSummaryBean);

				 totalCreditThisYear = totalCreditThisYear + projectCreditThisYear;
			}

			annualSummaryResultMap.put("annualProjectActualBudgetList", allAPAThisYear);
			annualSummaryResultMap.put("annualSummaryBeanList", annualSummaryBeanList);

			annualSummaryResultMap.put("totalCreditThisQuarter", totalCreditThisYear);
			return annualSummaryResultMap;
			
		}
		
		
		@CrossOrigin
		@RequestMapping(value="/annualProjectExport", method = RequestMethod.POST,produces = "application/octet-stream")
		public void AnnualSummaryExportAction(@RequestBody Integer year,HttpServletResponse response) throws IOException {
			
			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment;filename=AnnualSummary.xls");
			
			response.setHeader(headerKey, headerValue);
			Calendar calender = Calendar.getInstance();

			int y = calender.get(Calendar.YEAR);

			if (year != null) {
				y = year;
			}
			if (year == null) {
				year = y;
			}

			Map<String,Object> annualSummaryResultMap= annualSummaryResult(y);

			List<AnnualSummaryBean> annualSummaryBeanList=(List<AnnualSummaryBean>) annualSummaryResultMap.get("annualSummaryBeanList");
			
			String pathname = null;
			
			if (ExcelFileCreator.annualSummaryExport(annualSummaryBeanList) != null) {
				pathname = "D:\\PeopleOnboarding3.0\\Attachment.xls";
				pathname = pathname.replace("\"", "");
			} 
			else {
//				addActionMessage("No data found for this selection to Export");
//
//				if ("operations-menus.jsp".equals(urljsp)) {
//				return "failure-ops";
//				}
//				if ("leads-menus.jsp".equals(urljsp)) {
//				return "failure-leads";
//				}
			}
			
			
			try {
				InputStream fileInputStream= new FileInputStream(new File(pathname));
				OutputStream outputStream = response.getOutputStream();             // get output stream of the response

			    byte[] buffer = new byte[1024];
			    int bytesRead = -1;
			    while ((bytesRead = fileInputStream.read(buffer)) != -1) {  // write bytes read from the input stream into the output stream
			        outputStream.write(buffer, 0, bytesRead);
			    }

			    outputStream.flush();
			    
			    
			    fileInputStream.close();
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			

		}

		
		

	

}
