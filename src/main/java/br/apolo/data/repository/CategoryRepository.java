package br.apolo.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.apolo.data.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, CategoryRepositoryCustom {

	
}
