package br.com.aqueteron.jwt2j;

public class JOSEHeader {

	/**
	 * alg - Identifica o algoritmo a ser utilizado na criptografia.
	 */
	private String alg;

	/**
	 * typ (Type) - Identifica o media type.
	 */
	private String typ;

	private String enc;

	public JOSEHeader() {
		super();
	}

	public JOSEHeader(String alg, String typ) {
		super();
		this.alg = alg;
		this.typ = typ;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getEnc() {
		return enc;
	}

	public void setEnc(String enc) {
		this.enc = enc;
	}

}
