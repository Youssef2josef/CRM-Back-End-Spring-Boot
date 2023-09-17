package com.example.CRM.Stage.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRM.Stage.models.RegionModel;
import com.example.CRM.Stage.repositories.RegionRepository;
import com.example.CRM.Stage.services.RegionService;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepo;

	@Override
	public List<RegionModel> getAllRegions() {
		// TODO Auto-generated method stub
		return regionRepo.findAll();
	}

	@Override
	public RegionModel getRegionById(Long id) {
		// TODO Auto-generated method stub
		Optional<RegionModel> m = regionRepo.findById(id);
		return m.isPresent() ? m.get() : null;
	}

	@Override
	public void deleteRegion(Long id) {
		// TODO Auto-generated method stub
		regionRepo.deleteById(id);
	}

	@Override
	public RegionModel addOneRegion(RegionModel m) {
		// TODO Auto-generated method stub
		return regionRepo.save(m);
	}

	@Override
	public RegionModel updateOneRegion(RegionModel m) {
		// TODO Auto-generated method stub
		return regionRepo.save(m);
	}
	
}
	
