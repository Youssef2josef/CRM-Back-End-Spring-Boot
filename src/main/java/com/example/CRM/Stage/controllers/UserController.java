package com.example.CRM.Stage.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;


import com.example.CRM.Stage.jwt.JwtUtils;
import com.example.CRM.Stage.models.ERole;
import com.example.CRM.Stage.models.JwtResponse;
import com.example.CRM.Stage.models.LoginRequest;
import com.example.CRM.Stage.models.MessageResponse;
import com.example.CRM.Stage.models.RegionModel;
import com.example.CRM.Stage.models.Role;
import com.example.CRM.Stage.models.SignupRequest;
import com.example.CRM.Stage.models.User;
import com.example.CRM.Stage.models.UserDetailsImpl;
import com.example.CRM.Stage.repositories.RegionRepository;
import com.example.CRM.Stage.repositories.RoleRepository;
import com.example.CRM.Stage.repositories.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RegionRepository regionRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
	            .collect(Collectors.toList());
	        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFirstName(), userDetails.getEmail(), roles));
	    } catch (BadCredentialsException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
	    }
	}


	@PostMapping("/register")
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email Déjà utilisé!"));
		}
		// Create new user's account
		User user = new User(signUpRequest.getFirstName(),  signUpRequest.getLastName(),  signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		String regionRequest = signUpRequest.getRegion();
		if(regionRequest != null) {
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
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}