package br.apolo.data.repository;

import java.util.List;

import br.apolo.data.model.Sickness;
import br.apolo.data.model.Symptom;

public interface SicknessRepositoryCustom extends BaseRepository<Sickness> {
	
	List<Sickness> search(String name, String cid, List<Symptom> symptoms);

}
