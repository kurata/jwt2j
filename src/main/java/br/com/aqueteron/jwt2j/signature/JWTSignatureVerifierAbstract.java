package br.com.aqueteron.jwt2j.signature;

import java.util.Map;

public abstract class JWTSignatureVerifierAbstract implements JWTSignatureVerifier {

	private final String algorithm;

	public JWTSignatureVerifierAbstract(String algorithm) {
		super();
		this.algorithm = algorithm;
	}

	protected abstract Map<String, String> getAlgorithmNameMap();

	protected Boolean validAlgorithm(final String algorithm) {
		return getAlgorithmNameMap().containsKey(algorithm);
	}

	public String getJWTAlgorithmName() {
		return this.getAlgorithmNameMap().get(this.algorithm);
	}

}
