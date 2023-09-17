package com.example.CRM.Stage.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRM.Stage.models.ClientModel;
import com.example.CRM.Stage.repositories.ClientRepository;
import com.example.CRM.Stage.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepo;

	@Override
	public List<ClientModel> getAllClients() {
		// TODO Auto-generated method stub
		return clientRepo.findAll();
	}

	@Override
	public ClientModel getClientById(Long id) {
		// TODO Auto-generated method stub
		Optional<ClientModel> m = clientRepo.findById(id);
		return m.isPresent() ? m.get() : null;
	}

	@Override
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
		clientRepo.deleteById(id);
	}

	@Override
	public ClientModel addOneClient(ClientModel m) {
		// TODO Auto-generated method stub
		return clientRepo.save(m);
	}

	@Override
	public ClientModel updateOneClient(ClientModel m) {
		// TODO Auto-generated method stub
		return clientRepo.save(m);
	}
}
