package br.com.aqueteron.jwt2j;

import java.security.PrivateKey;

import br.com.aqueteron.jwt2j.signature.MacEncoder;
import br.com.aqueteron.jwt2j.signature.NoneEncoder;
import br.com.aqueteron.jwt2j.signature.SignatureAlgorithmName;
import br.com.aqueteron.jwt2j.signature.SignatureEncoder;

public class JWTEncoders {
	
	private JWTEncoders() {
		super();
	}

	public static JWTEncoder hs256(final String secretKey) {
		return new MacEncoder(MacEncoder.HS256_ALGORITHM, secretKey);
	}

	public static JWTEncoder hs384(final String secretKey) {
		return new MacEncoder(MacEncoder.HS384_ALGORITHM, secretKey);
	}

	public static JWTEncoder hs512(final String secretKey) {
		return new MacEncoder(MacEncoder.HS512_ALGORITHM, secretKey);
	}

	public static JWTEncoder rs256(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.RS256, privateKey);
	}

	public static JWTEncoder rs384(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.RS384, privateKey);
	}

	public static JWTEncoder rs512(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.RS512, privateKey);
	}

	public static JWTEncoder es256(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.ES256, privateKey);
	}

	public static JWTEncoder es384(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.ES384, privateKey);
	}

	public static JWTEncoder es512(final PrivateKey privateKey) {
		return new SignatureEncoder(SignatureAlgorithmName.ES512, privateKey);
	}
	
	public static JWTEncoder none() {
		return new NoneEncoder();
	}

}
