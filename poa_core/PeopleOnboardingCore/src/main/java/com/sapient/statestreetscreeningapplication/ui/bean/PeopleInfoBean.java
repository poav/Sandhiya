package com.sapient.statestreetscreeningapplication.ui.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class PeopleInfoBean.
 */
public class PeopleInfoBean {

/** The onboarding resource bean. */
private OnboardingResourceBean onboardingResourceBean;

/** The onboarding check list ops bean. */
private OnboardingCheckListOpsBean onboardingCheckListOpsBean;

/** The onboarding check list leads bean. */
private OnboardingCheckListLeadsBean onboardingCheckListLeadsBean;

/**
 * Instantiates a new people info bean.
 */
public PeopleInfoBean() {
	onboardingResourceBean = new OnboardingResourceBean();
	onboardingCheckListOpsBean = new OnboardingCheckListOpsBean();
	onboardingCheckListLeadsBean = new OnboardingCheckListLeadsBean();
}

/**
 * Gets the onboarding resource bean.
 *
 * @return the onboarding resource bean
 */
public OnboardingResourceBean getOnboardingResourceBean() {
	return onboardingResourceBean;
}

/**
 * Sets the onboarding resource bean.
 *
 * @param onboardingResourceBean the new onboarding resource bean
 */
public void setOnboardingResourceBean(
		OnboardingResourceBean onboardingResourceBean) {
	this.onboardingResourceBean = onboardingResourceBean;
}

/**
 * Gets the onboarding check list ops bean.
 *
 * @return the onboarding check list ops bean
 */
public OnboardingCheckListOpsBean getOnboardingCheckListOpsBean() {
	return onboardingCheckListOpsBean;
}

/**
 * Sets the onboarding check list ops bean.
 *
 * @param onboardingCheckListOpsBean the new onboarding check list ops bean
 */
public void setOnboardingCheckListOpsBean(
		OnboardingCheckListOpsBean onboardingCheckListOpsBean) {
	this.onboardingCheckListOpsBean = onboardingCheckListOpsBean;
}

/**
 * Gets the onboarding check list leads bean.
 *
 * @return the onboarding check list leads bean
 */
public OnboardingCheckListLeadsBean getOnboardingCheckListLeadsBean() {
	return onboardingCheckListLeadsBean;
}

/**
 * Sets the onboarding check list leads bean.
 *
 * @param onboardingCheckListLeadsBean the new onboarding check list leads bean
 */
public void setOnboardingCheckListLeadsBean(
		OnboardingCheckListLeadsBean onboardingCheckListLeadsBean) {
	this.onboardingCheckListLeadsBean = onboardingCheckListLeadsBean;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "PeopleInfoBean [onboardingResourceBean=" + onboardingResourceBean
			+ ", onboardingCheckListOpsBean=" + onboardingCheckListOpsBean
			+ ", onboardingCheckListLeadsBean=" + onboardingCheckListLeadsBean
			+ "]";
}
}
