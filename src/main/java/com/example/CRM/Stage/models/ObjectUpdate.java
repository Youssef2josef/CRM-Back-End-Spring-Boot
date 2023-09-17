package com.example.CRM.Stage.models;

public class ObjectUpdate {
	
	private Long id;
	private String nom;
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
	
	public ObjectUpdate() {
	}
	public ObjectUpdate(String nom) {
		this.nom = nom;
	}
}
