package com.example.CRM.Stage.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRM.Stage.services.RegionService;
import com.example.CRM.Stage.jwt.JwtUtils;
import com.example.CRM.Stage.models.MessageResponse;
import com.example.CRM.Stage.models.RegionModel;
import com.example.CRM.Stage.models.RegionRequest;
import com.example.CRM.Stage.models.RegionUpdate;
import com.example.CRM.Stage.repositories.RegionRepository;
//import com.example.Stage2023.Springbootangular.models.RegionRequest;
//import com.example.Stage2023.Springbootangular.models.RapportUpdate;
//import com.example.Stage2023.Springbootangular.repositories.RapportRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.UserRepository;
//import com.example.Stage2023.Springbootangular.services.RapportService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/regions")
public class RegionController {

	@Autowired
	public RegionService regionService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	PasswordEncoder encoder;
	//@Autowired
	//JwtUtils jwtUtils;
	public RegionRequest regionRequest;
	public RegionUpdate regionUpdate;	
	
	@PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	public ResponseEntity<?> ajouterRegion(@Valid @RequestBody RegionRequest regionRequest) {
		if (regionRepository.existsByNom(regionRequest.getNom())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Region Déjà Crée !"));
		}
	    RegionModel region = new RegionModel(regionRequest.getNom());
	    regionRepository.save(region);
	    regionService.addOneRegion(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
	}
	
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@GetMapping
	public List<RegionModel> recupererAllRegions(){
		return regionService.getAllRegions();
	}
	
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @GetMapping("/{id}")
	public RegionModel recupererOneRapport(@PathVariable Long id) {
		return regionService.getRegionById(id);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PutMapping
    public ResponseEntity<?> updateRapport(@RequestBody RegionUpdate regionUpdate) {
        RegionModel existingRegion = regionService.getRegionById(regionUpdate.getId());
        if (existingRegion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
        if (regionUpdate.getNom() != null && !regionUpdate.getNom().isEmpty()) {
        	if (regionRepository.existsByNom(regionUpdate.getNom())) {
    			return ResponseEntity.badRequest().body(new MessageResponse("Error: Region Déjà utilisé. Donnez un Autre nom !"));
    		}
        	existingRegion.setNom(regionUpdate.getNom());
        }
        
        // Save the updated user to the database
        RegionModel updatedRegion = regionService.updateOneRegion(existingRegion);
        return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
    }
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @DeleteMapping("/{id}")
	public void supprimerRegion(@PathVariable Long id) {
		regionService.deleteRegion(id);
	}
}
