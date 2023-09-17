package com.example.CRM.Stage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRM.Stage.models.ObjectModel;

@Repository
public interface ObjectRepository extends JpaRepository<ObjectModel,Long> {

	Boolean existsByNom(String nom);
	Optional<ObjectModel> findByNom(String nom);
	Optional<ObjectModel> findById(String nom);	
	
}
