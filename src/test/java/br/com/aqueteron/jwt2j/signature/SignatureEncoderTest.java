package br.com.aqueteron.jwt2j.signature;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.aqueteron.jwt2j.JWTConfigException;
import br.com.aqueteron.jwt2j.PrivateKeyReader;
import br.com.aqueteron.jwt2j.signature.SignatureEncoder;

public class SignatureEncoderTest {

	private static final Map<String, String> EXPECTED_ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS256, JWTAlgorithmName.RS256);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS384, JWTAlgorithmName.RS384);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.RS512, JWTAlgorithmName.RS512);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES256, JWTAlgorithmName.ES256);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES384, JWTAlgorithmName.ES384);
		EXPECTED_ALGORITHM_NAME_MAP.put(SignatureAlgorithmName.ES512, JWTAlgorithmName.ES512);
	};

	private SignatureEncoder signatureEncoder;

	@Before
	public void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		PrivateKey privateKey = PrivateKeyReader.getFromBase64File("src/test/resources/rsa.key", "RSA");
		this.signatureEncoder = new SignatureEncoder("SHA256withRSA", privateKey);
	}

	@Test
	public void testSign() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		PrivateKey privateKey = PrivateKeyReader.getFromBase64File("src/test/resources/rsa.key", "RSA");
		SignatureEncoder signatureEncoder = new SignatureEncoder("SHA256withRSA", privateKey);
		assertNotNull(signatureEncoder);
	}

	@Test(expected = JWTConfigException.class)
	public void testSignInvalidAlgorithm() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		PrivateKey privateKey = PrivateKeyReader.getFromBase64File("src/test/resources/rsa.key", "RSA");
		new SignatureEncoder("LALAL", privateKey);
	}

	@Test(expected = JWTConfigException.class)
	public void testSignInvalidKeyException() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		new SignatureEncoder("SHA256withRSA", null);
	}

	@Test
	public void testGetAlgorithmNameMap() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		assertThat(this.signatureEncoder.getAlgorithmNameMap(), is(EXPECTED_ALGORITHM_NAME_MAP));
	}

	@Test
	public void testSignatureEncoder() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String simpleString = "simple string";
		String expectedResultBase64String = "GWRnhBwjanB8u1kMApkkwWsxdTxXa6U3OB6ESEU1KsUE/T/nDpBGhvE0o981J+rlrNgFc83w0dcqvPAQJhLo364qRu2t2Bf9iyKGS04DGyoC8mR1SH0SyyiLUOnM/2zbIkmcYwEtke1I4K8510ElUdd/dc6TiWy0xtY6fOMrnjxauAZR6/LyohIXjeTPd8ITGgHQ35L4EvNdavdQeKwuTXHZpw1a7jukfy7Bzi0xqA4kD5N0b/uf3N6ReqJlBV7ERj7dsDYwpl6o82nH1HiWAJyIZ3I4aQCnWOFw9NRABv7h/mWuDGg1wESBTzkENdYza3DCrm1jMuf5Fh/IDwxH2Q";

		byte[] resultByteArray = this.signatureEncoder.sign(simpleString.getBytes());
		assertThat(resultByteArray, equalTo(Base64.getDecoder().decode(expectedResultBase64String)));
	}

}
