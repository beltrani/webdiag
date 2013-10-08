package br.apolo.data.repository.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.apolo.data.model.Sickness;
import br.apolo.data.model.Symptom;
import br.apolo.data.repository.SicknessRepositoryCustom;

@Repository
public class SicknessRepositoryImpl extends BaseRepotitoryImpl<Sickness> implements SicknessRepositoryCustom {

	@Override
	public List<Sickness> search(String name, String cid, List<Symptom> symptoms) {
		try {
			StringBuilder queryString = new StringBuilder(" SELECT DISTINCT s FROM Sickness s ");
			
			if (symptoms != null && !symptoms.isEmpty()) {
				queryString.append(" JOIN s.symptoms sSymptom ");	
			}
			
			queryString.append(" WHERE ( 1 = 1 ");
			
			if (name != null && !name.isEmpty()) {
				queryString.append(" AND s.name LIKE :name");	
			}
			
			if (cid != null && !cid.isEmpty()) {
				queryString.append(" AND s.cid LIKE :cid");	
			}
			
			if (symptoms != null && !symptoms.isEmpty()) {
				queryString.append(" AND sSymptom IN (:symptoms)");	
			}
			
			queryString.append(" ) ");
			
			queryString.append(" ORDER BY s.name ");

			TypedQuery<Sickness> query = em.createQuery(queryString.toString(), Sickness.class);

			if (name != null && !name.isEmpty()) {
				query.setParameter("name", "%" + name + "%");	
			}
			
			if (cid != null && !cid.isEmpty()) {
				query.setParameter("cid", "%" + cid + "%");	
			}
			
			if (symptoms != null && !symptoms.isEmpty()) {
				query.setParameter("symptoms", symptoms);	
			}
			
			return query.getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
}