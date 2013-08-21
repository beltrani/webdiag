package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.SpecialtyService;
import br.apolo.data.model.Specialty;
import br.apolo.data.repository.SpecialtyRepository;

@Service("specialtyService")
public class SpecialtyServiceImpl extends BaseServiceImpl<Specialty> implements SpecialtyService {

	@Autowired
	 private SpecialtyRepository specialtyRepository;

	@Override
	public List<Specialty> list() {
		return (List<Specialty>) specialtyRepository.findAll();
	}

	@Override
	public Specialty find(Long id) {
		return specialtyRepository.findOne(id);
	}

	@Override
	public Specialty save(Specialty entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return specialtyRepository.save(entity);	
	}

	@Override
	public void remove(Specialty entity) {
		specialtyRepository.delete(entity);	
	}

	@Override
	public SearchResult<Specialty> search(String param) {
		return null;
	}

	
	
}
