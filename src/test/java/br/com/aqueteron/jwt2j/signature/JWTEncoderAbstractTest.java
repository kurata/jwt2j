package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.SimpleJWTPayload;

public class JWTEncoderAbstractTest {

	private JWTEncoderAbstract jwtEncoderAbstract;

	@Before
	public void setUp() throws Exception {
		this.jwtEncoderAbstract = new AuxJWTEncoderImpl("algorithm");
	}

	@Test
	public void testJWTEncoderAbstract() {
		JWTEncoderAbstract jwtEncoderAbstract = new AuxJWTEncoderImpl("algorithm");
		assertNotNull(jwtEncoderAbstract);
	}

	@Test
	public void testJWTEncoderAbstractEmptyAlgorithm() {
		JWTEncoderAbstract jwtEncoderAbstract = new AuxJWTEncoderImpl("");
		assertNotNull(jwtEncoderAbstract);
	}

	@Test
	public void testJWTEncoderAbstractNullAlgorithm() {
		JWTEncoderAbstract jwtEncoderAbstract = new AuxJWTEncoderImpl(null);
		assertNotNull(jwtEncoderAbstract);
	}

	@Test
	public void testValidAlgorithm() {
		assertTrue(this.jwtEncoderAbstract.validAlgorithm("algorithm"));
		assertFalse(this.jwtEncoderAbstract.validAlgorithm("invalid"));
		assertFalse(this.jwtEncoderAbstract.validAlgorithm(""));
		assertFalse(this.jwtEncoderAbstract.validAlgorithm(null));
	}

	@Test
	public void testGetJOSEHeader() {
		JOSEHeader joseHeader = this.jwtEncoderAbstract.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("algorithmName", joseHeader.getAlg());
		assertEquals("jwt", joseHeader.getTyp());
	}

	@Test
	public void testEncodeSimpleJWTPayload() {
		SimpleJWTPayload jwtPayload = new SimpleJWTPayload();
		jwtPayload.setAud("test.aqueteron.com.br");
		jwtPayload.setIss("aqueteron.com.br");
		jwtPayload.setSub("aqueteron.com.br");

		String jwt = this.jwtEncoderAbstract.encode(jwtPayload);
		assertEquals(
				"eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0IiwiZW5jIjpudWxsfQ==.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==.ZXlKaGJHY2lPaUpoYkdkdmNtbDBhRzFPWVcxbElpd2lkSGx3SWpvaWFuZDBJaXdpWlc1aklqcHVkV3hzZlE9PS5leUpwYzNNaU9pSmhjWFZsZEdWeWIyNHVZMjl0TG1KeUlpd2ljM1ZpSWpvaVlYRjFaWFJsY205dUxtTnZiUzVpY2lJc0ltRjFaQ0k2V3lKMFpYTjBMbUZ4ZFdWMFpYSnZiaTVqYjIwdVluSWlYU3dpWlhod0lqcHVkV3hzTENKdVltWWlPbTUxYkd3c0ltbGhkQ0k2Ym5Wc2JDd2lhblJwSWpwdWRXeHNmUT09",
				jwt);
	}

	@Test
	public void testEncodeJOSEHeaderSimpleJWTPayload() {
		JOSEHeader joseHeader = new JOSEHeader("algorithmName", "jwt");
		
		SimpleJWTPayload jwtPayload = new SimpleJWTPayload();
		jwtPayload.setAud("test.aqueteron.com.br");
		jwtPayload.setIss("aqueteron.com.br");
		jwtPayload.setSub("aqueteron.com.br");

		String jwt = this.jwtEncoderAbstract.encode(joseHeader, jwtPayload);
		assertEquals(
				"eyJhbGciOiJhbGdvcml0aG1OYW1lIiwidHlwIjoiand0IiwiZW5jIjpudWxsfQ==.eyJpc3MiOiJhcXVldGVyb24uY29tLmJyIiwic3ViIjoiYXF1ZXRlcm9uLmNvbS5iciIsImF1ZCI6WyJ0ZXN0LmFxdWV0ZXJvbi5jb20uYnIiXSwiZXhwIjpudWxsLCJuYmYiOm51bGwsImlhdCI6bnVsbCwianRpIjpudWxsfQ==.ZXlKaGJHY2lPaUpoYkdkdmNtbDBhRzFPWVcxbElpd2lkSGx3SWpvaWFuZDBJaXdpWlc1aklqcHVkV3hzZlE9PS5leUpwYzNNaU9pSmhjWFZsZEdWeWIyNHVZMjl0TG1KeUlpd2ljM1ZpSWpvaVlYRjFaWFJsY205dUxtTnZiUzVpY2lJc0ltRjFaQ0k2V3lKMFpYTjBMbUZ4ZFdWMFpYSnZiaTVqYjIwdVluSWlYU3dpWlhod0lqcHVkV3hzTENKdVltWWlPbTUxYkd3c0ltbGhkQ0k2Ym5Wc2JDd2lhblJwSWpwdWRXeHNmUT09",
				jwt);
	}

}
