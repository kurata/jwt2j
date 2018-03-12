package br.com.aqueteron.jwt2j.signature;

public final class SignatureAlgorithmName {

	public static final String RS256 = "SHA256withRSA";

	public static final String RS384 = "SHA384withRSA";

	public static final String RS512 = "SHA512withRSA";

	public static final String ES256 = "SHA256withECDSA";

	public static final String ES384 = "SHA384withECDSA";

	public static final String ES512 = "SHA512withECDSA";

	private SignatureAlgorithmName() {
		super();
	}
}
