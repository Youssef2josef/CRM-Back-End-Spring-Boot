package com.example.CRM.Stage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRM.Stage.models.RegionModel;

@Repository
public interface RegionRepository extends JpaRepository<RegionModel, Long>{

	Boolean existsByNom(String nom);
	Boolean existsById(String nom);
	Optional<RegionModel> findByNom(String nom);
	Optional<RegionModel> findById(String nom);	

}
