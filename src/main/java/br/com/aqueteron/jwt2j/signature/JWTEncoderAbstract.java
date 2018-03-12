package br.com.aqueteron.jwt2j.signature;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTConfigException;
import br.com.aqueteron.jwt2j.JWTEncoder;
import br.com.aqueteron.jwt2j.JWTPayload;

public abstract class JWTEncoderAbstract implements JWTEncoder {

	private static final Logger LOGGER = LogManager.getLogger(JWTEncoderAbstract.class);

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final String CHARSET_ASCII = "ASCII";

	private static final Encoder BASE64_ENCODER = Base64.getEncoder();

	private static final String DOT_STRING = ".";

	private final String algorithm;

	public JWTEncoderAbstract(final String algorithm) {
		super();
		this.algorithm = algorithm;
	}

	protected abstract byte[] sign(byte[] bytes);

	protected abstract Map<String, String> getAlgorithmNameMap();

	protected Boolean validAlgorithm(final String algorithm) {
		return getAlgorithmNameMap().containsKey(algorithm);
	}

	public JOSEHeader getJOSEHeader() {
		return new JOSEHeader(getJWTAlgorithmName(), "jwt");
	}

	protected String getJWTAlgorithmName() {
		return this.getAlgorithmNameMap().get(this.algorithm);
	}

	@Override
	public String encode(final JWTPayload jwtPayload) {
		return this.encode(getJOSEHeader(), jwtPayload);
	}

	@Override
	public String encode(final JOSEHeader joseHeader, final JWTPayload jwtPayload) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String joseHeaderString = objectMapper.writeValueAsString(joseHeader);
			String joseHeaderBase64 = BASE64_ENCODER.encodeToString(joseHeaderString.getBytes(CHARSET_UTF8));
			LOGGER.debug("JOSE Header Base64: " + joseHeaderBase64);

			String payloadJson = objectMapper.writeValueAsString(jwtPayload);
			LOGGER.debug("JSON content: " + payloadJson);
			String payloadBase64 = BASE64_ENCODER.encodeToString(payloadJson.getBytes(CHARSET_UTF8));
			LOGGER.debug("JSON Base64: " + payloadBase64);
			String headerPlusPayload = joseHeaderBase64 + DOT_STRING + payloadBase64;

			String signatureBase64 = BASE64_ENCODER.encodeToString(sign(headerPlusPayload.getBytes(CHARSET_ASCII)));
			LOGGER.debug("SignatureBase64: " + signatureBase64);
			return buildJWTString(joseHeaderBase64, payloadBase64, signatureBase64);
		} catch (UnsupportedEncodingException | JsonProcessingException e) {
			throw new JWTConfigException(e.getMessage(), e);
		}
	}

	private String buildJWTString(final String joseHeaderBase64, final String contentBase64,
			final String signatureBase64) {
		StringBuilder stringBuilder = new StringBuilder(joseHeaderBase64);
		stringBuilder.append(DOT_STRING);
		stringBuilder.append(contentBase64);
		if (!signatureBase64.isEmpty()) {
			stringBuilder.append(DOT_STRING);
			stringBuilder.append(signatureBase64);
		}

		return stringBuilder.toString();
	}
}
