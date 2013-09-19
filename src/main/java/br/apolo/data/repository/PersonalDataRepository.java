package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.PersonalData;

public interface PersonalDataRepository extends PagingAndSortingRepository<PersonalData, Long>, PersonalDataRepositoryCustom {

	
}
