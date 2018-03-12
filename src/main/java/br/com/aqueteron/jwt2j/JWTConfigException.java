package br.com.aqueteron.jwt2j;

public class JWTConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167768490088374638L;

	public JWTConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	public JWTConfigException(String message) {
		super(message);
	}

}
