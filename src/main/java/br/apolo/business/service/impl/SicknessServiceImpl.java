package br.apolo.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		return sicknessRepository.findByNewSicknessIsNullOrderByNameAsc();
	}

	@Override
	public Sickness find(Long id) {
		return sicknessRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Sickness save(Sickness entity) {
		if(entity != null) {
			entity.setLastUpdatedBy(getAuthenticatedUser());
			entity.setLastUpdateDate(new Date());
			
			if (entity.getId() != null) {
				Sickness oldSickness = sicknessRepository.findOne(entity.getId());
				
				// associa a doenca que foi consultada no banco como doenca antiga desta
				entity.setOldSickness(oldSickness);
				
				// retirando o id, estamos forcando o hibernate a criar um registro novo
				entity.setId(null);
				
				// sava a doenca
				entity = sicknessRepository.save(entity);
				
				// associa a doenca que acabou de ser criada ao campo de referencia da que esta sendo desativada.
				oldSickness.setNewSickness(entity);

				// retira a doenca da lista de clinicas anunciadas, deve permanecer somente a nova
				if (oldSickness.getClinicsAds() != null && !oldSickness.getClinicsAds().isEmpty()) {
					oldSickness.getClinicsAds().clear();
				}
				
				// salva a nova doenca
				sicknessRepository.save(oldSickness);
			} else {
				entity = sicknessRepository.save(entity);
			}
		}
		
		return entity;
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

	@Override
	public List<Sickness> getAllHistory(Long id) {
		List<Sickness> result = new ArrayList<Sickness>();
		
		if (id != null) {
			// consulta a doenca na base
			Sickness atualSickness = sicknessRepository.findOne(id);
			
			if (atualSickness != null && atualSickness.getOldSickness() != null) {
				// caso a doenca possua historico, preenche lista com cada edicao.
				getHistory(result, atualSickness.getOldSickness().getId());
			}			
		}
		
		return result;
	}
	
	
	private void getHistory(List<Sickness> result, Long id) {
		// consulta doenca historica na base
		Sickness atualSickness = sicknessRepository.findOne(id);
		
		if (atualSickness != null) {
			// inclui a doenca na lista
			result.add(atualSickness);
			
			// caso ainda existam edicoes, realiza chamada recursiva
			if (atualSickness.getOldSickness() != null) {
				getHistory(result, atualSickness.getOldSickness().getId());	
			}
		}
	}
	
}
