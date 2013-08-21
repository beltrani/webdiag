package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Specialty;

public interface SpecialtyRepository extends PagingAndSortingRepository<Specialty, Long>, SpecialtyRepositoryCustom {



}
