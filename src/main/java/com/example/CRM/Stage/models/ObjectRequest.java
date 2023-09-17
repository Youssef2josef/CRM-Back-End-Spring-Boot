package com.example.CRM.Stage.models;

import javax.validation.constraints.NotBlank;

public class ObjectRequest {

	private Long id;
	@NotBlank
	private String nom;
	public ObjectRequest(@NotBlank String nom) {
		this.nom = nom;
	}
	public ObjectRequest() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
}
