package com.example.CRM.Stage.models;

public class RegionUpdate {

	private Long id;
	private String nom;
	
	public RegionUpdate() {
	}
	
	public RegionUpdate(String nom) {
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
