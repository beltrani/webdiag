package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.SicknessService;
import br.apolo.data.model.Sickness;
import br.apolo.data.model.Symptom;
import br.apolo.data.repository.SicknessRepository;

@Service("sicknessService")
public class SicknessServiceImpl extends BaseServiceImpl<Sickness> implements SicknessService {

	@Autowired
	private SicknessRepository sicknessRepository;

	@Override
	public List<Sickness> list() {
		return (List<Sickness>) sicknessRepository.findAll();
	}

	@Override
	public Sickness find(Long id) {
		return sicknessRepository.findOne(id);
	}

	@Override
	public Sickness save(Sickness entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return sicknessRepository.save(entity);	
	}

	@Override
	public void remove(Sickness entity) {
		sicknessRepository.delete(entity);	
		
	}

	@Override
	public SearchResult<Sickness> search(String param) {
		return null;
	}

	@Override
	public SearchResult<Sickness> search(String name, String cid, List<Symptom> symptoms) {
		SearchResult<Sickness> result = new SearchResult<Sickness>();
		
		result.setResults(sicknessRepository.search(name, cid, symptoms));

		return result;
	}
	
	
}
