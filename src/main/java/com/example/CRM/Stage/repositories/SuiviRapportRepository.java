package com.example.CRM.Stage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRM.Stage.models.SuiviRapport;

@Repository
public interface SuiviRapportRepository extends JpaRepository<SuiviRapport, Long> {
	
}
