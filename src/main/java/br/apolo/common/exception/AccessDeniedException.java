package br.apolo.common.exception;

public class AccessDeniedException extends GenericException {

	private static final long serialVersionUID = 6102557498006358663L;
	
	public AccessDeniedException(String customMsg) {
		super(customMsg);
	}
	
}