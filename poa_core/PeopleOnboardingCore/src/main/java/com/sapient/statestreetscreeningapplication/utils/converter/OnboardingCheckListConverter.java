package com.sapient.statestreetscreeningapplication.utils.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sapient.statestreetscreeningapplication.model.entity.OnboardingCheckList;
import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListLeadsBean;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingCheckListConverter.
 */
public class OnboardingCheckListConverter {

	/**
	 * Convert onboarding check list bean to onboarding check list entity.
	 *
	 * @param bean
	 *            the bean
	 * @return the onboarding check list
	 */
	public static OnboardingCheckList convertOnboardingCheckListBeanToOnboardingCheckListEntity(
			OnboardingCheckListBean bean) {
		OnboardingCheckList entity = new OnboardingCheckList();
		if (bean.getPersonScreeningDetails() != null) {
			entity.setPersonScreeningDetails(PersonScreeningDetailsConvertor
					.PersonScreeningDetailsBeanToPersonScreeningDetailsEntity(bean.getPersonScreeningDetails()));
		}
		if (bean.getPerson() != null) {
			entity.setPerson(PersonNewConverter.personBeanToEntity(bean.getPerson()));
		}

		if (bean.getInitiator() != null) {
			entity.setInitiator(PersonNewConverter.personBeanToEntity(bean.getInitiator()));
		}
		entity.setOnboardingCheckListId(bean.getOnboardingCheckListId());
		if (bean.getAddedInDl() != null && bean.getAddedInDl().equalsIgnoreCase("yes"))
			entity.setAddedInDl(true);
		else
			entity.setAddedInDl(false);
		if (bean.getAddedInRS3() != null && bean.getAddedInRS3().equalsIgnoreCase("yes"))
			entity.setAddedInRS3(true);
		else
			entity.setAddedInRS3(false);
		if (bean.getBackgroundCheckStatus() != null)
			entity.setBackgroundCheckStatus(bean.getBackgroundCheckStatus());
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			if (bean.getBuildingAccessRequestDate() != null)
				entity.setBuildingAccessRequestDate(formatter.parse(bean.getBuildingAccessRequestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if(bean.getBackgroundCheckDoneDate()!=null)
				entity.setDateBackgroundCheckDone(bean.getBackgroundCheckDoneDate());
			else
			if (bean.getDateBackgroundCheckDone() != null)
				entity.setDateBackgroundCheckDone(formatter.parse(bean.getDateBackgroundCheckDone()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if(bean.getBackgroundCheckSubmittedDate()!=null)
				entity.setDateBackgroundCheckSubmitted(bean.getBackgroundCheckSubmittedDate());
			else
			if (bean.getDateBackgroundCheckSubmitted() != null)
				entity.setDateBackgroundCheckSubmitted(formatter.parse(bean.getDateBackgroundCheckSubmitted()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if(bean.getDateOnboardingInitiatedDate()!=null)
				entity.setDateOnboardingInitiated(bean.getDateOnboardingInitiatedDate());
			else
			if (bean.getDateOnboardingInitiated() != null)
				entity.setDateOnboardingInitiated(formatter.parse(bean.getDateOnboardingInitiated()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (bean.getPersonAddedInClientVendorManagementSystemDate() != null)
				entity.setDatePersonAddedInClientVendorManagementSystem(
						bean.getPersonAddedInClientVendorManagementSystemDate());
			else
			if (bean.getDatePersonAddedInClientVendorManagementSystem() != null)
				entity.setDatePersonAddedInClientVendorManagementSystem(
						formatter.parse(bean.getDatePersonAddedInClientVendorManagementSystem()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (bean.getPersonApprovedInClientVendorManagementSystemDate() != null)
				entity.setDatePersonApprovedInClientVendorManagementSystem(
						bean.getPersonApprovedInClientVendorManagementSystemDate());
			else
			if (bean.getDatePersonApprovedInClientVendorManagementSystem() != null)
				entity.setDatePersonApprovedInClientVendorManagementSystem(
						formatter.parse(bean.getDatePersonApprovedInClientVendorManagementSystem()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (bean.getFingerPrintDate() != null)
				entity.setFingerPrintDate(formatter.parse(bean.getFingerPrintDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (bean.isHasPreviouslyWorkedForClient())
			entity.setHasPreviouslyWorkedForClient(true);
		else
			entity.setHasPreviouslyWorkedForClient(false);
		if (bean.isHasSapientLaptop())
			entity.setHasSapientLaptop(true);
		else
			entity.setHasSapientLaptop(false);
		if (bean.getIsOnsite() != null && bean.getIsOnsite().equalsIgnoreCase("yes"))
			entity.setIsOnsite(true);
		else
			entity.setIsOnsite(false);

		if (bean.getMachineName() != null)
			entity.setMachineName(bean.getMachineName());

		try {
			if (bean.getMachineRequestDate() != null)
				entity.setMachineRequestDate(formatter.parse(bean.getMachineRequestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setMachineRequestId(bean.getMachineRequestId());
		try {
			if (bean.getOfficeAccessRequestDate() != null)
				entity.setOfficeAccessRequestDate(formatter.parse(bean.getOfficeAccessRequestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setOnboardingStatus(bean.getOnboardingStatus());
		entity.setPersonStaffing(PersonStaffingConverter.personStaffingBeanToEntity(bean.getPersonStaffingBean()));
		try {
			if (bean.getRampupInitiatedDate() != null)
				entity.setRampupInitiatedDate(formatter.parse(bean.getRampupInitiatedDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (bean.getTimeOffToolRequestDate() != null)
				entity.setTimeOffToolRequestDate(formatter.parse(bean.getTimeOffToolRequestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			if (bean.getUserAccessRequestDate() != null)
				entity.setUserAccessRequestDate(formatter.parse(bean.getUserAccessRequestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		entity.setUserAccessRequestId(bean.getUserAccessRequestId());

		return entity;

	}

	public static OnboardingCheckListBean convertOnboardingCheckListEntityToOnboardingCheckListBean(
			OnboardingCheckList entity) {
		OnboardingCheckListBean bean = new OnboardingCheckListBean();
		bean.setPerson(PersonNewConverter.personNewEntityToBean(entity.getPerson()));
		bean.setOnboardingCheckListId(entity.getOnboardingCheckListId());
		if (entity.getInitiator() != null) {
			bean.setInitiator(PersonNewConverter.personNewEntityToBean(entity.getInitiator()));
		}
		if (entity.getAddedInDl())
			bean.setAddedInDl("Yes");
		else
			bean.setAddedInDl("No");
		if (entity.getIsOnsite())
			bean.setIsOnsite("Yes");
		else
			bean.setIsOnsite("No");

		if (entity.getAddedInRS3())
			bean.setAddedInRS3("Yes");
		else
			bean.setAddedInRS3("No");
		bean.setBackgroundCheckStatus(entity.getBackgroundCheckStatus());
		SimpleDateFormat formatter;
		Date currentDate = new Date();
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		if (entity.getBuildingAccessRequestDate() != null)
		{
			
			bean.setBuildingAccessRequestDate(formatter.format(entity.getBuildingAccessRequestDate()));
		}
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		if (entity.getDateBackgroundCheckDone() != null)
		{
			bean.setBackgroundCheckDoneDate(entity.getDateBackgroundCheckDone());
			bean.setDateBackgroundCheckDone(formatter.format(entity.getDateBackgroundCheckDone()));
		}
		if (entity.getDateBackgroundCheckSubmitted() != null)
		{
			bean.setBackgroundCheckSubmittedDate(entity.getDateBackgroundCheckSubmitted());
			bean.setDateBackgroundCheckSubmitted(formatter.format(entity.getDateBackgroundCheckSubmitted()));
		}

		if (entity.getDateOnboardingInitiated() != null) {
			bean.setDateOnboardingInitiatedDate(entity.getDateOnboardingInitiated());
			bean.setDateOnboardingInitiated(formatter.format(entity.getDateOnboardingInitiated()));
			float var = currentDate.getTime() - entity.getDateOnboardingInitiated().getTime();
			var = var / 1000;
			var = var / 3600;
			var = var / 24;
			var = var / 365;
			var = var * 12;

			bean.setAccountTenure((long)var);
		}
		if (entity.getDatePersonAddedInClientVendorManagementSystem() != null)
		{
			bean.setPersonAddedInClientVendorManagementSystemDate(entity.getDatePersonAddedInClientVendorManagementSystem());
			bean.setDatePersonAddedInClientVendorManagementSystem(
					formatter.format(entity.getDatePersonAddedInClientVendorManagementSystem()));
			
		}
		if (entity.getDatePersonApprovedInClientVendorManagementSystem() != null)
		{
			bean.setDatePersonApprovedInClientVendorManagementSystem(
					formatter.format(entity.getDatePersonApprovedInClientVendorManagementSystem()));
			bean.setPersonApprovedInClientVendorManagementSystemDate(entity.getDatePersonApprovedInClientVendorManagementSystem());
		}
		if (entity.getFingerPrintDate() != null)
			bean.setFingerPrintDate(formatter.format(entity.getFingerPrintDate()));
		if (entity.getHasPreviouslyWorkedForClient())
			bean.setHasPreviouslyWorkedForClient(true);
		else
			bean.setHasPreviouslyWorkedForClient(false);

		if (entity.getHasSapientLaptop())
			bean.setHasSapientLaptop(true);
		else
			bean.setHasSapientLaptop(false);
		if (entity.getMachineName() != null)
			bean.setMachineName(entity.getMachineName());
		if (entity.getMachineRequestDate() != null)
			bean.setMachineRequestDate(formatter.format(entity.getMachineRequestDate()));
		bean.setMachineRequestId(entity.getMachineRequestId());
		if (entity.getOfficeAccessRequestDate() != null)
			bean.setOfficeAccessRequestDate(formatter.format(entity.getOfficeAccessRequestDate()));

		bean.setOnboardingStatus(entity.getOnboardingStatus());

		bean.setPersonStaffingBean(PersonStaffingConverter.personStaffingEntityToBean(entity.getPersonStaffing()));
		bean.setPersonScreeningDetails(
				PersonScreeningDetailsConvertor.PersonScreeningDetailsEntityToBean(entity.getPersonScreeningDetails()));
		if (entity.getRampupInitiatedDate() != null)
			bean.setRampupInitiatedDate(formatter.format(entity.getRampupInitiatedDate()));

		if (entity.getTimeOffToolRequestDate() != null)
			bean.setTimeOffToolRequestDate(formatter.format(entity.getTimeOffToolRequestDate()));

		if (entity.getUserAccessRequestDate() != null)
			bean.setUserAccessRequestDate(formatter.format(entity.getUserAccessRequestDate()));
		bean.setUserAccessRequestId(entity.getUserAccessRequestId());

		return bean;

	}

	public static List<OnboardingCheckListBean> convertOnboardingCheckListEntityListToOnboardingCheckListBeanList(
			List<OnboardingCheckList> entityList) {
		List<OnboardingCheckListBean> beanList = new ArrayList<OnboardingCheckListBean>();
		for (OnboardingCheckList entity : entityList) {

			beanList.add(convertOnboardingCheckListEntityToOnboardingCheckListBean((entity)));

		}
		return beanList;
	}
}
