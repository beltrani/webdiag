package br.apolo.data.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "clinic")
@AttributeOverride(name = "id", column = @Column(name = "clinic_id"))
public class Clinic extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;

	@Column(name = "name", length = InputLength.NAME, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NAME)
	private String name;
	
	@Column(name = "address", length = InputLength.ADDRESS, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.ADDRESS)
	private String address;
	
	@Column(name = "city", length = InputLength.CITY, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CITY)
	private String city;
	
	@Column(name = "number", length = InputLength.NUMBER, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NUMBER)
	private String number;

	@Column(name = "phone", length = InputLength.PHONE, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.PHONE)
	private String phone;
	
	@Column(name = "state", length = InputLength.STATE, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.STATE)
	private String state;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "clinic_doctor", 
			joinColumns = { @JoinColumn(name = "clinic_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "doctor_id", nullable = false, updatable = false) })
	private List<Doctor>doctors;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
