package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Doctor;

public interface DoctorRepository extends PagingAndSortingRepository<Doctor, Long>, DoctorRepositoryCustom {

	
}
