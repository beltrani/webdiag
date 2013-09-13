package br.apolo.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Clinic.class)
public abstract class Clinic_ extends br.apolo.data.model.AuditableBaseEntity_ {

	public static volatile ListAttribute<Clinic, Sickness> sicknessAds;
	public static volatile SingularAttribute<Clinic, String> phone;
	public static volatile SingularAttribute<Clinic, String> address;
	public static volatile SingularAttribute<Clinic, String> name;
	public static volatile SingularAttribute<Clinic, String> state;
	public static volatile SingularAttribute<Clinic, String> number;
	public static volatile ListAttribute<Clinic, Doctor> doctors;
	public static volatile SingularAttribute<Clinic, String> city;

}

