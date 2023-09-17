package com.example.CRM.Stage.services;

import java.util.List;

import com.example.CRM.Stage.models.ClientModel;

public interface ClientService {

	public List<ClientModel> getAllClients();
	
	public ClientModel getClientById(Long id);
	
	public void deleteClient(Long id);
	
	public ClientModel updateOneClient(ClientModel p);

	public ClientModel addOneClient(ClientModel p);
}
