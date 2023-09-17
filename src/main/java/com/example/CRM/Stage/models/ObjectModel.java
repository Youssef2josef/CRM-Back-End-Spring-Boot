package com.example.CRM.Stage.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="object")
public class ObjectModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OBJECT_ID") 
	private Long id;
	private String nom;
	
	public ObjectModel() {
	}
	
	public ObjectModel(String nom) {
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
	

	@Override
	public String toString() {
		return "ObjectModel [id=" + id + ", nom=" + nom + "]";
	}
}
