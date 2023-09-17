package com.example.CRM.Stage.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRM.Stage.models.ObjectModel;
import com.example.CRM.Stage.repositories.ObjectRepository;
import com.example.CRM.Stage.services.ObjectService;

@Service
public class ObjectServiceImpl implements ObjectService {

	@Autowired
	private ObjectRepository objectRepo;

	@Override
	public List<ObjectModel> getAllObjects() {
		// TODO Auto-generated method stub
		return objectRepo.findAll();
	}

	@Override
	public ObjectModel getObjectById(Long id) {
		// TODO Auto-generated method stub
		Optional<ObjectModel> m = objectRepo.findById(id);
		return m.isPresent() ? m.get() : null;
	}

	@Override
	public void deleteObject(Long id) {
		// TODO Auto-generated method stub
		objectRepo.deleteById(id);
	}

	@Override
	public ObjectModel addOneObject(ObjectModel m) {
		// TODO Auto-generated method stub
		return objectRepo.save(m);
	}

	@Override
	public ObjectModel updateOneObject(ObjectModel m) {
		// TODO Auto-generated method stub
		return objectRepo.save(m);
	}
}
