package br.com.aqueteron.jwt2j.signature;

import br.com.aqueteron.jwt2j.JOSEHeader;

public class NoneVerifier implements JWTSignatureVerifier {
	
	private static final String ALGORITHM_NAME = "none";

	private static final String JWT_STRING = "jwt";

	@Override
	public Boolean verifySignature(byte[] data, byte[] signature) {
		if( null == signature) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean verifyJOSEHeader(JOSEHeader joseHeader) {
		if (JWT_STRING.equals(joseHeader.getTyp())) {
			return ALGORITHM_NAME.equals(joseHeader.getAlg());
		}
		return Boolean.FALSE;
	}

	@Override
	public String getJWTAlgorithmName() {
		return ALGORITHM_NAME;
	}

}
