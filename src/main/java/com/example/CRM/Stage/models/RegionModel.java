package com.example.CRM.Stage.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.persistence.CascadeType;

import org.hibernate.annotations.*;

@Entity
@Table(name="region")
public class RegionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="REGION_ID")
	private Long id;
    private String nom;

    
	public RegionModel() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegionModel(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "RegionModel [id=" + id + ", nom=" + nom + "]";
	}
}
