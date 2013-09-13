package br.apolo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Sickness;

public interface SicknessRepository extends PagingAndSortingRepository<Sickness, Long>, SicknessRepositoryCustom {

	Sickness findByCategory_Name(String categoryName);
	Sickness findByName(String name);
	
	@Query("FROM Sickness e ORDER BY e.name")
	List<Sickness> findAllSicknes();

}