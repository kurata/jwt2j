package br.com.aqueteron.jwt2j.signature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JWTConfigException;

public class MacEncoderTest {

	private static final Map<String, String> EXPECTED_ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS256_ALGORITHM, MacVerifier.HS256_NAME);
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS384_ALGORITHM, MacVerifier.HS384_NAME);
		EXPECTED_ALGORITHM_NAME_MAP.put(MacVerifier.HS512_ALGORITHM, MacVerifier.HS512_NAME);
	};

	private MacEncoder macEncoder;

	@Before
	public void setUp() throws Exception {
		this.macEncoder = new MacEncoder("HMACSHA256", "secretkey");
	}

	@Test
	public void testMacEncoder() {
		MacEncoder macEncoder = new MacEncoder("HMACSHA256", "secretkey");
		assertNotNull(macEncoder);
	}

	@Test(expected = JWTConfigException.class)
	public void testMacEncoderInvalidAlgorithm() {
		new MacEncoder("LALALA", "secretkey");
	}

	@Test(expected = JWTConfigException.class)
	public void testMacEncoderEmptySecretKey() {
		new MacEncoder("HMACSHA256", "");
	}

	@Test(expected = JWTConfigException.class)
	public void testMacEncoderNullSecretKey() {
		new MacEncoder("HMACSHA256", null);
	}

	@Test
	public void testGetAlgorithmNameMap() {
		assertThat(this.macEncoder.getAlgorithmNameMap(), is(EXPECTED_ALGORITHM_NAME_MAP));
	}

	@Test
	public void testSign() {
		String simpleString = "simple string";
		String expectedResultBase64String = "+qZlV12ADi8nV6CHwsb91xsKgOVM2aCQWZ1z59h79k4";

		byte[] resultByteArray = this.macEncoder.sign(simpleString.getBytes());
		assertThat(resultByteArray, equalTo(Base64.getDecoder().decode(expectedResultBase64String)));
	}

}
