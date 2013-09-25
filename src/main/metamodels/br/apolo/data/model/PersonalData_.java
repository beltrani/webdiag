package br.apolo.data.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonalData.class)
public abstract class PersonalData_ extends br.apolo.data.model.AuditableBaseEntity_ {

	public static volatile SingularAttribute<PersonalData, String> address;
	public static volatile SingularAttribute<PersonalData, String> state;
	public static volatile SingularAttribute<PersonalData, String> rg;
	public static volatile SingularAttribute<PersonalData, String> number;
	public static volatile SingularAttribute<PersonalData, String> cpf;
	public static volatile SingularAttribute<PersonalData, User> user;
	public static volatile SingularAttribute<PersonalData, String> city;

}

