package br.com.aqueteron.jwt2j.signature;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTConfigException;
import br.com.aqueteron.jwt2j.PublicKeyReader;
import br.com.aqueteron.jwt2j.signature.SignatureVerifier;

public class SignatureVerifierTest {

	private static final Map<String, String> EXPECTED_ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS256, JWTAlgorithmName.RS256);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS384, JWTAlgorithmName.RS384);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS512, JWTAlgorithmName.RS512);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES256, JWTAlgorithmName.ES256);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES384, JWTAlgorithmName.ES384);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES512, JWTAlgorithmName.ES512);
	};

	private SignatureVerifier signatureVerifier;

	@Before
	public void setUp() throws Exception {
		PublicKey publicKey = PublicKeyReader.getFromBase64File("src/test/resources/rsa.pub", "RSA");
		this.signatureVerifier = new SignatureVerifier("SHA256withRSA", publicKey);
	}

	@Test
	public void testGetAlgorithmNameMap() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		assertThat(this.signatureVerifier.getAlgorithmNameMap(), is(EXPECTED_ALGORITHM_NAME_MAP));
	}

	@Test
	public void testSignatureVerifier() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		PublicKey publicKey = PublicKeyReader.getFromBase64File("src/test/resources/rsa.pub", "RSA");
		SignatureVerifier signatureVerifier = new SignatureVerifier("SHA256withRSA", publicKey);

		assertNotNull(signatureVerifier);
	}

	@Test(expected = JWTConfigException.class)
	public void testSignatureVerifierInvalidKeyException()
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		new SignatureVerifier("SHA256withRSA", null);
	}

	@Test(expected = JWTConfigException.class)
	public void testSignatureVerifierInvalidAlgorithm()
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		PublicKey publicKey = PublicKeyReader.getFromBase64File("src/test/resources/rsa.pub", "RSA");
		new SignatureVerifier("LALALA", publicKey);
	}

	@Test
	public void testVerifySignature() {
		String data = "simple string";
		String signature = "GWRnhBwjanB8u1kMApkkwWsxdTxXa6U3OB6ESEU1KsUE/T/nDpBGhvE0o981J+rlrNgFc83w0dcqvPAQJhLo364qRu2t2Bf9iyKGS04DGyoC8mR1SH0SyyiLUOnM/2zbIkmcYwEtke1I4K8510ElUdd/dc6TiWy0xtY6fOMrnjxauAZR6/LyohIXjeTPd8ITGgHQ35L4EvNdavdQeKwuTXHZpw1a7jukfy7Bzi0xqA4kD5N0b/uf3N6ReqJlBV7ERj7dsDYwpl6o82nH1HiWAJyIZ3I4aQCnWOFw9NRABv7h/mWuDGg1wESBTzkENdYza3DCrm1jMuf5Fh/IDwxH2Q";

		assertTrue(this.signatureVerifier.verifySignature(data.getBytes(), Base64.getDecoder().decode(signature)));
	}

	@Test
	public void testVerifySignatureEmptyData() {
		String data = "";
		String signature = "GWRnhBwjanB8u1kMApkkwWsxdTxXa6U3OB6ESEU1KsUE/T/nDpBGhvE0o981J+rlrNgFc83w0dcqvPAQJhLo364qRu2t2Bf9iyKGS04DGyoC8mR1SH0SyyiLUOnM/2zbIkmcYwEtke1I4K8510ElUdd/dc6TiWy0xtY6fOMrnjxauAZR6/LyohIXjeTPd8ITGgHQ35L4EvNdavdQeKwuTXHZpw1a7jukfy7Bzi0xqA4kD5N0b/uf3N6ReqJlBV7ERj7dsDYwpl6o82nH1HiWAJyIZ3I4aQCnWOFw9NRABv7h/mWuDGg1wESBTzkENdYza3DCrm1jMuf5Fh/IDwxH2Q";

		assertFalse(this.signatureVerifier.verifySignature(data.getBytes(), Base64.getDecoder().decode(signature)));
	}

	@Test
	public void testVerifyJOSEHeader() {
		JOSEHeader joseHeader = new JOSEHeader(JWTAlgorithmName.RS256, "jwt");
		assertTrue(this.signatureVerifier.verifyJOSEHeader(joseHeader));
	}

	@Test
	public void testVerifyJOSEHeaderAlgorithmName() {
		JOSEHeader joseHeader = new JOSEHeader(JWTAlgorithmName.ES256, "jwt");
		assertFalse(this.signatureVerifier.verifyJOSEHeader(joseHeader));
	}

	@Test
	public void testVerifyJOSEHeaderType() {
		JOSEHeader joseHeader = new JOSEHeader(JWTAlgorithmName.RS256, "lol");
		assertFalse(this.signatureVerifier.verifyJOSEHeader(joseHeader));
	}

}
