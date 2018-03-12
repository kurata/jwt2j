package br.com.aqueteron.jwt2j;

import static org.junit.Assert.*;

import java.security.PublicKey;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.signature.JWTSignatureVerifier;

public class JWTSignatureVerifiersTest {

	private PublicKey rsaPublicKey;

	private PublicKey ecdsaPublicKey;

	@Before
	public void setUp() throws Exception {
		this.rsaPublicKey = PublicKeyReader.getFromBase64File("src/test/resources/rsa.pub", "RSA");
		this.ecdsaPublicKey = PublicKeyReader.getFromBase64File("src/test/resources/ecdsa.pub", "EC");
	}

	@Test
	public void testHs256() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.hs256("secretKey");
		assertEquals("HS256" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testHs384() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.hs384("secretKey");
		assertEquals("HS384" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testHs512() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.hs512("secretKey");
		assertEquals("HS512" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testRs256() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.rs256(rsaPublicKey);
		assertEquals("RS256" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testRs384() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.rs384(rsaPublicKey);
		assertEquals("RS384" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testRs512() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.rs512(rsaPublicKey);
		assertEquals("RS512" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testEs256() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.es256(ecdsaPublicKey);
		assertEquals("ES256" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testEs384() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.es384(ecdsaPublicKey);
		assertEquals("ES384" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testEs512() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.es512(ecdsaPublicKey);
		assertEquals("ES512" , verifier.getJWTAlgorithmName());
	}

	@Test
	public void testNone() {
		JWTSignatureVerifier verifier = JWTSignatureVerifiers.none();
		assertEquals("none" , verifier.getJWTAlgorithmName());
	}

}
