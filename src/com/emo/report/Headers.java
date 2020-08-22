package com.emo.report;

public enum Headers {

	TOC("Table of Contents", "resources/images/toc.png"),
	EXEC_SUMMARY("Executive Summary", "resources/images/exec_summary.png"),
	RESULTS_DETAIL("Results Detail", "resources/images/results_detail.png"),
	LEADERSHIP_STYLE("Leadership Style", "resources/images/leadership_style.png"),
	DEVELOPMENT_PLAN("Development Plan", "resources/images/development_plan.png"),
	NEXT_STEPS("Next Steps", "resources/images/next_steps.png");

	private String title = "";
	private String img = "";

	Headers(String title, String img) {
		this.title = title;
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
