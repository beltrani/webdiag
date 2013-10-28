package br.apolo.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sickness.class)
public abstract class Sickness_ extends br.apolo.data.model.AuditableBaseEntity_ {

	public static volatile ListAttribute<Sickness, Symptom> symptoms;
	public static volatile ListAttribute<Sickness, Clinic> clinicsAds;
	public static volatile SingularAttribute<Sickness, Category> category;
	public static volatile SingularAttribute<Sickness, String> description;
	public static volatile SingularAttribute<Sickness, String> name;
	public static volatile SingularAttribute<Sickness, Sickness> oldSickness;
	public static volatile SingularAttribute<Sickness, Sickness> newSickness;
	public static volatile SingularAttribute<Sickness, String> cid;

}

