package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.ClinicService;
import br.apolo.data.model.Clinic;
import br.apolo.data.repository.ClinicRepository;

@Service("clinicService")
public class ClinicServiceImpl extends BaseServiceImpl<Clinic> implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	@Override
	public List<Clinic> list() {
		return (List<Clinic>) clinicRepository.findAll();
	}

	@Override
	public Clinic find(Long id) {
		return clinicRepository.findOne(id);
	}

	@Override
	public Clinic save(Clinic entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return clinicRepository.save(entity);	
	}

	@Override
	public void remove(Clinic entity) {
		clinicRepository.delete(entity);	
	}

	@Override
	public SearchResult<Clinic> search(String param) {
		return null;
	}


}
