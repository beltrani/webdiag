package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.SymptomService;
import br.apolo.data.model.Symptom;
import br.apolo.data.repository.SymptomRepository;

@Service("symptomService")
public class SymptomServiceImpl extends BaseServiceImpl<Symptom> implements SymptomService {

	@Autowired
	private SymptomRepository symptomRepository;

	@Override
	public List<Symptom> list() {
		return (List<Symptom>) symptomRepository.findAll();
	}

	@Override
	public Symptom find(Long id) {
		return symptomRepository.findOne(id);
	}

	@Override
	public Symptom save(Symptom entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return symptomRepository.save(entity);	
	}

	@Override
	public void remove(Symptom entity) {
		symptomRepository.delete(entity);	
		
	}

	@Override
	public SearchResult<Symptom> search(String param) {
		return null;
	}
	
}
