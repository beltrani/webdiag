package br.apolo.web.enums;

public enum Navigation {
	
	HOME("/"), 
	INDEX("index"),
	
	USER("user"),
	USER_INDEX("user/index"),
	USER_NEW("user/new"),
	USER_EDIT("user/edit"),
	USER_LIST("user/list"),
	USER_SEARCH("user/search"),
	USER_VIEW("user/view"),
	USER_CHANGE_PASSWORD("user/change-password"),
	
	USER_PERMISSION_LIST("user-group/list"),
	USER_PERMISSION_SEARCH("user-group/search"),
	USER_PERMISSION_NEW("user-group/new"),
	USER_PERMISSION_EDIT("user-group/edit"),
	USER_PERMISSION_VIEW("user-group/view"),
	
	CATEGORY_LIST("category/list"),
	CATEGORY_NEW("category/new"),
	CATEGORY_EDIT("category/edit"),
	CATEGORY_VIEW("category/view"),
	
	SYMPTOM_LIST("symptom/list"),
	SYMPTOM_NEW("symptom/new"),
	SYMPTOM_EDIT("symptom/edit"),
	SYMPTOM_VIEW("symptom/view"),
	
	SICKNESS_LIST("sickness/list"),
	SICKNESS_NEW("sickness/new"),
	SICKNESS_EDIT("sickness/edit"),
	SICKNESS_VIEW("sickness/view"),
	SICKNESS_SEARCH("sickness/search"),
	
	SPECIALTY_LIST("specialty/list"),
	SPECIALTY_NEW("specialty/new"),
	SPECIALTY_EDIT("specialty/edit"),
	SPECIALTY_VIEW("specialty/view"),
	
	DOCTOR_LIST("doctor/list"),
	DOCTOR_NEW("doctor/new"),
	DOCTOR_EDIT("doctor/edit"),
	DOCTOR_VIEW("doctor/view"),
	
	CLINIC_LIST("clinic/list"),
	CLINIC_NEW("clinic/new"),
	CLINIC_EDIT("clinic/edit"),
	CLINIC_VIEW("clinic/view"),
	
	PERSONALDATA_LIST("personaldata/list"),
	PERSONALDATA_NEW("personaldata/new"),
	PERSONALDATA_EDIT("personaldata/edit"),
	PERSONALDATA_VIEW("personaldata/view"),
	
	AUTH("auth"),
	AUTH_LOGIN("auth/login"),
	AUTH_LOGOUT("auth/logout"),
	
	ERROR("error/error"),
	
	;
	
	
	private String path;

	private Navigation(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
