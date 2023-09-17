package com.example.CRM.Stage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRM.Stage.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

	
	Boolean existsByName(String name);
	Boolean existsByMail(String mail);
	Optional<ClientModel> findByMail(String mail);
//	Optional<ClientModel> findById(String mail);
	Optional<ClientModel> findByName(String name);

}
