package com.example.CRM.Stage.models;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SuiviRapportRequest {

	private boolean valid;
	@NotNull
	private LocalDate date;
	private String text;
	@Email
	private String userEmail;
	private String regionNom;
	private String objectNom;
	private String clientNom;
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public SuiviRapportRequest() {
	}
	public SuiviRapportRequest(boolean valid, @NotNull LocalDate date, String text, @Email String userEmail,
			String regionNom, String objectNom, String clientNom) {
		this.valid = valid;
		this.date = date;
		this.text = text;
		this.userEmail = userEmail;
		this.regionNom = regionNom;
		this.objectNom = objectNom;
		this.clientNom = clientNom;
	}

	
}
