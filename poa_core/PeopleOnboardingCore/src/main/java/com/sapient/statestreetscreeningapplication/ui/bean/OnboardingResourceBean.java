package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sapient.statestreetscreeningapplication.utils.enums.PSIDStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class OnboardingResourceBean.
 */
public class OnboardingResourceBean {
	
	/** The onboarding resource id. */
	private long onboardingResourceId;
	
	/** The current metro. */
	private String currentMetro;
	
	/** The home metro. */
	private String homeMetro;
	
	/** The office. */
	private String office;
	
	/** The interviewee. */
	private IntervieweeBean interviewee;
	
	/** The traveller status. */
	private String travellerStatus;
	
	/** The added in dl. */
	private String addedInDL;
	
	/** The added in r s3. */
	private String addedInRS3;
	
	/** The psid. */
	private int PSID;
	
	/** The psid status. */
	private PSIDStatus psidStatus;
	
	/** The IQN resource id. */
	private int IQNResourceId;
	
	/** The ss start date. */
	private Date ssStartDate;
	
	/** The IQN end date. */
	private Date IQNEndDate;
	
	/** The domain. */
	private String domain;
	
	/** The rate category. */
	private String rateCategory;
	
	/** The supervisor. */
	private String supervisor;
	
	/** The string ss start date. */
	private String stringSSStartDate;
	
	/** The string iqn end date. */
	private String stringIQNEndDate;
	
	/** The string birth date. */
	private String stringBirthDate;
	
	/** The dl names. */
	private String dlNames;
	
	/** The active. */
	private boolean active;
	
	/** The comments. */
	private String comments;
	
	/** The has previously assigned at ss. */
	private boolean hasPreviouslyAssignedAtSS;
	
	/** The onsite at ss. */
	private boolean onsiteAtSS;
	
	/** The has sapient laptop. */
	private boolean hasSapientLaptop;
	
	/** The lead oracle id. */
	private int leadOracleId;
    
    /** The dls. */
    private Set<DLBean> dls = new HashSet<DLBean>(); 
	
	/** The home geo. */
	private String homeGeo;
	
	/** The current geo. */
	private String currentGeo;
	
	/** The supervisor nt id. */
	private String supervisorNtId;
	
	/** The monthly rate. */
	private float monthlyRate;
	
	/**
	 * Gets the dl names.
	 *
	 * @return the dl names
	 */
	public String getDlNames() {
		return dlNames;
	}
	
	/**
	 * Sets the dl names.
	 *
	 * @param dlNames the new dl names
	 */
	public void setDlNames(String dlNames) {
		this.dlNames = dlNames;
	}
	
	/**
	 * Gets the string birth date.
	 *
	 * @return the string birth date
	 */
	public String getStringBirthDate() {
		return stringBirthDate;
	}
	
	/**
	 * Sets the string birth date.
	 *
	 * @param stringBirthDate the new string birth date
	 */
	public void setStringBirthDate(String stringBirthDate) {
		this.stringBirthDate = stringBirthDate;
	}
	
	/**
	 * Gets the string ss start date.
	 *
	 * @return the string ss start date
	 */
	public String getStringSSStartDate() {
		return stringSSStartDate;
	}
	
	/**
	 * Sets the string ss start date.
	 *
	 * @param stringSSStartDate the new string ss start date
	 */
	public void setStringSSStartDate(String stringSSStartDate) {
		this.stringSSStartDate = stringSSStartDate;
	}
	
	/**
	 * Gets the string iqn end date.
	 *
	 * @return the string iqn end date
	 */
	public String getStringIQNEndDate() {
		return stringIQNEndDate;
	}
	
	/**
	 * Sets the string iqn end date.
	 *
	 * @param stringIQNEndDate the new string iqn end date
	 */
	public void setStringIQNEndDate(String stringIQNEndDate) {
		this.stringIQNEndDate = stringIQNEndDate;
	}
	
	/**
	 * Gets the supervisor.
	 *
	 * @return the supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}
	
	/**
	 * Sets the supervisor.
	 *
	 * @param supervisor the new supervisor
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	
	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	/**
	 * Gets the rate category.
	 *
	 * @return the rate category
	 */
	public String getRateCategory() {
		return rateCategory;
	}
	
	/**
	 * Sets the rate category.
	 *
	 * @param rateCategory the new rate category
	 */
	public void setRateCategory(String rateCategory) {
		this.rateCategory = rateCategory;
	}
	
