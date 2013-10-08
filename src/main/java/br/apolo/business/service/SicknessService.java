package br.apolo.business.service;

import java.util.List;

import br.apolo.business.model.SearchResult;
import br.apolo.data.model.Sickness;
import br.apolo.data.model.Symptom;

public interface SicknessService extends BaseService<Sickness> {

	SearchResult<Sickness> search(String name, String cid, List<Symptom> symptoms);

}