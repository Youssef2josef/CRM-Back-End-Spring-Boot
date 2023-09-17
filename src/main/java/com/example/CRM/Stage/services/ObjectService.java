package com.example.CRM.Stage.services;

import java.util.List;

import com.example.CRM.Stage.models.ObjectModel;

public interface ObjectService {

	public List<ObjectModel> getAllObjects();
	
	public ObjectModel getObjectById(Long id);
	
	public void deleteObject(Long id);
	
	public ObjectModel updateOneObject(ObjectModel p);

	public ObjectModel addOneObject(ObjectModel p);
	
}
