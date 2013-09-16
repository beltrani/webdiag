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
@Table(name = "doctor")
@AttributeOverride(name = "id", column = @Column(name = "doctor_id"))
public class Doctor extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;

	@Column(name = "name", length = InputLength.NAME, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NAME)
	private String name;

	@Column(name = "crm", length = InputLength.CRM, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CRM)
	private String crm;
	
	@Column(name = "state", nullable = false)
	@NotNull
	@Size(min = 1, max = 256)
	private String state;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "doctor_specialty", 
			joinColumns = { @JoinColumn(name = "doctor_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "specialty_id", nullable = false, updatable = false) })
	private List<Specialty>specialties;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
