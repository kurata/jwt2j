package br.com.aqueteron.jwt2j;

public interface JWTEncoder {

	public JOSEHeader getJOSEHeader();

	public String encode(JWTPayload jwtPayload);

	public String encode(JOSEHeader joseHeader, JWTPayload jwtPayload);

}
