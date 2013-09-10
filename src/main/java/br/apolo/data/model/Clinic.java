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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "clinic")
@AttributeOverride(name = "id", column = @Column(name = "clinic_id"))
public class Clinic extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;

	@Column(name = "address", length = InputLength.ADDRESS, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.ADDRESS)
	private String address;
	
	@Column(name = "city", length = InputLength.CITY, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CITY)
	private String citty;
	
	@Column(name = "number", length = InputLength.NUMBER, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NUMBER)
	private Integer number;

	@Column(name = "phone", length = InputLength.PHONE, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.PHONE)
	private Integer phone;
	
	@Column(name = "state", length = InputLength.STATE, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.STATE)
	private Integer state;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "clinic_doctor", 
			joinColumns = { @JoinColumn(name = "clinic_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "doctor_id", nullable = false, updatable = false) })
	private List<Doctor>doctors;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "clinic_sickness", 
			joinColumns = { @JoinColumn(name = "clinic_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "sickness_id", nullable = false, updatable = false) })
	private List<Sickness>sicknessAds;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCitty() {
		return citty;
	}

	public void setCitty(String citty) {
		this.citty = citty;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Sickness> getSicknessAds() {
		return sicknessAds;
	}

	public void setSicknessAds(List<Sickness> sicknessAds) {
		this.sicknessAds = sicknessAds;
	}
	
	
}
