package br.com.aqueteron.jwt2j.signature;

import java.util.HashMap;
import java.util.Map;

public class NoneEncoder extends JWTEncoderAbstract {

	private static final String ALGORITHM_NAME = "none";

	public NoneEncoder() {
		super(NoneEncoder.ALGORITHM_NAME);
	}

	@Override
	public byte[] sign(byte[] bytes) {
		return "".getBytes();
	}

	@Override
	public Map<String, String> getAlgorithmNameMap() {
		Map<String, String> algorithmNameMap = new HashMap<>();
		algorithmNameMap.put(ALGORITHM_NAME, ALGORITHM_NAME);
		return algorithmNameMap;
	}

}
