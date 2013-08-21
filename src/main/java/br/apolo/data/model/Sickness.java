package br.apolo.data.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "sickness")
@AttributeOverride(name = "id", column = @Column(name = "sickness_id"))
public class Sickness extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;

	@Column(name = "name", length = InputLength.NAME, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NAME)
	private String name;

	@Column(name = "cid", length = InputLength.CID, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CID)
	private String cid;

	@Column(name = "description", length = InputLength.DESCR, nullable = true)
	@Size(max = InputLength.DESCR)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sickness_symptom", 
			joinColumns = { @JoinColumn(name = "sickness_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "symptom_id", nullable = false, updatable = false) })
	
	private List<Symptom>symptoms;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
