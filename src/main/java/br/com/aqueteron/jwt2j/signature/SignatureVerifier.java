package br.com.aqueteron.jwt2j.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Map;

import br.com.aqueteron.jwt2j.JOSEHeader;
import br.com.aqueteron.jwt2j.JWTConfigException;

public class SignatureVerifier extends JWTSignatureVerifierAbstract {

	private static final String JWT_STRING = "jwt";

	private Signature signature;

	public SignatureVerifier(final String algorithm, final PublicKey publicKey) {
		super(algorithm);
		if (validAlgorithm(algorithm)) {
			try {
				this.signature = Signature.getInstance(algorithm);
				this.signature.initVerify(publicKey);
			} catch (NoSuchAlgorithmException | InvalidKeyException e) {
				throw new JWTConfigException(e.getMessage(), e);
			}
		} else {
			throw new JWTConfigException("Invalid RSAVerifier algorithm. Try some like: algorithm=SHA256withRSA .");
		}
	}

	@Override
	public Boolean verifySignature(final byte[] data, final byte[] signature) {
		try {
			this.signature.update(data);
			return this.signature.verify(signature);
		} catch (SignatureException e) {
			throw new JWTConfigException(e.getMessage(), e);
		}
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
		return AlgorithmsMap.SIGNATURE_JWT_MAP;
	}

}
