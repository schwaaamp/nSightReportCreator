package com.emo.domain;

import java.util.List;

public enum Category {

	SELF_PERCEPTION("Self-Perception"), THINKING_STYLE("Thinking Style"), DRIVE("Drive"), STRESS("Stress"),
	COMMUNICATION("Communication"), LEADERSHIP("Leadership"), RELIABILITY("Reliability"), APTITUDE_PROFILE("Aptitude Profile");

	private String categoryName = "";
	private List<Trait> categoryTraits;

	Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Trait> getCategoryTraits() {

		return categoryTraits;
	}

	public void setCategoryTraits(List<Trait> categoryTraits) {
		this.categoryTraits = categoryTraits;
	}

}
