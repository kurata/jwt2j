package br.com.aqueteron.jwt2j.signature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTConfigException;

public class MacVerifier extends JWTSignatureVerifierAbstract {

	private static final String CHARSET_UTF8 = "UTF-8";

	public static final String HS256_NAME = "HS256";

	public static final String HS256_ALGORITHM = "HMACSHA256";

	public static final String HS384_NAME = "HS384";

	public static final String HS384_ALGORITHM = "HMACSHA384";

	public static final String HS512_NAME = "HS512";

	public static final String HS512_ALGORITHM = "HMACSHA512";

	private static final String JWT_STRING = "jwt";

	private static final Map<String, String> ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		ALGORITHM_NAME_MAP.put(HS256_ALGORITHM, HS256_NAME);
		ALGORITHM_NAME_MAP.put(HS384_ALGORITHM, HS384_NAME);
		ALGORITHM_NAME_MAP.put(HS512_ALGORITHM, HS512_NAME);
	};

	private Mac mac;

	public MacVerifier(final String algorithm, final String secretKey) {
		super(algorithm);
		if (validAlgorithm(algorithm) && null != secretKey && !secretKey.isEmpty()) {
			try {
				this.mac = Mac.getInstance(algorithm);
				SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET_UTF8), algorithm);
				this.mac.init(keySpec);
			} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
				throw new JWTConfigException(e.getMessage(), e);
			}
		} else {
			throw new JWTConfigException("Invalid MacVerifier algorithm. Try some like: algorithm=HMACSHA256 .");
		}
	}

	@Override
	public Boolean verifySignature(final byte[] data, final byte[] signature) {
		byte[] dataSigned = this.mac.doFinal(data);
		return MessageDigest.isEqual(dataSigned, signature);
	}

	@Override
	public Boolean verifyJOSEHeader(final JOSEHeader joseHeader) {
		if (JWT_STRING.equals(joseHeader.getTyp())) {
			return getJWTAlgorithmName().equals(joseHeader.getAlg());
		}
		return Boolean.FALSE;
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		return ALGORITHM_NAME_MAP;
	}
}
