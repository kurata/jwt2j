package br.com.aqueteron.jwt2j.signature;

import java.util.HashMap;
import java.util.Map;

public class AuxJWTEncoderImpl extends JWTEncoderAbstract {

	public AuxJWTEncoderImpl(String algorithm) {
		super(algorithm);
	}

	@Override
	public byte[] sign(byte[] bytes) {
		return bytes;
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		Map<String,String> algorithmNameMap = new HashMap<String, String>();
		algorithmNameMap.put("algorithm", "algorithmName");
		return algorithmNameMap;
	}

}
