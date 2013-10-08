package br.apolo.web.model;

import java.util.List;

import br.apolo.data.model.Symptom;

public class SicknessFormModel {
	
	private String name;
	
	private String cid;
	
	private List<Symptom> symptoms;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

}