	/**
	 * Gets the dls.
	 *
	 * @return the dls
	 */
	public Set<DLBean> getDls() {
		return dls;
	}
	
	/**
	 * Sets the dls.
	 *
	 * @param dls the new dls
	 */
	public void setDls(Set<DLBean> dls) {
		this.dls = dls;
	}
	
	/** The account tenure. */
	private double accountTenure;
	
	/**
	 * Gets the supervisor nt id.
	 *
	 * @return the supervisor nt id
	 */
	public String getSupervisorNtId() {
		return supervisorNtId;
	}
	
	/**
	 * Sets the supervisor nt id.
	 *
	 * @param supervisorNtId the new supervisor nt id
	 */
	public void setSupervisorNtId(String supervisorNtId) {
		this.supervisorNtId = supervisorNtId;
	}
	
	/**
	 * Checks if is checks for previously assigned at ss.
	 *
	 * @return true, if is checks for previously assigned at ss
	 */
	public boolean isHasPreviouslyAssignedAtSS() {
		return hasPreviouslyAssignedAtSS;
	}
	
	/**
	 * Sets the checks for previously assigned at ss.
	 *
	 * @param hasPreviouslyAssignedAtSS the new checks for previously assigned at ss
	 */
	public void setHasPreviouslyAssignedAtSS(boolean hasPreviouslyAssignedAtSS) {
		this.hasPreviouslyAssignedAtSS = hasPreviouslyAssignedAtSS;
	}
	
	/**
	 * Checks if is onsite at ss.
	 *
	 * @return true, if is onsite at ss
	 */
	public boolean isOnsiteAtSS() {
		return onsiteAtSS;
	}
	
	/**
	 * Sets the onsite at ss.
	 *
	 * @param isOnsiteAtSS the new onsite at ss
	 */
	public void setOnsiteAtSS(boolean isOnsiteAtSS) {
		this.onsiteAtSS = isOnsiteAtSS;
	}
	
	/**
	 * Checks if is checks for sapient laptop.
	 *
	 * @return true, if is checks for sapient laptop
	 */
	public boolean isHasSapientLaptop() {
		return hasSapientLaptop;
	}
	
	/**
	 * Sets the checks for sapient laptop.
	 *
	 * @param hasSapientLaptop the new checks for sapient laptop
	 */
	public void setHasSapientLaptop(boolean hasSapientLaptop) {
		this.hasSapientLaptop = hasSapientLaptop;
	}
	
	/**
	 * Gets the traveller status.
	 *
	 * @return the traveller status
	 */
	public String getTravellerStatus() {
		return travellerStatus;
	}
	
	/**
	 * Sets the traveller status.
	 *
	 * @param travellerStatus the new traveller status
	 */
	public void setTravellerStatus(String travellerStatus) {
		this.travellerStatus = travellerStatus;
	}
	
	/**
	 * Gets the added in dl.
	 *
	 * @return the added in dl
	 */
	public String getAddedInDL() {
		return addedInDL;
	}
	
	/**
	 * Sets the added in dl.
	 *
	 * @param addedInDL the new added in dl
	 */
	public void setAddedInDL(String addedInDL) {
		this.addedInDL = addedInDL;
	}
	
	/**
	 * Gets the psid.
	 *
	 * @return the psid
	 */
	public int getPSID() {
		return PSID;
	}
	
	/**
	 * Sets the psid.
	 *
	 * @param pSID the new psid
	 */
	public void setPSID(int pSID) {
		PSID = pSID;
	}
	
	/**
	 * Gets the IQN resource id.
	 *
	 * @return the IQN resource id
	 */
	public int getIQNResourceId() {
		return IQNResourceId;
	}
	
	/**
	 * Sets the IQN resource id.
	 *
	 * @param iQNResourceId the new IQN resource id
	 */
	public void setIQNResourceId(int iQNResourceId) {
		IQNResourceId = iQNResourceId;
	}
	
	/**
	 * Gets the ss start date.
	 *
	 * @return the ss start date
	 */
	public Date getSsStartDate() {
		return ssStartDate;
	}
	
	/**
	 * Sets the ss start date.
	 *
	 * @param ssStartDate the new ss start date
	 */
	public void setSsStartDate(Date ssStartDate) {
		this.ssStartDate = ssStartDate;
	}
	
	/**
	 * Gets the IQN end date.
	 *
	 * @return the IQN end date
	 */
	public Date getIQNEndDate() {
		return IQNEndDate;
	}
	
