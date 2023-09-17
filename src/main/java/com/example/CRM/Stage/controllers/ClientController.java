package com.example.CRM.Stage.controllers;

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

import com.example.CRM.Stage.models.MessageResponse;
import com.example.CRM.Stage.models.ClientModel;
import com.example.CRM.Stage.models.ClientRequest;
import com.example.CRM.Stage.models.ClientUpdate;
import com.example.CRM.Stage.repositories.ClientRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.UserRepository;
import com.example.CRM.Stage.services.ClientService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	public ClientService clientService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ClientRepository clientRepository;
	public ClientRequest clientRequest;
	public ClientUpdate clientUpdate;	
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@PostMapping
	public ResponseEntity<?> ajouterClient(@Valid @RequestBody ClientRequest clientRequest) {
		if (clientRepository.existsByName(clientRequest.getName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Client Déjà Crée!"));
		}
	    ClientModel client = new ClientModel(clientRequest.getName(),clientRequest.getContact1(),clientRequest.getTel1(),
	    		clientRequest.getContact2(),clientRequest.getTel2(),clientRequest.getMail(),clientRequest.getRegion(),clientRequest.getPays());
	    clientRepository.save(client);
	    clientService.addOneClient(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
	@GetMapping
	public List<ClientModel> recupererAllClients(){
		return clientService.getAllClients();
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @GetMapping("/{id}")
	public ClientModel recupererOneClient(@PathVariable Long id) {
		return clientService.getClientById(id);
	}
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody ClientUpdate clientUpdate) {
        ClientModel existingClient = clientService.getClientById(clientUpdate.getId());
        if (existingClient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        if (clientUpdate.getName() != null && !clientUpdate.getName().isEmpty()) {
        	existingClient.setName(clientUpdate.getName());
        }

        	existingClient.setContact1(clientUpdate.getContact1()); 

        	existingClient.setTel1(clientUpdate.getTel1());
        
        	existingClient.setContact2(clientUpdate.getContact2());
        
        	existingClient.setTel2(clientUpdate.getTel2());
        
        if (clientUpdate.getMail() != null && !clientUpdate.getMail().isEmpty()) {
        	existingClient.setMail(clientUpdate.getMail());
        }
        	existingClient.setRegion(clientUpdate.getRegion());
        
        	existingClient.setPays(clientUpdate.getPays());
        
        
        // Save the updated user to the database
        ClientModel updatedRegion = clientService.updateOneClient(existingClient);
        return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
    }
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @DeleteMapping("/{id}")
	public void supprimerClient(@PathVariable Long id) {
		clientService.deleteClient(id);
	}
    
}
