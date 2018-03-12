package br.com.aqueteron.jwt2j.signature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.SimpleJWTPayload;

public class NoneEncoderTest {

	private NoneEncoder noneEncoder;

	@Before
	public void setUp() {
		this.noneEncoder = new NoneEncoder();
	}

	@Test
	public void testNoneEncoder() {
		NoneEncoder noneEncoder = new NoneEncoder();
		assertNotNull(noneEncoder);
	}

	@Test
	public void testSign() {
		String simpleString = "simple string";
		String expectedResultBase64String = "";

		byte[] resultByteArray = this.noneEncoder.sign(simpleString.getBytes());
		assertThat(resultByteArray, equalTo(Base64.getDecoder().decode(expectedResultBase64String)));
	}

	@Test
	public void testGetAlgorithmNameMap() {
		Map<String, String> expectedMap = new HashMap<String, String>();
		expectedMap.put("none", "none");

		assertThat(this.noneEncoder.getAlgorithmNameMap(), is(expectedMap));
	}

	@Test
	public void testEncodeJWTPayload() {
		SimpleJWTPayload jwtPayload = new SimpleJWTPayload();
		jwtPayload.setAud("test.aqueteron.com.br");
		jwtPayload.setIss("aqueteron.com.br");
		jwtPayload.setSub("aqueteron.com.br");

		String jwt = this.noneEncoder.encode(jwtPayload);
		assertEquals(
				"eyJhbGciOiJub25lIiwidHlwIjoiand0IiwiZW5jIjpudWxsfQ==.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==",
				jwt);
	}

}
