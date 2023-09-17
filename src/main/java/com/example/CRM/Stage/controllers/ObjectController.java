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

import com.example.CRM.Stage.models.MessageResponse;
import com.example.CRM.Stage.models.ObjectModel;
import com.example.CRM.Stage.models.ObjectRequest;
import com.example.CRM.Stage.models.ObjectUpdate;
import com.example.CRM.Stage.repositories.ObjectRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.UserRepository;
import com.example.CRM.Stage.services.ObjectService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/objets")
public class ObjectController {


	@Autowired
	public ObjectService objectService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ObjectRepository objectRepository;
	@Autowired
	PasswordEncoder encoder;
	//@Autowired
	//JwtUtils jwtUtils;
	public ObjectRequest objectRequest;
	public ObjectUpdate objectUpdate;	
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@PostMapping
	public ResponseEntity<?> ajouterObject(@Valid @RequestBody ObjectRequest objectRequest) {
		if (objectRepository.existsByNom(objectRequest.getNom())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Object Déjà Crée !"));
		}
	    ObjectModel object = new ObjectModel(objectRequest.getNom());
	    objectRepository.save(object);
	    objectService.addOneObject(object);
        return new ResponseEntity<>(object, HttpStatus.OK);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@GetMapping
	public List<ObjectModel> recupererAllObjects(){
		return objectService.getAllObjects();
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @GetMapping("/{id}")
	public ObjectModel recupererOneRapport(@PathVariable Long id) {
		return objectService.getObjectById(id);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PutMapping
    public ResponseEntity<?> updateRapport(@RequestBody ObjectUpdate objectUpdate) {
        ObjectModel existingObject = objectService.getObjectById(objectUpdate.getId());
        if (existingObject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }        
     
        if (objectUpdate.getNom() != null && !objectUpdate.getNom().isEmpty()) {
        	if (objectRepository.existsByNom(objectUpdate.getNom())) {
    			return ResponseEntity.badRequest().body(new MessageResponse("Error: Object  Déjà utilisé. Donnez un Autre nom !"));
    		}
        	existingObject.setNom(objectUpdate.getNom());
        }
        
        // Save the updated user to the database
        ObjectModel updatedObject = objectService.updateOneObject(existingObject);
        return new ResponseEntity<>(updatedObject, HttpStatus.OK);
    }
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @DeleteMapping("/{id}")
	public void supprimerObject(@PathVariable Long id) {
		objectService.deleteObject(id);;
	}
}
