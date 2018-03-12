package br.com.aqueteron.jwt2j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import org.junit.Before;
import org.junit.Test;

public class JWTEncodersTest {

	private PrivateKey rsaPrivateKey;

	private PrivateKey ecdsaPrivateKey;

	@Before
	public void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		this.rsaPrivateKey = PrivateKeyReader.getFromBase64File("src/test/resources/rsa.key", "RSA");
		this.ecdsaPrivateKey = PrivateKeyReader.getFromBase64File("src/test/resources/ecdsa.key", "EC");

	}

	@Test
	public void testHs256() {
		JWTEncoder encoder = JWTEncoders.hs256("secretKey");
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("HS256", joseHeader.getAlg());
	}

	@Test
	public void testHs384() {
		JWTEncoder encoder = JWTEncoders.hs384("secretKey");
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("HS384", joseHeader.getAlg());
	}

	@Test
	public void testHs512() {
		JWTEncoder encoder = JWTEncoders.hs512("secretKey");
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("HS512", joseHeader.getAlg());
	}

	@Test
	public void testRs256() {
		JWTEncoder encoder = JWTEncoders.rs256(this.rsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("RS256", joseHeader.getAlg());
	}

	@Test
	public void testRs384() {
		JWTEncoder encoder = JWTEncoders.rs384(this.rsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("RS384", joseHeader.getAlg());
	}

	@Test
	public void testRs512() {
		JWTEncoder encoder = JWTEncoders.rs512(this.rsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("RS512", joseHeader.getAlg());
	}

	@Test
	public void testEs256() {
		JWTEncoder encoder = JWTEncoders.es256(this.ecdsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("ES256", joseHeader.getAlg());
	}

	@Test
	public void testEs384() {
		JWTEncoder encoder = JWTEncoders.es384(this.ecdsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("ES384", joseHeader.getAlg());
	}

	@Test
	public void testEs512() {
		JWTEncoder encoder = JWTEncoders.es512(this.ecdsaPrivateKey);
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("ES512", joseHeader.getAlg());
	}

	@Test
	public void testNone() {
		JWTEncoder encoder = JWTEncoders.none();
		assertNotNull(encoder);
		JOSEHeader joseHeader = encoder.getJOSEHeader();
		assertNotNull(joseHeader);
		assertEquals("none", joseHeader.getAlg());
	}

}
