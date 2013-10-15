package br.apolo.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doctor.class)
public abstract class Doctor_ extends br.apolo.data.model.AuditableBaseEntity_ {

	public static volatile ListAttribute<Doctor, Specialty> specialties;
	public static volatile ListAttribute<Doctor, Clinic> clinics;
	public static volatile SingularAttribute<Doctor, String> name;
	public static volatile SingularAttribute<Doctor, String> state;
	public static volatile SingularAttribute<Doctor, String> crm;
	public static volatile SingularAttribute<Doctor, User> user;

}

