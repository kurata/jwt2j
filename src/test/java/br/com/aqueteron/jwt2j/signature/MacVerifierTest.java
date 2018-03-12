package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTConfigException;

public class MacVerifierTest {

	private static final Map<String, String> EXPECTED_ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS256_ALGORITHM, MacVerifier.HS256_NAME);
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS384_ALGORITHM, MacVerifier.HS384_NAME);
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS512_ALGORITHM, MacVerifier.HS512_NAME);
	};

	private MacVerifier macVerifier;

	@Before
	public void setUp() throws Exception {
		this.macVerifier = new MacVerifier("HMACSHA256", "secretkey");
	}

	@Test
	public void testGetAlgorithmNameMap() {
		assertThat(this.macVerifier.getAlgorithmNameMap(), is(EXPECTED_ALGORITHM_NAME_MAP));
	}

	@Test
	public void testMacVerifier() {
		MacVerifier macVerifier = new MacVerifier("HMACSHA256", "secretkey");
		assertNotNull(macVerifier);
	}

	@Test(expected = JWTConfigException.class)
	public void testMacVerifierNoSuchAlgorithmException() {
		MacVerifier macVerifier = new MacVerifier("HMACSHA192", "secretkey");
		assertNotNull(macVerifier);
	}

	@Test(expected = JWTConfigException.class)
	public void testMacVerifierEmptySecretKey() {
		MacVerifier macVerifier = new MacVerifier("HMACSHA256", "");
		assertNotNull(macVerifier);
	}

	@Test(expected = JWTConfigException.class)
	public void testMacVerifierNullSecretKey() {
		MacVerifier macVerifier = new MacVerifier("HMACSHA256", null);
		assertNotNull(macVerifier);
	}

	@Test
	public void testVerifySignature() {
		String data = "simple string";
		String signature = "+qZlV12ADi8nV6CHwsb91xsKgOVM2aCQWZ1z59h79k4";

		assertTrue(this.macVerifier.verifySignature(data.getBytes(), Base64.getDecoder().decode(signature)));
	}

	@Test
	public void testVerifyJOSEHeader() {
		JOSEHeader joseHeader = new JOSEHeader(MacVerifier.HS256_NAME, "jwt");
		assertTrue(this.macVerifier.verifyJOSEHeader(joseHeader));
	}

	@Test
	public void testVerifyJOSEHeaderAlgorithmName() {
		JOSEHeader joseHeader = new JOSEHeader(MacVerifier.HS512_NAME, "jwt");
		assertFalse(this.macVerifier.verifyJOSEHeader(joseHeader));
	}

	@Test
	public void testVerifyJOSEHeaderType() {
		JOSEHeader joseHeader = new JOSEHeader(MacVerifier.HS256_NAME, "lol");
		assertFalse(this.macVerifier.verifyJOSEHeader(joseHeader));
	}

}
