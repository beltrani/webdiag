package br.apolo.data.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.apolo.common.util.InputLength;
import br.apolo.data.entitylistener.AuditLogListener;

@Entity
@EntityListeners(value = AuditLogListener.class)
@Table(name = "personal_data")
@AttributeOverride(name = "id", column = @Column(name = "personal_data_id"))
public class PersonalData extends AuditableBaseEntity {

	private static final long serialVersionUID = 5588722501578237833L;
	
	@Column(name = "cpf", length = InputLength.CPF, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CPF)
	private String cpf;
	
	@Column(name = "rg", length = InputLength.RG, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.RG)
	private String rg;

	@Column(name = "address", length = InputLength.ADDRESS, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.ADDRESS)
	private String address;
	
	@Column(name = "number", length = InputLength.NUMBER, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.NUMBER)
	private String number;
	
	@Column(name = "city", length = InputLength.CITY, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.CITY)
	private String city;

	@Column(name = "state", length = InputLength.STATE, nullable = false)
	@NotNull
	@Size(min = 1, max = InputLength.STATE)
	private String state;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
