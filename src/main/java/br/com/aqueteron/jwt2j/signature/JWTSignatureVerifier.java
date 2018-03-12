package br.com.aqueteron.jwt2j.signature;

import br.com.aqueteron.jwt2j.JOSEHeader;

public interface JWTSignatureVerifier {

	public Boolean verifySignature(byte[] data, byte[] signature);

	public Boolean verifyJOSEHeader(JOSEHeader joseHeader);
	
	public String getJWTAlgorithmName();
}
