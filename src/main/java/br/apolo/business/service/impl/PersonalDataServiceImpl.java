package br.apolo.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.apolo.business.model.SearchResult;
import br.apolo.business.service.PersonalDataService;
import br.apolo.business.service.UserService;
import br.apolo.data.model.PersonalData;
import br.apolo.data.repository.PersonalDataRepository;

@Service("personalDataService")
public class PersonalDataServiceImpl extends BaseServiceImpl<PersonalData> implements PersonalDataService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PersonalDataRepository personalDataRepository;

	@Override
	public List<PersonalData> list() {
		return (List<PersonalData>) personalDataRepository.findAll();
	}

	@Override
	public PersonalData find(Long id) {
		return personalDataRepository.findOne(id);
	}

	@Override
	public PersonalData save(PersonalData entity) {
		entity.setLastUpdatedBy(getAuthenticatedUser());
		entity.setLastUpdateDate(new Date());
		
		if (entity.getUser() != null && entity.getUser().getId() == null) {
			entity.getUser().setName(entity.getName());
			entity.getUser().setPassword("mudeASenha!!!");
			
			entity.setUser(userService.save(entity.getUser(), true));
		}
		
		return personalDataRepository.save(entity);	
	}

	@Override
	public SearchResult<PersonalData> search(String param) {
		return null;
	}

	@Override
	public void remove(PersonalData entity) {
		personalDataRepository.delete(entity);
		
	}
}
