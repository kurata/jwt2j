package br.com.aqueteron.jwt2j.signature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.com.aqueteron.jwt2j.JOSEHeader;

public class AuxJWTSignatureVerifierImpl extends JWTSignatureVerifierAbstract {

	public AuxJWTSignatureVerifierImpl(String algorithm) {
		super(algorithm);
	}

	@Override
	public Boolean verifySignature(byte[] data, byte[] signature) {
		return Arrays.equals(data, signature);
	}

	@Override
	public Boolean verifyJOSEHeader(JOSEHeader joseHeader) {
		if ("jwt".equals(joseHeader.getTyp())) {
			return getJWTAlgorithmName().equals(joseHeader.getAlg());
		}
		return Boolean.FALSE;
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		Map<String, String> algorithmNameMap = new HashMap<String, String>();
		algorithmNameMap.put("algorithm", "algorithmName");
		return algorithmNameMap;
	}

}
