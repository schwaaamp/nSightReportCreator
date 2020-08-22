package com.emo.domain;

import java.util.List;

public class AssessmentScores {
	
	public String clientName = "";
	public List<Category> categories;
	
	public AssessmentScores() {}
	
	public AssessmentScores(String clientName, List<Category> categories) {
		super();
		this.clientName = clientName;
		this.categories = categories;
	}

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
