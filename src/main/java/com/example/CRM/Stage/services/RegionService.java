package com.example.CRM.Stage.services;

import java.util.List;

import com.example.CRM.Stage.models.RegionModel;

public interface RegionService {

	public List<RegionModel> getAllRegions();
	
	public RegionModel getRegionById(Long id);
	
	public void deleteRegion(Long id);
	
	public RegionModel updateOneRegion(RegionModel p);

	public RegionModel addOneRegion(RegionModel p);
	
}
