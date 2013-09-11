package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Clinic;

public interface ClinicRepository extends PagingAndSortingRepository<Clinic, Long>, ClinicRepositoryCustom {

	
}
