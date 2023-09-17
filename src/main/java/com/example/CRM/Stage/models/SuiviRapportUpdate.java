package com.example.CRM.Stage.models;

import java.time.LocalDate;

public class SuiviRapportUpdate {

	private Long id;
	private boolean valid;
	private String text;
	private LocalDate date;
	private String userEmail;
	private String regionNom;
	private String objectNom;
	private String clientNom;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getRegionNom() {
		return regionNom;
	}
	public void setRegionNom(String regionNom) {
		this.regionNom = regionNom;
	}
	public String getObjectNom() {
		return objectNom;
	}
	public void setObjectNom(String objectNom) {
		this.objectNom = objectNom;
	}
	public String getClientNom() {
		return clientNom;
	}
	public void setClientNom(String clientNom) {
		this.clientNom = clientNom;
	}	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public SuiviRapportUpdate() {
	}
	public SuiviRapportUpdate(boolean valid, String text, LocalDate date, String userEmail, String regionNom,
			String objectNom, String clientNom) {
		this.valid = valid;
		this.text = text;
		this.date = date;
		this.userEmail = userEmail;
		this.regionNom = regionNom;
		this.objectNom = objectNom;
		this.clientNom = clientNom;
	}

	
}
