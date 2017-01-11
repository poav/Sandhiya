package com.sapient.statestreetscreeningapplication.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.PersonLookupService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectDashboardService;
import com.sapient.statestreetscreeningapplication.model.service.ProjectService;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectNewBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ProjectSummaryBean;
import com.sapient.statestreetscreeningapplication.utils.CustomLoggerUtils;


@RestController
public class ProjectDashBoardController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectDashboardService projectDashboardService;

	@Autowired
	private PersonLookupService personLookupService;

	@CrossOrigin
	@RequestMapping("/displayProjectDashBoard")
	public Map<String, Object> populateProjectDashboardTable(@RequestParam("start") int start,
			@RequestParam("rows") int rows,
			@RequestParam("colNum") int colNum,
			@RequestParam("searchKey") String searchKey,
			@RequestParam("sortDirection") String  sortDirection,
			@RequestParam("year") int  year,
			@RequestParam("projectName") String  projectName){

		CustomLoggerUtils.INSTANCE.log
		.info("inside  populateProjectDashboardTable() method of ProjectDashboardAction");
		Map<String, Object> projectSummaryBeanMap;
		ProjectNewBean pnb = null;

		String[] columnNamess = { "personStaffingBean.personStaffingId", "personStaffingBean.project.projectName",
				"personStaffingBean.categoryBean.categoryName", "personStaffingBean.location.locationName", 
				"personStaffingBean.person.personName", 
				"personStaffingBean.allocation" ,
				"personStaffingBean.rateBean.rate" ,
				"personStaffingBean.startDate",
				"personStaffingBean.endDate", 
				"jan.individualMonthlyCost", "feb.individualMonthlyCost", "march.individualMonthlyCost","q1",
				"april.individualMonthlyCost","may.individualMonthlyCost", "june.individualMonthlyCost","q2", 
				"july.individualMonthlyCost","aug.individualMonthlyCost", "sept.individualMonthlyCost","q3",
				"oct.individualMonthlyCost", "nov.individualMonthlyCost", "dec.individualMonthlyCost","q4"};
		

		String columnName;
				
			if(projectName==null){
				pnb=projectService.getNewProjectName(projectService.getAllProjects().get(0));
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Calendar cal=Calendar.getInstance();
				try {
					cal.setTime(formatter.parse(pnb.getProjectStartDate()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				year=cal.get(Calendar.YEAR);
			}else{
			pnb=projectService.getNewProjectName(projectName);
			}
		
			if (colNum < 0 || colNum > columnNamess.length) {
				colNum = 0;
			}
			columnName = columnNamess[colNum];
			if (start < 0) {
				start = 0;
			}
			if (rows < 0) {
				rows = 0;
			}
			if (!sortDirection.equals("asc")) {
				sortDirection = "desc";
			}
			/*
			 * CustomLoggerUtils.INSTANCE.log.info("Interviewee Datatable. start:"
			 * + sStart + " amount:" + sAmount + " sortingColumn:" + columnName
			 * + " dir:" + sdir + " search:" + sSearch);
			 */

		

				
				
		projectSummaryBeanMap= projectDashboardService.populateProjectDashboardTable(start,
				rows, "PERSON_STAFFING_ID", sortDirection, searchKey, pnb.getProjectId(),year);
		
		
		
		@SuppressWarnings("unchecked")
		List<ProjectSummaryBean> projectSummaryBeanList= (List<ProjectSummaryBean>) projectSummaryBeanMap.get("projectSummaryBeanList");
		
		String data = "";
		int i = 0;
		
		
		

		do {
			if (i != 0) {
				data += ",";
			}
			
			if(!projectSummaryBeanList.isEmpty())
		{
			if(projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().getIsTemp()){
				projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().setPersonName(projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().getTempPersonBean().getTempPersonName());
				
			}else{
				PersonBean pbean=personLookupService.getPersonByOracleId(projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().getPersonOracleId());
				if(pbean!=null){
					projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().setPersonName(pbean.getName());
				}else{
					projectSummaryBeanList.get(i).getPersonStaffingBean().getPerson().setPersonName("N.A");			}
			}
		}
		

		

			i++;
		} while (i < projectSummaryBeanList.size());
		
		return projectSummaryBeanMap;
		
	}


}
