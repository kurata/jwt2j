package br.com.aqueteron.jwt2j;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aqueteron.jwt2j.util.JsonZoneDateTimeDeserializer;
import br.com.aqueteron.jwt2j.util.JsonZoneDateTimeSerializer;

/**
 * @author Fernando
 */
public class SimpleJWTPayload implements JWTPayload {

	/**
	 * iss (Issuer) - Identificador do emissor, pode ser usado para identificar quem
	 * gerou o JWT. Geralmente é uma string URI case-sensitive.
	 */
	private String iss;

	/**
	 * sub (Subject) - Geralmente utilizado para identificar um escopo de subdominio
	 * do emisso. Geralmente é uma string URI case-sensitive.
	 */
	private String sub;

	/**
	 * aud (Audience) - Itentifica os clientes aos quais este JWT é significativo.
	 * Ou seja na validação do token for identificado que ele foi utilizado por um
	 * cliente que não esta na lista, o token será rejeitado. Geralmente este campo
	 * é representado como um array de strings URI que representa cada consumidor
	 * case-sensitive.
	 */
	private String[] aud;

	/**
	 * exp (Expiration time) - Identifica o momento no tempo em que este token deve
	 * ser considerado expirado. Geralmente ele é representado com uma data
	 * numerica.
	 */
	@JsonDeserialize(using = JsonZoneDateTimeDeserializer.class)
	@JsonSerialize(using = JsonZoneDateTimeSerializer.class)
	private ZonedDateTime exp;

	/**
	 * nbf (Not Before) - Identifica um momento no tempo em que este token deve ser
	 * considerado valido. Geralmente ele é representado com uma data numérica.
	 */
	@JsonDeserialize(using = JsonZoneDateTimeDeserializer.class)
	@JsonSerialize(using = JsonZoneDateTimeSerializer.class)
	private ZonedDateTime nbf;

	/**
	 * iat (Issued at) - Identifica o momento em que o token foi emitido. Geralmente
	 * ele é representado com uma data numérica.
	 */
	@JsonDeserialize(using = JsonZoneDateTimeDeserializer.class)
	@JsonSerialize(using = JsonZoneDateTimeSerializer.class)
	private ZonedDateTime iat;

	/**
	 * jti (JWT Id) - Prove um identificador unico para o token. O valor do
	 * identificador deve ser definido de forma a evitar que dois tokens tenham o
	 * mesmo jti. String case-sensitive.
	 */
	private String jti;

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String[] getAud() {
		return aud.clone();
	}

	public void setAud(String... aud) {
		this.aud = aud.clone();
	}

	public ZonedDateTime getExp() {
		return exp;
	}

	public void setExp(ZonedDateTime exp) {
		this.exp = exp;
	}

	public ZonedDateTime getNbf() {
		return nbf;
	}

	public void setNbf(ZonedDateTime nbf) {
		this.nbf = nbf;
	}

	public ZonedDateTime getIat() {
		return iat;
	}

	public void setIat(ZonedDateTime iat) {
		this.iat = iat;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

}
