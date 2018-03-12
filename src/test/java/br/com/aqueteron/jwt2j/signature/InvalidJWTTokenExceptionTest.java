package br.com.aqueteron.jwt2j.signature;

import org.junit.Test;

public class InvalidJWTTokenExceptionTest {

	@Test(expected=InvalidJWTTokenException.class)
	public void testInvalidJWTTokenExceptionString() throws InvalidJWTTokenException {
		throw new InvalidJWTTokenException("Invalid JWT Token.");
	}

	@Test(expected=InvalidJWTTokenException.class)
	public void testInvalidJWTTokenExceptionStringThrowable() throws InvalidJWTTokenException {
		throw new InvalidJWTTokenException("Invalid JWT Token." , new Exception());
	}

}
