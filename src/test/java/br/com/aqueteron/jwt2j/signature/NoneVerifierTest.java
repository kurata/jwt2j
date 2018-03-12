package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JOSEHeader;

public class NoneVerifierTest {

	private NoneVerifier verifier;

	@Before
	public void setUp() {
		this.verifier = new NoneVerifier();
	}

	@Test
	public void testVerifySignature() {
		assertTrue(this.verifier.verifySignature(null, null));
		assertTrue(this.verifier.verifySignature(new byte[0], null));
		assertFalse(this.verifier.verifySignature(new byte[0], new byte[0]));
		assertFalse(this.verifier.verifySignature(null, new byte[0]));
	}

	@Test
	public void testVerifyJOSEHeader() {
		JOSEHeader joseHeader = new JOSEHeader("none", "jwt");
		assertTrue(this.verifier.verifyJOSEHeader(joseHeader));
		joseHeader = new JOSEHeader("none", "");
		assertFalse(this.verifier.verifyJOSEHeader(joseHeader));
	}

	@Test
	public void testGetJWTAlgorithmName() {
		assertEquals("none" , this.verifier.getJWTAlgorithmName());
	}

}
