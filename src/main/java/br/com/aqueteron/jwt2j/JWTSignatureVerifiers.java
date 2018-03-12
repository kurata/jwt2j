package br.com.aqueteron.jwt2j;

import java.security.PublicKey;

import br.com.aqueteron.jwt2j.signature.JWTSignatureVerifier;
import br.com.aqueteron.jwt2j.signature.MacVerifier;
import br.com.aqueteron.jwt2j.signature.NoneVerifier;
import br.com.aqueteron.jwt2j.signature.SignatureAlgorithmName;
import br.com.aqueteron.jwt2j.signature.SignatureVerifier;

public class JWTSignatureVerifiers {

	private JWTSignatureVerifiers() {
		super();
	}

	public static JWTSignatureVerifier hs256(final String secretKey) {
		return new MacVerifier(MacVerifier.HS256_ALGORITHM, secretKey);
	}

	public static JWTSignatureVerifier hs384(final String secretKey) {
		return new MacVerifier(MacVerifier.HS384_ALGORITHM, secretKey);
	}

	public static JWTSignatureVerifier hs512(final String secretKey) {
		return new MacVerifier(MacVerifier.HS512_ALGORITHM, secretKey);
	}

	public static JWTSignatureVerifier rs256(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.RS256, publicKey);
	}

	public static JWTSignatureVerifier rs384(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.RS384, publicKey);
	}

	public static JWTSignatureVerifier rs512(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.RS512, publicKey);
	}

	public static JWTSignatureVerifier es256(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.ES256, publicKey);
	}

	public static JWTSignatureVerifier es384(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.ES384, publicKey);
	}

	public static JWTSignatureVerifier es512(final PublicKey publicKey) {
		return new SignatureVerifier(SignatureAlgorithmName.ES512, publicKey);
	}
	
	public static JWTSignatureVerifier none() {
		return new NoneVerifier();
	}

}
