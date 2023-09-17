package com.example.CRM.Stage.models;

import javax.validation.constraints.NotBlank;

public class RegionRequest {

	private Long id;
	@NotBlank
	private String nom;
	
	public RegionRequest() {
	}

	public RegionRequest(@NotBlank String nom, @NotBlank String description) {
		this.nom = nom;
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
