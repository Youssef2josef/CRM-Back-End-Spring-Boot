package com.example.CRM.Stage.services;

import java.util.List;

import com.example.CRM.Stage.models.SuiviRapport;

public interface SuiviRapportService {


	public List<SuiviRapport> getAllSuiviRapports();
	
	public SuiviRapport getSuiviRapportById(Long id);
	
	public void deleteSuiviRapport(Long id);
	
	public SuiviRapport updateOneSuiviRapport(SuiviRapport p);

	public SuiviRapport addOneSuiviRapport(SuiviRapport p);
}