	/**
	 * Sets the IQN end date.
	 *
	 * @param iQNEndDate the new IQN end date
	 */
	public void setIQNEndDate(Date iQNEndDate) {
		IQNEndDate = iQNEndDate;
	}
	
	/**
	 * Gets the account tenure.
	 *
	 * @return the account tenure
	 */
	public double getAccountTenure() {
		return accountTenure;
	}
	
	/**
	 * Sets the account tenure.
	 *
	 * @param accountTenure the new account tenure
	 */
	public void setAccountTenure(double accountTenure) {
		this.accountTenure = accountTenure;
	}
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets the active.
	 *
	 * @param isActive the new active
	 */
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	
	/**
	 * Gets the onboarding resource id.
	 *
	 * @return the onboarding resource id
	 */
	public long getOnboardingResourceId() {
		return onboardingResourceId;
	}
	
	/**
	 * Sets the onboarding resource id.
	 *
	 * @param onboardingResourceId the new onboarding resource id
	 */
	public void setOnboardingResourceId(long onboardingResourceId) {
		this.onboardingResourceId = onboardingResourceId;
	}
	
	/**
	 * Gets the interviewee.
	 *
	 * @return the interviewee
	 */
	public IntervieweeBean getInterviewee() {
		return interviewee;
	}
	
	/**
	 * Sets the interviewee.
	 *
	 * @param interviewee the new interviewee
	 */
	public void setInterviewee(IntervieweeBean interviewee) {
		this.interviewee = interviewee;
	}
	
	/**
	 * Gets the added in r s3.
	 *
	 * @return the added in r s3
	 */
	public String getAddedInRS3() {
		return addedInRS3;
	}
	
	/**
	 * Sets the added in r s3.
	 *
	 * @param addedInRS3 the new added in r s3
	 */
	public void setAddedInRS3(String addedInRS3) {
		this.addedInRS3 = addedInRS3;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the current metro.
	 *
	 * @return the current metro
	 */
	public String getCurrentMetro() {
		return currentMetro;
	}
	
	/**
	 * Sets the current metro.
	 *
	 * @param currentMetro the new current metro
	 */
	public void setCurrentMetro(String currentMetro) {
		this.currentMetro = currentMetro;
	}
	
	/**
	 * Gets the home metro.
	 *
	 * @return the home metro
	 */
	public String getHomeMetro() {
		return homeMetro;
	}
	
	/**
	 * Sets the home metro.
	 *
	 * @param homeMetro the new home metro
	 */
	public void setHomeMetro(String homeMetro) {
		this.homeMetro = homeMetro;
	}
	
	/**
	 * Gets the office.
	 *
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	
	/**
	 * Sets the office.
	 *
	 * @param office the new office
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	
	/**
	 * Gets the psid status.
	 *
	 * @return the psid status
	 */
	public PSIDStatus getPsidStatus() {
		return psidStatus;
	}
	
	/**
	 * Sets the psid status.
	 *
	 * @param psidStatus the new psid status
	 */
	public void setPsidStatus(PSIDStatus psidStatus) {
		this.psidStatus = psidStatus;
	}
	
	/**
	 * Gets the lead oracle id.
	 *
	 * @return the lead oracle id
	 */
	public int getLeadOracleId() {
		return leadOracleId;
	}
	
	/**
	 * Sets the lead oracle id.
	 *
	 * @param leadOracleId the new lead oracle id
	 */
	public void setLeadOracleId(int leadOracleId) {
		this.leadOracleId = leadOracleId;
	}
	
	/**
	 * Gets the current geo.
	 *
	 * @return the current geo
	 */
	public String getCurrentGeo() {
		return currentGeo;
	}
	
	/**
	 * Sets the current geo.
	 *
	 * @param currentGeo the new current geo
	 */
	public void setCurrentGeo(String currentGeo) {
		this.currentGeo = currentGeo;
	}
	
	/**
	 * Gets the home geo.
	 *
	 * @return the home geo
	 */
	public String getHomeGeo() {
		return homeGeo;
	}
	
	/**
	 * Sets the home geo.
	 *
	 * @param homeGeo the new home geo
	 */
	public void setHomeGeo(String homeGeo) {
		this.homeGeo = homeGeo;
	}
	
	/**
	 * Gets the monthly rate.
	 *
	 * @return the monthly rate
	 */
	public float getMonthlyRate() {
		return monthlyRate;
	}
	
	/**
	 * Sets the monthly rate.
	 *
	 * @param monthlyRate the new monthly rate
	 */
	public void setMonthlyRate(float monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
}
