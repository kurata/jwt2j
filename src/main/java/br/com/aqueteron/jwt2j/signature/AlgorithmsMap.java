package br.com.aqueteron.jwt2j.signature;

import java.util.HashMap;
import java.util.Map;

public final class AlgorithmsMap {

	protected static final Map<String, String> SIGNATURE_JWT_MAP = new HashMap<>();

	static {
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.RS256, JWTAlgorithmName.RS256);
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.RS384, JWTAlgorithmName.RS384);
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.RS512, JWTAlgorithmName.RS512);
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.ES256, JWTAlgorithmName.ES256);
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.ES384, JWTAlgorithmName.ES384);
		SIGNATURE_JWT_MAP.put(SignatureAlgorithmName.ES512, JWTAlgorithmName.ES512);
	}

	private AlgorithmsMap() {
		super();
	};
	
}
