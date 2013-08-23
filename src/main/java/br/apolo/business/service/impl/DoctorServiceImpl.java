package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.DoctorService;
import br.apolo.data.model.Doctor;
import br.apolo.data.repository.DoctorRepository;

@Service("doctorService")
public class DoctorServiceImpl extends BaseServiceImpl<Doctor> implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> list() {
		return (List<Doctor>) doctorRepository.findAll();
	}

	@Override
	public Doctor find(Long id) {
		return doctorRepository.findOne(id);
	}

	@Override
	public Doctor save(Doctor entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return doctorRepository.save(entity);	
	}

	@Override
	public void remove(Doctor entity) {
		doctorRepository.delete(entity);	
	}

	@Override
	public SearchResult<Doctor> search(String param) {
		return null;
	}


}
