package br.com.aqueteron.jwt2j.signature;

public class InvalidJWTTokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1451495797742960083L;

	public InvalidJWTTokenException(final String message) {
		super(message);
	}

	public InvalidJWTTokenException(final String message, Throwable cause) {
		super(message, cause);
	}

}
