package br.apolo.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Sickness;

public interface SicknessRepository extends PagingAndSortingRepository<Sickness, Long>, SicknessRepositoryCustom {

	Sickness findByCategory_NameAndNewSicknessIsNullOrderByNameAsc(String categoryName);
	
	Sickness findByNameAndNewSicknessIsNullOrderByNameAsc(String name);
	
	List<Sickness> findByNewSicknessIsNullOrderByNameAsc();

}
