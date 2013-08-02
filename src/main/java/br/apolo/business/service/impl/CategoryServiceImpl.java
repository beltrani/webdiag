package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.CategoryService;
import br.apolo.data.model.Category;
import br.apolo.data.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public List<Category> list() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Category find(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public Category save(Category entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		return categoryRepository.save(entity);	
	}

	@Override
	public void remove(Category entity) {
		categoryRepository.delete(entity);	
	}
		

	@Override
	public SearchResult<Category> search(String param) {
		return null;
	}

}
