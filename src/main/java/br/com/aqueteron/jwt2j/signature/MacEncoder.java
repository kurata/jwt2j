package br.com.aqueteron.jwt2j.signature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import br.com.aqueteron.jwt2j.JWTConfigException;

public class MacEncoder extends JWTEncoderAbstract {

	private static final String CHARSET_UTF8 = "UTF-8";

	public static final String HS256_NAME = "HS256";

	public static final String HS256_ALGORITHM = "HMACSHA256";

	public static final String HS384_NAME = "HS384";

	public static final String HS384_ALGORITHM = "HMACSHA384";

	public static final String HS512_NAME = "HS512";

	public static final String HS512_ALGORITHM = "HMACSHA512";

	private static final Map<String, String> ALGORITHM_NAME_MAP = new HashMap<>();

	static {
		ALGORITHM_NAME_MAP.put(HS256_ALGORITHM, HS256_NAME);
		ALGORITHM_NAME_MAP.put(HS384_ALGORITHM, HS384_NAME);
		ALGORITHM_NAME_MAP.put(HS512_ALGORITHM, HS512_NAME);
	};

	private Mac mac;

	public MacEncoder(final String algorithm, final String secretKey) {
		super(algorithm);
		if (validAlgorithm(algorithm) && null != secretKey && !secretKey.isEmpty()) {
			try {
				this.mac = Mac.getInstance(algorithm);
				SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(CHARSET_UTF8), algorithm);
				this.mac.init(keySpec);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
				throw new JWTConfigException(e.getMessage(), e);
			}
		} else {
			throw new JWTConfigException(
					"Invalid RSAlgorithm name or algorithm. Try some like: name=HS256 algorithm=HMACSHA256 .");
		}
	}

	@Override
	public byte[] sign(final byte[] bytes) {
		return this.mac.doFinal(bytes);
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		return ALGORITHM_NAME_MAP;
	}
}
