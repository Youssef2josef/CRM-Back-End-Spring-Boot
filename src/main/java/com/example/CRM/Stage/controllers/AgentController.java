package com.example.CRM.Stage.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRM.Stage.models.ERole;
import com.example.CRM.Stage.models.MessageResponse;
import com.example.CRM.Stage.models.RegionModel;
import com.example.CRM.Stage.models.Role;
import com.example.CRM.Stage.models.SignupRequest;
import com.example.CRM.Stage.models.UpdateUserSuper;
import com.example.CRM.Stage.models.User;
import com.example.CRM.Stage.repositories.RegionRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.UserRepository;
import com.example.CRM.Stage.services.UserService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agents")
public class AgentController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')")
	@PostMapping
	public ResponseEntity<?> ajouterAgent(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email Déja Utilisé!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getFirstName(),  signUpRequest.getLastName(),  signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		String regionRequest = signUpRequest.getRegion();
		if(regionRequest == null) {
		}else {
			RegionModel region = regionRepository.findByNom(regionRequest.toLowerCase())
				.orElseThrow(() -> new RuntimeException("Error: Region non trouvé."));
		user.setRegion(region);
		}
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
					.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
			roles.add(agentRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
					roles.add(adminRole);
					break;
				case "responsable":
					Role responsableRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE)
							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
					roles.add(responsableRole);
					break;
				default:
					Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
					roles.add(agentRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);	
	}
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')")
	@GetMapping
	public List<User> recupererAllAgents(){
		return userService.getAllUsers();
	}
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')")
    @GetMapping("/{id}")
	public User recupererOneAgent(@PathVariable Long id) {
		return userService.getUserById(id);
	}
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody SignupRequest signupRequest) {
        User existingUser = userService.getUserById(signupRequest.getId());

        if (existingUser == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Utilisateur non trouvé !"));
        }

        // Update the user's attributes based on the UpdateRequest data
        if (signupRequest.getFirstName() != null && !signupRequest.getFirstName().isEmpty()) {
        existingUser.setFirstName(signupRequest.getFirstName());
        }
        
        if (signupRequest.getLastName() != null && !signupRequest.getLastName().isEmpty()) {
        existingUser.setLastName(signupRequest.getLastName());
        }
        
        if (signupRequest.getEmail() != null && !signupRequest.getEmail().isEmpty()) {
        	if (userRepository.existsByEmail(signupRequest.getEmail())) {
    			Optional<User> userFound = userRepository.findByEmail(signupRequest.getEmail());
    			Optional<User> userNow = userRepository.findById(signupRequest.getId());
    			if(userNow.get().getId() != userFound.get().getId()) {
    				return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Email Dupliqué !"));
    			}
    	    }
        existingUser.setEmail(signupRequest.getEmail());
        }
        
        if (signupRequest.getPassword() != null && !signupRequest.getPassword().isEmpty()) {
        existingUser.setPassword(encoder.encode(signupRequest.getPassword()));
        }
        
        if (signupRequest.getRegion() != null && !signupRequest.getRegion().isEmpty()) {
        	String regionRequest = signupRequest.getRegion();
    		if(regionRequest == null) {
    		}else {
    			RegionModel region = regionRepository.findByNom(regionRequest.toLowerCase())
    				.orElseThrow(() -> new RuntimeException("Error: Region non trouvé."));
    			existingUser.setRegion(region);
    		}
        }

        // Save the updated user to the database
        User updatedUser = userService.updateOneUser(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE')") // Users with 'ROLE_ADMIN' OR 'ROLE_RESPONSABLE' can add users
    @PatchMapping
    public ResponseEntity<?> updateUserBySuperUser(@RequestBody UpdateUserSuper updateUserSuper) {
        User existingUser = userService.getUserById(updateUserSuper.getId());

        if (existingUser == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User non trouvé!"));
        }
        
        if (updateUserSuper.getRole() != null && !updateUserSuper.getRole().isEmpty()) {
        	Set<String> strRoles = updateUserSuper.getRole();
    		Set<Role> roles = new HashSet<>();
    		if (strRoles == null) {
    			Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
    					.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
    			roles.add(agentRole);
    		} else {
    			strRoles.forEach(role -> {
    				switch (role) {
    				case "admin":
    					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
    							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
    					roles.add(adminRole);
    					break;
    				case "responsable":
    					Role responsableRole = roleRepository.findByName(ERole.ROLE_RESPONSABLE)
    							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
    					roles.add(responsableRole);
    					break;
    				default:
    					Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
    							.orElseThrow(() -> new RuntimeException("Error: Role non trouvé."));
    					roles.add(agentRole);
    				}
    			});
    		}
    		existingUser.setRoles(roles);
        }
        // Save the updated user to the database
        User updatedUser = userService.updateOneUser(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RESPONSABLE', 'ROLE_AGENT')") 
    @DeleteMapping("/{id}")
	public void supprimerAgent(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
