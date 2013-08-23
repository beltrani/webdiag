package br.apolo.data.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "category")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends AuditableBaseEntity {
	
	private static final long serialVersionUID = -7985007135932159381L; //este serial é igual para todas as classes ou muda de acordo com cada "funcionalidade"?//
	
	@Column(name = "name", length = InputLength.NAME, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NAME)
	private String name;

	@Column(name = "description", length = InputLength.DESCR, nullable = true)
	@Size(max = InputLength.DESCR)
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@OrderBy("id")
	private List<Sickness> sickness;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Sickness> getSickness() {
		return sickness;
	}

	public void setSickness(List<Sickness> sickness) {
		this.sickness = sickness;
	}
	
} 
