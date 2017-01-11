package com.sapient.statestreetscreeningapplication.model.entity;

import java.util.List;

import com.sapient.statestreetscreeningapplication.ui.bean.OnboardingCheckListBean;
import com.sapient.statestreetscreeningapplication.ui.bean.PersonScreeningDetailsBean;
import com.sapient.statestreetscreeningapplication.ui.bean.ScoresNewBean;

public class ScreeningDetailsEntity {

	
	private List<ScoresNewBean> scoreListFromUI;
	public List<ScoresNewBean> getScoreListFromUI() {
		return scoreListFromUI;
	}
	public void setScoreListFromUI(List<ScoresNewBean> scoreList222) {
		this.scoreListFromUI = scoreList222;
	}
	private OnboardingCheckListBean onboardingCheckListBean;
	
	public OnboardingCheckListBean getOnboardingCheckListBean() {
		return onboardingCheckListBean;
	}
	public void setOnboardingCheckListBean(OnboardingCheckListBean onboardingCheckListBean) {
		this.onboardingCheckListBean = onboardingCheckListBean;
	}
	
	
	
	
}
