package com.example.CRM.Stage.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRM.Stage.models.SuiviRapport;
import com.example.CRM.Stage.models.ClientModel;
import com.example.CRM.Stage.models.ObjectModel;
import com.example.CRM.Stage.models.RegionModel;
import com.example.CRM.Stage.models.SuiviRapportRequest;
import com.example.CRM.Stage.models.SuiviRapportUpdate;
import com.example.CRM.Stage.models.User;
import com.example.CRM.Stage.repositories.ClientRepository;
import com.example.CRM.Stage.repositories.ObjectRepository;
import com.example.CRM.Stage.repositories.RegionRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.SuiviRapportRepository;
import com.example.CRM.Stage.repositories.UserRepository;
import com.example.CRM.Stage.services.SuiviRapportService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/rapportSuivi")
public class SuiviRapportController {
   
	@Autowired
	public SuiviRapportService suiviRapportService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	ObjectRepository objectRepository;
	@Autowired
	SuiviRapportRepository suiviRapportRepository;
	public SuiviRapportRequest suiviRapportRequest;
	public SuiviRapportUpdate suiviRapportUpdate;
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@PostMapping
	public ResponseEntity<?> ajouterRapport(@Valid @RequestBody SuiviRapportRequest suiviRapportRequest) {
		
		ClientModel client = clientRepository.findByName(suiviRapportRequest.getClientNom())
				.orElseThrow(() -> new RuntimeException("Error: Client is not found."));
		ObjectModel object = objectRepository.findByNom(suiviRapportRequest.getObjectNom())
				.orElseThrow(() -> new RuntimeException("Error: Object is not found."));
		RegionModel region = regionRepository.findByNom(suiviRapportRequest.getRegionNom())
				.orElseThrow(() -> new RuntimeException("Error: Region is not found."));
		User user = userRepository.findByEmail(suiviRapportRequest.getUserEmail())
				.orElseThrow(() -> new RuntimeException("Error: User is not found."));
		SuiviRapport suiviRapport = new SuiviRapport(suiviRapportRequest.isValid(),suiviRapportRequest.getDate(),suiviRapportRequest.getText(),user,region,object,client);
		suiviRapportRepository.save(suiviRapport);
		suiviRapportService.addOneSuiviRapport(suiviRapport);
        return new ResponseEntity<>(suiviRapport, HttpStatus.OK);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@GetMapping
	public List<SuiviRapport> recupererAllRapports(){
    	
    	List<SuiviRapport> suiviRapports = suiviRapportService.getAllSuiviRapports();
        List<SuiviRapport> rapportsModifies = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (SuiviRapport suiviRapport : suiviRapports) {
        	if(suiviRapport.isValid() == true) {	
        	}else {
        		if(currentDate.isAfter(suiviRapport.getDate().plusDays(15))) {
        		suiviRapport.setValid(true);
        		}
        	}
            rapportsModifies.add(suiviRapport);
        }
		return rapportsModifies;
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @GetMapping("/{id}")
	public SuiviRapport recupererOneRapport(@PathVariable Long id) {
		return suiviRapportService.getSuiviRapportById(id);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PutMapping
    public ResponseEntity<?> updateRapport(@RequestBody SuiviRapportUpdate suiviRapportUpdate) {
        SuiviRapport existingRapport = suiviRapportService.getSuiviRapportById(suiviRapportUpdate.getId());
        if (existingRapport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (suiviRapportUpdate.getClientNom() != null && !suiviRapportUpdate.getClientNom().isEmpty()) {
        	existingRapport.setClient(clientRepository.findByName(suiviRapportUpdate.getClientNom())
    				.orElseThrow(() -> new RuntimeException("Error: Client non trouvé.")));
        }

        if (suiviRapportUpdate.getUserEmail() != null && !suiviRapportUpdate.getUserEmail().isEmpty()) {
        	existingRapport.setUser(userRepository.findByEmail(suiviRapportUpdate.getUserEmail())
    				.orElseThrow(() -> new RuntimeException("Error: User non trouvé.")));
        }
        if (suiviRapportUpdate.getObjectNom() != null && !suiviRapportUpdate.getObjectNom().isEmpty()) {
        	existingRapport.setObject(objectRepository.findByNom(suiviRapportUpdate.getObjectNom())
    				.orElseThrow(() -> new RuntimeException("Error: Object non trouvé.")));
        }
        if (suiviRapportUpdate.getRegionNom() != null && !suiviRapportUpdate.getRegionNom().isEmpty()) {
        	existingRapport.setRegion(regionRepository.findByNom(suiviRapportUpdate.getRegionNom())
    				.orElseThrow(() -> new RuntimeException("Error: Region non trouvé.")));
        }
        if (suiviRapportUpdate.getDate() != null ) {
        	existingRapport.setDate(suiviRapportUpdate.getDate());
        }
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isAfter(suiviRapportUpdate.getDate().plusDays(15))) {
        	existingRapport.setValid(true);
        }
        else {
            existingRapport.setValid(suiviRapportUpdate.isValid());
        }
        if(suiviRapportUpdate.getText()!=null) {
        	existingRapport.setText(suiviRapportUpdate.getText());
        }

        SuiviRapport updatedRapport = suiviRapportService.updateOneSuiviRapport(existingRapport);
        return new ResponseEntity<>(updatedRapport, HttpStatus.OK);
    }
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @DeleteMapping("/{id}")
	public void supprimerRapport(@PathVariable Long id) {
    	suiviRapportService.deleteSuiviRapport(id);
	}
	
}