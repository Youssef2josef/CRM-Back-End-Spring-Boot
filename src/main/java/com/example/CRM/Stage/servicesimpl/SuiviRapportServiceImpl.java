package com.example.CRM.Stage.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRM.Stage.models.SuiviRapport;
import com.example.CRM.Stage.repositories.SuiviRapportRepository;
import com.example.CRM.Stage.services.SuiviRapportService;

@Service
public class SuiviRapportServiceImpl implements SuiviRapportService{

	@Autowired
	private SuiviRapportRepository suiviRapportRepo;

	@Override
	public List<SuiviRapport> getAllSuiviRapports() {
		// TODO Auto-generated method stub
		return suiviRapportRepo.findAll();
	}

	@Override
	public SuiviRapport getSuiviRapportById(Long id) {
		// TODO Auto-generated method stub
		Optional<SuiviRapport> m = suiviRapportRepo.findById(id);
		return m.isPresent() ? m.get() : null;
	}

	@Override
	public void deleteSuiviRapport(Long id) {
		// TODO Auto-generated method stub
		suiviRapportRepo.deleteById(id);
	}

	@Override
	public SuiviRapport updateOneSuiviRapport(SuiviRapport m) {
		// TODO Auto-generated method stub
		return suiviRapportRepo.save(m);
	}

	@Override
	public SuiviRapport addOneSuiviRapport(SuiviRapport m) {
		// TODO Auto-generated method stub
		return suiviRapportRepo.save(m);
	}
}
