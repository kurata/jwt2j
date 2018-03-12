package br.com.aqueteron.jwt2j.signature;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTDecoder;
import br.com.aqueteron.jwt2j.JWTPayload;

public class JWTSignatureDecoder<T extends JWTPayload> implements JWTDecoder<T> {

	private static final String DOT_STRING = ".";

	private static final String CHARSET_UTF8 = "UTF-8";

	private static final Decoder BASE64_DECODER = Base64.getDecoder();

	private final JWTSignatureVerifier verifier;

	private final Class<T> jwtPayloadImplClass;

	public JWTSignatureDecoder(final JWTSignatureVerifier verifier, final Class<T> jwtPayloadImplClass) {
		super();
		this.verifier = verifier;
		this.jwtPayloadImplClass = jwtPayloadImplClass;
	}

	@Override
	public T decode(String jwtToken) throws InvalidJWTTokenException {
		try {
			String[] tokenSplit = jwtToken.split(Pattern.quote(DOT_STRING));
			if ((tokenSplit.length == 3) || (tokenSplit.length == 2)) {
				return signatureDecode(tokenSplit);
			}
		} catch (IOException e) {
			throw new InvalidJWTTokenException("Invalid JWT Token content.", e);
		}
		throw new InvalidJWTTokenException("Invalid JWT Token content.");
	}

	private T signatureDecode(final String[] tokenSplit) throws IOException, InvalidJWTTokenException {
		ObjectMapper objectMapper = new ObjectMapper();
		String headerBase64String = tokenSplit[0];
		String headerString = new String(BASE64_DECODER.decode(headerBase64String), CHARSET_UTF8);
		JOSEHeader joseHeader = objectMapper.readValue(headerString, JOSEHeader.class);
		if (this.verifier.verifyJOSEHeader(joseHeader)) {
			byte[] signByteArray = null;
			String payloadBase64String = tokenSplit[1];
			String payload = new String(BASE64_DECODER.decode(payloadBase64String), CHARSET_UTF8);
			T jwtPayload = objectMapper.readValue(payload, this.jwtPayloadImplClass);

			if (tokenSplit.length == 3) {
				String signBase64String = tokenSplit[2];
				signByteArray = BASE64_DECODER.decode(signBase64String);
			}

			String dataString = headerString + DOT_STRING + payload;
			Boolean verificationValue = this.verifier.verifySignature(dataString.getBytes(CHARSET_UTF8), signByteArray);
			if (verificationValue) {
				return tokenValidation(jwtPayload);
			} else {
				throw new InvalidJWTTokenException("Signature invalid.");
			}
		} else {
			throw new InvalidJWTTokenException("JWT JOSE Header invalid.");
		}
	}

	private T tokenValidation(T jwtPayload) throws InvalidJWTTokenException {
		ZonedDateTime now = ZonedDateTime.now();

		if ((null != jwtPayload.getExp()) && now.isAfter(jwtPayload.getExp())) {
			throw new InvalidJWTTokenException("Now is after JWT Token exp time (Expiration Time).");
		}

		if ((null != jwtPayload.getNbf()) && now.isBefore(jwtPayload.getNbf())) {
			throw new InvalidJWTTokenException("Now is before JWT Token nbf time (Not Before Time).");
		}
		return jwtPayload;
	}

}
