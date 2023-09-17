package com.example.CRM.Stage.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="client", uniqueConstraints = { @UniqueConstraint(columnNames = "mail", name = "unique_mail") })
public class ClientModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CLIENT_ID")
	private Long id;
	@NotBlank
	private String name;
	private String contact1;
	private String tel1;
	private String contact2;
	private String tel2;
	@NotBlank
	@Email
	private String mail;
	private String region;
	private String pays;
    
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
 
	public ClientModel() {
	}
 
	public ClientModel(@NotBlank String name, String contact1, String tel1, String contact2, String tel2, @Email String mail,
			String region, String pays) {
		this.name = name;
		this.contact1 = contact1;
		this.tel1 = tel1;
		this.contact2 = contact2;
		this.tel2 = tel2;
		this.mail = mail;
		this.region = region;
		this.pays = pays;
	}
	@Override
	public String toString() {
		return "ClientModel [id=" + id + ", name=" + name + ", contact1=" + contact1 + ", tel1=" + tel1 + ", contact2="
				+ contact2 + ", tel2=" + tel2 + ", mail=" + mail + ", region=" + region + ", pays=" + pays + "]";
	}


	
}
