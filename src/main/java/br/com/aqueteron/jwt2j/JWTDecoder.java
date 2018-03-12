package br.com.aqueteron.jwt2j;

import br.com.aqueteron.jwt2j.signature.InvalidJWTTokenException;

@FunctionalInterface
public interface JWTDecoder<T extends JWTPayload> {

	public T decode(String jwtToken) throws InvalidJWTTokenException;

}
