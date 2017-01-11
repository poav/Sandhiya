package com.sapient.statestreetscreeningapplication.ui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjectBean.
 */
public class ProjectBean {
	
	/** The Id. */
	private long Id;
	
	/** The account division. */
	private String accountDivision;
	
	/** The project name. */
	private String projectName;
	
	/** The is used. */
	private int isUsed;//0-unused,1-used
	
	/** The pid. */
	private int PID;
	
	/** The IQN number. */
	private int IQNNumber;
	
	/** The IQN name. */
	private String IQNName;
	
	/** The sape start date. */
	private Date sapeStartDate;
	
	/** The sape end date. */
	private Date sapeEndDate;
	
	/** The sape pm. */
	private int sapePM;
	
	/**
	 * Gets the dl list1.
	 *
	 * @return the dl list1
	 */
	public List<DLBean> getDlList1() {
		return dlList1;
	}

	/**
	 * Sets the dl list1.
	 *
	 * @param dlList1 the new dl list1
	 */
	public void setDlList1(List<DLBean> dlList1) {
		this.dlList1 = dlList1;
	}

	
	/**
	 * Gets the account division.
	 *
	 * @return the account division
	 */
	public String getAccountDivision() {
		return accountDivision;
	}

	/**
	 * Sets the account division.
	 *
	 * @param accountDivision the new account division
	 */
	public void setAccountDivision(String accountDivision) {
		this.accountDivision = accountDivision;
	}


	/** The attrack number. */
	private String attrackNumber;
	
	/** The attrack name. */
	private String attrackName;
    
    /** The dl list1. */
    private List<DLBean> dlList1=new ArrayList<DLBean>();
	
	/**
	 * Gets the pid.
	 *
	 * @return the pid
	 */
	public int getPID() {
		return PID;
	}

	/**
	 * Sets the pid.
	 *
	 * @param pID the new pid
	 */
	public void setPID(int pID) {
		PID = pID;
	}

	/**
	 * Gets the IQN number.
	 *
	 * @return the IQN number
	 */
	public int getIQNNumber() {
		return IQNNumber;
	}

	/**
	 * Sets the IQN number.
	 *
	 * @param iQNNumber the new IQN number
	 */
	public void setIQNNumber(int iQNNumber) {
		IQNNumber = iQNNumber;
	}

	/**
	 * Gets the sape start date.
	 *
	 * @return the sape start date
	 */
	public Date getSapeStartDate() {
		return sapeStartDate;
	}

	/**
	 * Sets the sape start date.
	 *
	 * @param sapeStartDate the new sape start date
	 */
	public void setSapeStartDate(Date sapeStartDate) {
		this.sapeStartDate = sapeStartDate;
	}

	/**
	 * Gets the sape end date.
	 *
	 * @return the sape end date
	 */
	public Date getSapeEndDate() {
		return sapeEndDate;
	}

	/**
	 * Sets the sape end date.
	 *
	 * @param sapeEndDate the new sape end date
	 */
	public void setSapeEndDate(Date sapeEndDate) {
		this.sapeEndDate = sapeEndDate;
	}

	/**
	 * Gets the sape pm.
	 *
	 * @return the sape pm
	 */
	public int getSapePM() {
		return sapePM;
	}

	/**
	 * Sets the sape pm.
	 *
	 * @param sapePM the new sape pm
	 */
	public void setSapePM(int sapePM) {
		this.sapePM = sapePM;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * Gets the project name.
	 *
	 * @return the project name
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Sets the project name.
	 *
	 * @param projectName the new project name
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	/**
	 * Gets the checks if is used.
	 *
	 * @return the checks if is used
	 */
	public int getIsUsed() {
		return isUsed;
	}

	/**
	 * Sets the checks if is used.
	 *
	 * @param isUsed the new checks if is used
	 */
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	

	

	/**
	 * Gets the attrack number.
	 *
	 * @return the attrack number
	 */
	public String getAttrackNumber() {
		return attrackNumber;
	}

	/**
	 * Sets the attrack number.
	 *
	 * @param attrackNumber the new attrack number
	 */
	public void setAttrackNumber(String attrackNumber) {
		this.attrackNumber = attrackNumber;
	}

	/**
	 * Gets the attrack name.
	 *
	 * @return the attrack name
	 */
	public String getAttrackName() {
		return attrackName;
	}

	/**
	 * Sets the attrack name.
	 *
	 * @param attrackName the new attrack name
	 */
	public void setAttrackName(String attrackName) {
		this.attrackName = attrackName;
	}

	

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IQNName == null) ? 0 : IQNName.hashCode());
		result = prime * result + IQNNumber;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + PID;
		result = prime * result
				+ ((attrackName == null) ? 0 : attrackName.hashCode());
		result = prime * result
				+ ((attrackNumber == null) ? 0 : attrackNumber.hashCode());
		result = prime * result + isUsed;
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result
				+ ((sapeEndDate == null) ? 0 : sapeEndDate.hashCode());
		result = prime * result + sapePM;
		result = prime * result
				+ ((sapeStartDate == null) ? 0 : sapeStartDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectBean other = (ProjectBean) obj;
		if (IQNName == null) {
			if (other.IQNName != null)
				return false;
		} else if (!IQNName.equals(other.IQNName))
			return false;
		if (IQNNumber != other.IQNNumber)
			return false;
		if (Id != other.Id)
			return false;
		if (PID != other.PID)
			return false;
		if (attrackName == null) {
			if (other.attrackName != null)
				return false;
		} else if (!attrackName.equals(other.attrackName))
			return false;
		if (attrackNumber == null) {
			if (other.attrackNumber != null)
				return false;
		} else if (!attrackNumber.equals(other.attrackNumber))
			return false;
		if (isUsed != other.isUsed)
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (sapeEndDate == null) {
			if (other.sapeEndDate != null)
				return false;
		} else if (!sapeEndDate.equals(other.sapeEndDate))
			return false;
		if (sapePM != other.sapePM)
			return false;
		if (sapeStartDate == null) {
			if (other.sapeStartDate != null)
				return false;
		} else if (!sapeStartDate.equals(other.sapeStartDate))
			return false;
		return true;
	}

	/**
	 * Instantiates a new project bean.
	 */
	public ProjectBean() {
		Id = 0;
		projectName = "";
		isUsed=0;
		PID=0;
		IQNNumber=0;
		IQNName="";
		sapeEndDate=null;
		sapePM=0;
		sapeStartDate=null;
		attrackNumber="";
		attrackName="";
	}

	/**
	 * Gets the IQN name.
	 *
	 * @return the IQN name
	 */
	public String getIQNName() {
		return IQNName;
	}

	/**
	 * Sets the IQN name.
	 *
	 * @param iQNName the new IQN name
	 */
	public void setIQNName(String iQNName) {
		IQNName = iQNName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectBean [Id=" + Id + ", projectName=" + projectName
				+ ", isUsed=" + isUsed + ", PID=" + PID + ", IQNNumber="
				+ IQNNumber + ", IQNName=" + IQNName + ", sapeStartDate="
				+ sapeStartDate + ", sapeEndDate=" + sapeEndDate + ", sapePM="
				+ sapePM + ", attrackNumber=" + attrackNumber
				+ ", attrackName=" + attrackName + "]";
	}	
}
