package br.com.aqueteron.jwt2j.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Map;

import br.com.aqueteron.jwt2j.JWTConfigException;

public class SignatureEncoder extends JWTEncoderAbstract {

	private Signature signature;

	public SignatureEncoder(final String algorithm, final PrivateKey privateKey) {
		super(algorithm);
		if (validAlgorithm(algorithm)) {
			try {
				this.signature = Signature.getInstance(algorithm);
				this.signature.initSign(privateKey);
			} catch (NoSuchAlgorithmException | InvalidKeyException e) {
				throw new JWTConfigException(e.getMessage(), e);
			}
		} else {
			throw new JWTConfigException(
					"Invalid SignatureEncoder name or algorithm. Try some like algorithm=SHA256withRSA .");
		}
	}

	@Override
	public byte[] sign(final byte[] bytes) {
		try {
			signature.update(bytes);
			return this.signature.sign();
		} catch (SignatureException e) {
			throw new JWTConfigException(e.getMessage(), e);
		}
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		return AlgorithmsMap.SIGNATURE_JWT_MAP;
	}
}
