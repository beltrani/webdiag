package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Symptom;

public interface SymptomRepository extends PagingAndSortingRepository<Symptom, Long>, SymptomRepositoryCustom {

	
}
