package br.apolo.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "specialty")
@AttributeOverride(name = "id", column = @Column(name = "specialty_id"))
public class Specialty extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;

	@Column(name = "name", length = InputLength.NAME, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NAME)
	private String name;

	@Column(name = "description", length = InputLength.DESCR, nullable = true)
	@Size(max = InputLength.DESCR)
	private String description;

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

/*	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sickness_symptom", 
			joinColumns = { @JoinColumn(name = "sickness_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "symptom_id", nullable = false, updatable = false) })
	
	private List<Symptom>symptoms;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
*/

	
}
