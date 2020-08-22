package com.emo.domain;

public class Client {
	
	private String clientName;
	private String gender;
	
	public Client(String clientName, String gender) {
		super();
		this.clientName = clientName;
		this.gender = gender;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	

}
