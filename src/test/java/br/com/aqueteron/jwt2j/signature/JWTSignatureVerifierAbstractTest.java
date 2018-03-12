package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JWTSignatureVerifierAbstractTest {

	private JWTSignatureVerifierAbstract jwtSignatureVerifier;

	@Before
	public void setUp() throws Exception {
		this.jwtSignatureVerifier = new AuxJWTSignatureVerifierImpl("algorithm");
	}

	@Test
	public void testJWTSignatureVerifierAbstract() {
		JWTSignatureVerifierAbstract jwtSignatureVerifier = new AuxJWTSignatureVerifierImpl("algorithm");
		assertNotNull(jwtSignatureVerifier);
	}

	@Test
	public void testJWTSignatureVerifierAbstractEmptyAlgorithm() {
		JWTSignatureVerifierAbstract jwtSignatureVerifier = new AuxJWTSignatureVerifierImpl("");
		assertNotNull(jwtSignatureVerifier);
	}

	@Test
	public void testJWTSignatureVerifierAbstractNullAlgorithm() {
		JWTSignatureVerifierAbstract jwtSignatureVerifier = new AuxJWTSignatureVerifierImpl(null);
		assertNotNull(jwtSignatureVerifier);
	}

	@Test
	public void testValidAlgorithm() {
		assertTrue(this.jwtSignatureVerifier.validAlgorithm("algorithm"));
		assertFalse(this.jwtSignatureVerifier.validAlgorithm(""));
		assertFalse(this.jwtSignatureVerifier.validAlgorithm(null));
	}

	@Test
	public void testGetJWTAlgorithmName() {
		assertEquals("algorithmName", this.jwtSignatureVerifier.getJWTAlgorithmName());
	}

}
