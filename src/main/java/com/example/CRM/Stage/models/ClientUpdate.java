package com.example.CRM.Stage.models;


public class ClientUpdate {

	private Long id;
	private String name;
	private String contact1;
	private String tel1;
	private String contact2;
	private String tel2;
	private String mail;
	private String region;
	private String pays;
	
	public ClientUpdate() {
	}

	public ClientUpdate(String name, String contact1, String tel1, String contact2, String tel2, String mail, String region,
			String pays) {
		this.name = name;
		this.contact1 = contact1;
		this.tel1 = tel1;
		this.contact2 = contact2;
		this.tel2 = tel2;
		this.mail = mail;
		this.region = region;
		this.pays = pays;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	
}
